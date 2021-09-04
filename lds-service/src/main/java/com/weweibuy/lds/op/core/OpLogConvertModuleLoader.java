package com.weweibuy.lds.op.core;

import com.weweibuy.lds.op.model.po.OpLogModule;
import com.weweibuy.lds.op.model.vo.ModuleInfo;
import com.weweibuy.lds.op.support.MavenArtifactSupport;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.aether.artifact.Artifact;
import org.springframework.stereotype.Service;

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
        ModuleInfo moduleInfo = ModuleInfo.fromModuleAndArtifact(opLogModule, artifact);
        MavenRepOpLogConvertModule mavenRepOpLogConvertModule = new MavenRepOpLogConvertModule(moduleInfo);
        mavenRepOpLogConvertModule.init();
        return mavenRepOpLogConvertModule;

    }


}
