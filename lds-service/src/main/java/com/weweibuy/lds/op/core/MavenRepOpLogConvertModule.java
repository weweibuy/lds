package com.weweibuy.lds.op.core;

import com.weweibuy.framework.common.core.exception.Exceptions;
import com.weweibuy.lds.iop.LogParam;
import com.weweibuy.lds.iop.OpLog;
import com.weweibuy.lds.iop.OpLogHandler;
import com.weweibuy.lds.op.model.vo.ModuleInfo;

import java.io.File;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.ServiceLoader;

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

    public MavenRepOpLogConvertModule(ModuleInfo moduleInfo) {
        this.moduleInfo = moduleInfo;
        this.modelClassLoader = new ModelClassLoader(jarFileUrl(moduleInfo.getArtifact().getFile()));
    }

    private URL jarFileUrl(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new UncheckedIOException(e);
        }
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
        ServiceLoader<OpLogHandler> loader = ServiceLoader.load(OpLogHandler.class, modelClassLoader);
        Iterator<OpLogHandler> iterator = loader.iterator();
        OpLogHandler handler = null;
        if (iterator.hasNext()) {
            handler = iterator.next();
        }
        if (handler == null) {
            throw Exceptions.business("无法加载到 OpLogHandler 的实现类");
        }
        handler.init();
        this.opLogHandler = handler;
    }

    @Override
    public void destroy() throws Exception {
        opLogHandler.destroy();
    }
}
