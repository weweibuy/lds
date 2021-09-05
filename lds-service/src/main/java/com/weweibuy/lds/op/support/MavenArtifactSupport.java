package com.weweibuy.lds.op.support;

import com.weweibuy.framework.common.core.exception.Exceptions;
import com.weweibuy.lds.op.model.po.OpLogModule;
import org.appformer.maven.integration.MavenRepository;
import org.appformer.maven.support.AFReleaseId;
import org.appformer.maven.support.AFReleaseIdImpl;
import org.eclipse.aether.artifact.Artifact;
import sun.misc.URLClassPath;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
        Artifact artifact = loadArtifact(afReleaseId);
        try {
            checkFile(artifact.getFile());
        } catch (IOException e) {
            throw Exceptions.uncheckedIO(e);
        }
        return artifact;
    }

    public static void checkFile(File file) throws IOException {
        URL url = file.toURI().toURL();

        ZipFile zipFile = new ZipFile(file);
        ZipEntry entry = zipFile.getEntry("META-INF/services/com.weweibuy.lds.iop.OpLogHandler");
        InputStream inputStream1 = zipFile.getInputStream(entry);
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1, "utf-8"));
        String s = bufferedReader1.readLine();
        System.err.println(s);

        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        AccessControlContext context = AccessController.getContext();
        URL[] urls = new URL[1];
        urls[0] = url;
        URLClassPath urlClassPath = new URLClassPath(urls, context);

        URL resource = urlClassPath.findResource("META-INF/services/com.weweibuy.lds.iop.OpLogHandler",
                true);

        URLConnection urlConnection =
                resource.openConnection();
        urlConnection.setUseCaches(false);


        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        String ln = bufferedReader.readLine();
        System.err.println(ln);
        inputStream.close();
    }

}
