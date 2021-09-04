package com.weweibuy.lds.op.model.vo;

import com.weweibuy.framework.common.codec.FileEncryptUtils;
import com.weweibuy.framework.common.core.utils.BeanCopyUtils;
import com.weweibuy.lds.op.model.po.OpLogModule;
import lombok.Data;
import org.eclipse.aether.artifact.Artifact;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Objects;

/**
 * 模块信息
 *
 * @author durenhao
 * @date 2021/9/3 22:35
 **/
@Data
public class ModuleInfo {

    /**
     * id
     */
    private Long id;

    /**
     * 系统id
     */
    private String systemId;

    /**
     * 模块 maven artifactId
     */
    private String mvnArtifactId;

    /**
     * 模块 maven version
     */
    private String mvnVersion;

    /**
     * 模块 maven groupId
     */
    private String mvnGroupId;

    /**
     * jar 文件MD5
     */
    private String jarFileMd5;

    private Artifact artifact;

    /**
     * 是否为正式版
     *
     * @return
     */
    public boolean isRelease() {
        return !artifact.isSnapshot();
    }


    public static ModuleInfo fromModuleAndArtifact(OpLogModule opLogModule, Artifact artifact) {
        ModuleInfo moduleInfo = BeanCopyUtils.copy(opLogModule, ModuleInfo.class);
        moduleInfo.setArtifact(artifact);
        try {
            String md5Hex = FileEncryptUtils.md5Hex(artifact.getFile());
            moduleInfo.setJarFileMd5(md5Hex);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return moduleInfo;
    }

    /**
     * release 模块是否发生变化
     *
     * @param opLogModule
     * @return
     */
    public boolean moduleChange(OpLogModule opLogModule) {
        return !(Objects.equals(opLogModule.getMvnGroupId(), mvnGroupId)
                &&
                Objects.equals(opLogModule.getMvnArtifactId(), mvnArtifactId)
                &&
                Objects.equals(opLogModule.getMvnVersion(), mvnVersion));
    }


    public boolean sameMd5(Artifact artifact) {
        String md5Hex = null;
        try {
            md5Hex = FileEncryptUtils.md5Hex(artifact.getFile());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return Objects.equals(md5Hex, jarFileMd5);
    }

}
