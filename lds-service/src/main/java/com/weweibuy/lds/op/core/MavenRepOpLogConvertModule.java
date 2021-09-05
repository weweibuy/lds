package com.weweibuy.lds.op.core;

import com.weweibuy.lds.iop.LogParam;
import com.weweibuy.lds.iop.OpLog;
import com.weweibuy.lds.iop.OpLogHandler;
import com.weweibuy.lds.op.model.vo.ModuleInfo;

/**
 * maven 仓库操作日志模块
 *
 * @author durenhao
 * @date 2021/9/4 11:55
 **/
public class MavenRepOpLogConvertModule implements OpLogConvertModule {

    private ModuleInfo moduleInfo;

    private ModelClassLoader modelClassLoader;

    private OpLogHandler opLogHandler;

    public MavenRepOpLogConvertModule(ModuleInfo moduleInfo, ModelClassLoader modelClassLoader, OpLogHandler opLogHandler) {
        this.moduleInfo = moduleInfo;
        this.modelClassLoader = modelClassLoader;
        this.opLogHandler = opLogHandler;
    }


    @Override
    public OpLog convert(LogParam logParam) {
        return opLogHandler.handlerGwOpLog(logParam);
    }

    @Override
    public ModuleInfo moduleInfo() {
        return moduleInfo;
    }

    @Override
    public void init() throws Exception {
        opLogHandler.init();
    }

    @Override
    public void destroy() throws Exception {
        try {
            opLogHandler.destroy();
        } finally {
            modelClassLoader.close();
            modelClassLoader = null;
        }
    }
}
