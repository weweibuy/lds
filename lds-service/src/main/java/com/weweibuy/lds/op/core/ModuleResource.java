package com.weweibuy.lds.op.core;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author durenhao
 * @date 2021/9/6 22:58
 **/
class ModuleResource {

    private File file;

    private List<String> fileNames;

    private Map<String, byte[]> zipEntries;

    /**
     * 获取指定资源流
     *
     * @param name
     * @return
     */
    public Optional<InputStream> getResourceInputStream(String name) {
        return getResourceByte(name)
                .map(ByteArrayInputStream::new);
    }

    /**
     * 获取指定资源 byte数组
     *
     * @param name
     * @return
     */
    public Optional<byte[]> getResourceByte(String name) {
        return Optional.ofNullable(zipEntries.get(name));
    }

    public static ModuleResource fromJarFile(File jarFile) {
        ModuleResource moduleResource = new ModuleResource();
        moduleResource.indexZipFile(jarFile);
        return moduleResource;
    }

    private void indexZipFile(File jarFile) {
        Map<String, List<String>> folders = new HashMap<String, List<String>>();
        zipEntries = new HashMap<String, byte[]>();
        fileNames = new ArrayList<String>();

        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(jarFile);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".dex")) {
                    continue; //avoid out of memory error, it is useless anyway
                }
                String entryName = entry.getName();
                if (entry.isDirectory()) {
                    if (entryName.endsWith("/")) {
                        entryName = entryName.substring(0, entryName.length() - 1);
                    }
                } else {
                    ;
                    byte[] bytes = IOUtils.toByteArray(zipFile.getInputStream(entry));
                    zipEntries.put(entryName, bytes);
                    fileNames.add(entryName);
                }
                int lastSlashPos = entryName.lastIndexOf('/');
                String folderName = lastSlashPos < 0 ? "" : entryName.substring(0, lastSlashPos);
                List<String> folder = folders.get(folderName);
                if (folder == null) {
                    folder = new ArrayList<String>();
                    folders.put(folderName, folder);
                }
                folder.add(lastSlashPos < 0 ? entryName : entryName.substring(lastSlashPos + 1));
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to get all ZipFile entries: " + jarFile, e);
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    throw new RuntimeException("Unable to get all ZipFile entries: " + jarFile, e);
                }
            }
        }

        for (Map.Entry<String, List<String>> folder : folders.entrySet()) {
            StringBuilder sb = new StringBuilder();
            for (String child : folder.getValue()) {
                sb.append(child).append("\n");
            }
            zipEntries.put(folder.getKey(), sb.toString().getBytes(StandardCharsets.UTF_8));
        }
    }

}
