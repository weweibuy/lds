package com.weweibuy.lds.op.core;

import com.weweibuy.framework.common.core.concurrent.LogExceptionThreadFactory;
import com.weweibuy.lds.op.model.po.OpLogModule;
import com.weweibuy.lds.op.model.vo.ModuleScannerResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 模块调度器
 *
 * @author durenhao
 * @date 2021/9/3 22:46
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class ModuleDispatch implements InitializingBean {

    private final ModuleHolder moduleHolder;

    private final ModuleScanner moduleScanner;

    private ScheduledExecutorService schedule;

    private final OpLogConvertModuleLoader opLogConvertModuleLoader;


    /**
     * 查询模块
     *
     * @param systemId
     * @return
     */
    public Optional<OpLogConvertModule> findModule(String systemId) {
        return moduleHolder.findModule(systemId);
    }

    public void scanAndUpdate() {
        ModuleScannerResult moduleScannerResult = moduleScanner.doScan();
        List<OpLogConvertModule> opLogConvertModuleList = moduleScannerResult.getOpLogModuleListRemove();
        List<OpLogModule> opLogModuleListAdd = moduleScannerResult.getOpLogModuleListAdd();
        List<ModuleScannerResult.OpLogModuleUpdate> opLogModuleListUpdate = moduleScannerResult.getOpLogModuleListUpdate();

        removeModule(opLogConvertModuleList);
        addModule(opLogModuleListAdd);
        updateModule(opLogModuleListUpdate);
    }


    private void addModule(List<OpLogModule> opLogModuleListAdd) {
        opLogModuleListAdd.forEach(this::addModuleAndLog);
    }

    private void removeModule(List<OpLogConvertModule> opLogConvertModuleList) {
        opLogConvertModuleList.forEach(this::removeModuleAndLog);
    }


    private void updateModule(List<ModuleScannerResult.OpLogModuleUpdate> opLogModuleListUpdate) {
        opLogModuleListUpdate.forEach(this::updateModuleAndLog);
    }

    private void updateModuleAndLog(ModuleScannerResult.OpLogModuleUpdate opLogModuleUpdate) {
        OpLogModule opLogModule = opLogModuleUpdate.getOpLogModuleUpdate();
        OpLogConvertModule moduleUpdate = opLogModuleUpdate.getModuleUpdate();

        OpLogConvertModule newModule = null;
        try {
            newModule = opLogConvertModuleLoader.loadModule(opLogModule);
        } catch (Exception e) {
            log.error("加载操作日志模块: {}, 异常:", opLogModule, e);
            return;
        }
        if (newModule == null) {
            lodNoModule(opLogModule);
            return;
        }
        removeModuleAndLog(moduleUpdate);
        moduleHolder.addModule(opLogModule.getSystemId(), newModule);
    }

    private void addModuleAndLog(OpLogModule opLogModule) {
        log.warn("加载操作日志模块: {}", opLogModule);
        try {
            OpLogConvertModule opLogConvertModule = opLogConvertModuleLoader.loadModule(opLogModule);
            if (opLogConvertModule == null) {
                lodNoModule(opLogModule);
                return;
            }
            moduleHolder.addModule(opLogModule.getSystemId(), opLogConvertModule);
        } catch (Exception e) {
            log.error("加载操作日志模块: {}, 异常:", opLogModule, e);
        }
    }

    private void lodNoModule(OpLogModule opLogModule) {
        log.warn("日志模块: {}, 不存在无法加载", opLogModule);
    }

    private void removeModuleAndLog(OpLogConvertModule opLogConvertModule) {
        log.warn("卸载操作日志模块: {}", opLogConvertModule.moduleInfo());
        try {
            opLogConvertModuleLoader.unLoadModule(opLogConvertModule);
            moduleHolder.removeModule(opLogConvertModule.moduleInfo().getSystemId());
        } catch (Exception e) {
            log.error("卸载操作日志模块: {}, 异常: ", opLogConvertModule.moduleInfo(), e);
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        schedule = new ScheduledThreadPoolExecutor(1,
                new LogExceptionThreadFactory("module-scan-schedule-"),
                new ThreadPoolExecutor.DiscardPolicy());
        schedule.scheduleWithFixedDelay(this::scanAndUpdate, 5, 10, TimeUnit.SECONDS);
    }


}
