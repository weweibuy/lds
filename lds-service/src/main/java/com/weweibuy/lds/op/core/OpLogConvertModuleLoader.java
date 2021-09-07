package com.weweibuy.lds.op.core;

import com.weweibuy.framework.common.core.exception.Exceptions;
import com.weweibuy.lds.iop.OpLogHandler;
import com.weweibuy.lds.op.model.po.OpLogModule;
import com.weweibuy.lds.op.model.vo.ModuleInfo;
import com.weweibuy.lds.op.support.MavenArtifactSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.aether.artifact.Artifact;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * 模块加载器
 *
 * @author durenhao
 * @date 2021/9/3 22:27
 **/
@Slf4j
@Service
public class OpLogConvertModuleLoader {

    private static final String CONF_FILE = "META-INF/op.conf";

    private static final String LAUNCHER_CLASS = "launcher";


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

        ResourceProvider resourceProvider = new ResourceProvider(
                moduleInfo.getArtifact().getFile().toURI().toURL(), moduleResource);

        // 类加载器
        ModelClassLoader modelClassLoader = new ModelClassLoader(resourceProvider);
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
    private OpLogHandler opLogHandler(ModelClassLoader modelClassLoader) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        Properties properties = new Properties();
        try (InputStream resourceAsStream = modelClassLoader.getResourceAsStream(CONF_FILE)) {
            if (resourceAsStream == null) {
                throw Exceptions.system(CONF_FILE + "META-INF 配置不存在");
            }
            properties.load(resourceAsStream);
        }
        String launcher = (String) properties.get("launcher");
        if (StringUtils.isBlank(launcher)) {
            throw Exceptions.system("加载模块 " + CONF_FILE + " 配置类异常");
        }

        Class<?> clazz = Class.forName(launcher, false, modelClassLoader);

        OpLogHandler handler = OpLogHandler.class.cast(clazz.newInstance());

        if (handler == null) {
            throw Exceptions.business("无法加载到 OpLogHandler 的实现类");
        }
        return handler;
    }


}