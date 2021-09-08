package com.weweibuy.lds.op.core;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;

/**
 * 资源提供器
 *
 * @author durenhao
 * @date 2021/9/3 22:43
 **/
class ResourceProvider {

    /**
     * jar 包地址
     */
    private final URL moduleUrl;

    /**
     * JAR 包资源信息
     */
    private final ModuleResource moduleResource;

    ResourceProvider(URL moduleUrl, ModuleResource moduleResource) {
        this.moduleUrl = moduleUrl;
        this.moduleResource = moduleResource;
    }

    public InputStream getResourceAsStream(String name) throws IOException {
        if (name.endsWith("/")) {
            name = name.substring(0, name.length() - 1);
        }
        return moduleResource.getResourceInputStream(name)
                .orElse(null);
    }

    public URL getResource(String name) {
        if (name.endsWith("/")) {
            name = name.substring(0, name.length() - 1);
        }
        boolean present = moduleResource.getResourceByte(name)
                .filter(ArrayUtils::isNotEmpty)
                .isPresent();
        return present ? createURLForResource(name) : null;
    }

    /**
     * 创建资源地址
     *
     * @param name
     * @return
     */
    private URL createURLForResource(String name) {
        try {
            return new URL("jar", "", moduleUrl + "!/" + name);
        } catch (MalformedURLException e) {
            return null;
        }
    }


    public Map<String, byte[]> getClassesMap() {
        return moduleResource.getClassesMap();
    }


    /**
     * 自定义资源实现, 先使用当前包下的资源, 在使用项目本身的资源
     */
    static class ResourcesEnum implements Enumeration<URL> {

        private URL providedResource;
        private final Enumeration<URL> resources;

        ResourcesEnum(URL providedResource, Enumeration<URL> resources) {
            this.providedResource = providedResource;
            this.resources = resources;
        }

        @Override
        public boolean hasMoreElements() {
            return providedResource != null || resources.hasMoreElements();
        }

        @Override
        public URL nextElement() {
            if (providedResource != null) {
                URL result = providedResource;
                providedResource = null;
                return result;
            }
            return resources.nextElement();
        }
    }

}