package com.weweibuy.lds.op.core;

import com.weweibuy.framework.common.core.exception.Exceptions;
import com.weweibuy.lds.iop.OpLogHandler;
import com.weweibuy.lds.op.model.po.OpLogModule;
import com.weweibuy.lds.op.model.vo.ModuleInfo;
import com.weweibuy.lds.op.support.MavenArtifactSupport;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.aether.artifact.Artifact;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 模块加载器
 *
 * @author durenhao
 * @date 2021/9/3 22:27
 **/
@Slf4j
@Service
public class OpLogConvertModuleLoader {


    /**
     * 加载模块
     *
     * @param opLogModule
     * @return
     */
    public OpLogConvertModule loadModule(OpLogModule opLogModule) throws Exception {
        Artifact artifact = MavenArtifactSupport.loadArtifact(opLogModule);
        if (artifact == null) {
            return null;
        }
        return loadModule(opLogModule, artifact);
    }

    /**
     * 加载模块
     *
     * @param opLogModule
     * @param artifact
     * @return
     * @throws Exception
     */
    public OpLogConvertModule loadModule(OpLogModule opLogModule, Artifact artifact) throws Exception {
        return buildAndInitModule(opLogModule, artifact);
    }


    /**
     * 卸载模块
     *
     * @param opLogConvertModule
     */
    public void unLoadModule(OpLogConvertModule opLogConvertModule) throws Exception {
        opLogConvertModule.destroy();
    }

    /**
     * 构建并初始化模块
     *
     * @param opLogModule
     * @param artifact
     * @return
     * @throws Exception
     */
    private MavenRepOpLogConvertModule buildAndInitModule(OpLogModule opLogModule, Artifact artifact) throws Exception {
        // 模型信息
        ModuleInfo moduleInfo = ModuleInfo.fromModuleAndArtifact(opLogModule, artifact);

        // 资源信息
        ModuleResource moduleResource = ModuleResource.fromJarFile(artifact.getFile());

        ResourceProvider resourceProvider = new ResourceProvider();

        // 类加载器
        ModelClassLoader modelClassLoader = new ModelClassLoader(artifactJarFileUrl(moduleInfo.getArtifact()));
        // 加载 OpLogHandler
        OpLogHandler opLogHandler = opLogHandler(modelClassLoader);

        // 创建模块
        MavenRepOpLogConvertModule mavenRepOpLogConvertModule =
                new MavenRepOpLogConvertModule(moduleInfo, modelClassLoader,
                        opLogHandler, moduleResource);

        // 初始化模块
        mavenRepOpLogConvertModule.init();

        return mavenRepOpLogConvertModule;

    }


    private URL artifactJarFileUrl(Artifact artifact) {
        try {
            return artifact.getFile().toURI().toURL();
        } catch (MalformedURLException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 通过 ServiceLoader加载 OpLogHandler
     *
     * @param modelClassLoader
     * @return
     */
    private OpLogHandler opLogHandler(ModelClassLoader modelClassLoader) throws IOException {
        ServiceLoader<OpLogHandler> loader =
                ServiceLoader.load(OpLogHandler.class, modelClassLoader);

        Iterator<OpLogHandler> iterator = loader.iterator();
        OpLogHandler handler = null;
        try {
            if (iterator.hasNext()) {
                // 如果找不到类会抛出 ServiceConfigurationError
                handler = iterator.next();
            }
        } catch (Throwable throwable) {
            modelClassLoader.close();
            throw Exceptions.system("加载模块 META-INF/services 配置类异常", throwable);
        }
        if (handler == null) {
            modelClassLoader.close();
            throw Exceptions.business("无法加载到 OpLogHandler 的实现类");
        }
        return handler;
    }

    private void closeClassLoader(ModelClassLoader modelClassLoader) throws IOException {
        modelClassLoader.close();
    }

}