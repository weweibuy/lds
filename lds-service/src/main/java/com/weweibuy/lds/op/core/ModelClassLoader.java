package com.weweibuy.lds.op.core;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author durenhao
 * @date 2021/9/4 16:20
 **/
@Slf4j
public class ModelClassLoader extends ClassLoader {

    private final ResourceProvider resourceProvider;

    public ModelClassLoader(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    // TODO
    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        return super.findClass(name);
    }


    /**
     * 直接从对的模块中获取资源
     *
     * @param name
     * @return
     * @throws IOException
     */
    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        Enumeration<URL> resources = super.getResources(name);
        if (resourceProvider != null) {
            URL providedResource = resourceProvider.getResource(name);
            if (resources != null) {
                return new ResourceProvider.ResourcesEnum(providedResource, resources);
            }
        }
        return resources;
    }


    @Override
    public InputStream getResourceAsStream(String name) {
        if (resourceProvider != null) {
            try {
                InputStream is = resourceProvider.getResourceAsStream(name);
                if (is != null) {
                    return is;
                }
            } catch (IOException e) {
            }
        }
        return super.getResourceAsStream(name);
    }

    @Override
    public URL getResource(String name) {
        URL resource = resourceProvider.getResource(name);
        if (resource != null) {
            return resource;
        }
        return super.getResource(name);
    }


}
