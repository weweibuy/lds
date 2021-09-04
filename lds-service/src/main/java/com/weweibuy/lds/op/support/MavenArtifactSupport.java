package com.weweibuy.lds.op.support;

import com.weweibuy.lds.op.model.po.OpLogModule;
import org.appformer.maven.integration.MavenRepository;
import org.appformer.maven.support.AFReleaseId;
import org.appformer.maven.support.AFReleaseIdImpl;
import org.eclipse.aether.artifact.Artifact;

import java.io.IOException;

/**
 * maven 仓库 Artifact
 *
 * @author durenhao
 * @date 2021/9/4 15:18
 **/
public class MavenArtifactSupport {


    /**
     * 加载 Artifact
     *
     * @param afReleaseId
     * @return
     */
    private static Artifact loadArtifact(AFReleaseId afReleaseId) {
        return MavenRepository.getMavenRepository().resolveArtifact(afReleaseId);
    }

    /**
     * 从maven 仓库中加载 Artifact
     *
     * @param opLogModule
     * @return
     * @throws IOException
     */
    public static Artifact loadArtifact(OpLogModule opLogModule) {
        AFReleaseIdImpl afReleaseId = new AFReleaseIdImpl();
        afReleaseId.setGroupId(opLogModule.getMvnGroupId());
        afReleaseId.setArtifactId(opLogModule.getMvnArtifactId());
        afReleaseId.setVersion(opLogModule.getMvnVersion());
        return loadArtifact(afReleaseId);
    }


}
