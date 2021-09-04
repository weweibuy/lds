package com.weweibuy.lds.op.core;

import com.weweibuy.lds.op.model.po.OpLogModule;
import com.weweibuy.lds.op.model.vo.ModuleInfo;
import com.weweibuy.lds.op.model.vo.ModuleScannerResult;
import com.weweibuy.lds.op.repository.OpLogModuleRepository;
import com.weweibuy.lds.op.support.MavenArtifactSupport;
import lombok.RequiredArgsConstructor;
import org.eclipse.aether.artifact.Artifact;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 模块扫描器
 *
 * @author durenhao
 * @date 2021/9/3 22:43
 **/
@Component
@RequiredArgsConstructor
public class ModuleScanner {

    private final ModuleHolder moduleHolder;

    private final OpLogModuleRepository opLogModuleRepository;


    /**
     * 扫描  输出新增/更新/删除的模块
     *
     * @return
     */
    public ModuleScannerResult doScan() {
        List<OpLogModule> opLogModuleList = opLogModuleRepository.select();
        Map<String, OpLogConvertModule> convertModuleMap = moduleHolder.currentModule();
        List<OpLogModule> opLogModuleListAdd = new ArrayList<>();
        List<ModuleScannerResult.OpLogModuleUpdate> opLogModuleListUpdate = new ArrayList<>();
        List<OpLogConvertModule> opLogModuleListRemove = new ArrayList<>();

        for (int i = 0; i < opLogModuleList.size(); i++) {
            OpLogModule opLogModule = opLogModuleList.get(i);
            OpLogConvertModule opLogConvertModule = convertModuleMap.get(opLogModule.getSystemId());
            if (opLogConvertModule == null) {
                opLogModuleListAdd.add(opLogModule);
                break;
            }
            ModuleInfo moduleInfo = opLogConvertModule.moduleInfo();

            if (needUpdate(opLogModule, moduleInfo)) {
                ModuleScannerResult.OpLogModuleUpdate moduleUpdate =
                        ModuleScannerResult.moduleUpdate(opLogModule, opLogConvertModule);
                opLogModuleListUpdate.add(moduleUpdate);
            }
        }

        // 判断是否需要删除
        Set<String> systemIdSet = opLogModuleList.stream()
                .map(OpLogModule::getSystemId)
                .collect(Collectors.toSet());

        convertModuleMap.entrySet().stream()
                .filter(e -> !systemIdSet.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .forEach(opLogModuleListRemove::add);

        return ModuleScannerResult.fromScan(opLogModuleListAdd, opLogModuleListUpdate,
                opLogModuleListRemove);
    }


    /**
     * 是否需要更新
     *
     * @param opLogModule
     * @return
     */
    public boolean needUpdate(OpLogModule opLogModule, ModuleInfo moduleInfo) {
        boolean release = moduleInfo.isRelease();
        if (release && !moduleInfo.moduleChange(opLogModule)) {
            return true;
        }
        if (!release && !sameMd5(opLogModule, moduleInfo)) {
            return true;
        }
        return false;
    }


    /**
     * 比较MD5值
     *
     * @param opLogModule
     * @param moduleInfo
     * @return
     */
    private boolean sameMd5(OpLogModule opLogModule, ModuleInfo moduleInfo) {
        Artifact artifact = MavenArtifactSupport.loadArtifact(opLogModule);
        if (artifact == null) {
            return true;
        }
        return moduleInfo.sameMd5(artifact);
    }


}
