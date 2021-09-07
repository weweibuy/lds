package com.weweibuy.lds.op.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author durenhao
 * @date 2021/9/4 16:20
 **/
@Slf4j
public class ModelClassLoader extends ClassLoader {

    private final ResourceProvider resourceProvider;

    private Map<String, byte[]> classNameStore;


    public ModelClassLoader(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
        initClassNameStore();
    }

    private void initClassNameStore() {
        Map<String, byte[]> classesMap = resourceProvider.getClassesMap();
        storeClasses(classesMap);
    }

    public void storeClasses(Map<String, byte[]> classesMap) {
        for (Map.Entry<String, byte[]> entry : classesMap.entrySet()) {
            if (entry.getValue() != null) {
                String resourceName = entry.getKey();
                String className = convertResourceToClassName(resourceName);
                storeClass(className, resourceName, entry.getValue());
            }
        }
    }

    public void storeClass(String name, String resourceName, byte[] bytecode) {
        if (classNameStore == null) {
            classNameStore = new HashMap<String, byte[]>();
        }
        classNameStore.put(resourceName, bytecode);
    }


    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        byte[] bytecode = classNameStore.get(convertClassToResourcePath(name));
        if (ArrayUtils.isEmpty(bytecode)) {
            throw new ClassNotFoundException(name);
        }
        return defineClass(name, bytecode);
    }


    public synchronized Class<?> defineClass(String name, byte[] bytecode) {
        int lastDot = name.lastIndexOf('.');
        if (lastDot > 0) {
            String pkgName = name.substring(0, lastDot);
            if (getPackage(pkgName) == null) {
                definePackage(pkgName, "", "", "", "", "", "", null);
            }
        }
        return defineClass(name, bytecode, 0, bytecode.length);
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


    static String convertResourceToClassName(final String pResourceName) {
        return stripExtension(pResourceName).replace('/', '.');
    }

    static String stripExtension(final String pResourceName) {
        final int i = pResourceName.lastIndexOf('.');
        return pResourceName.substring(0, i);
    }

    static String convertClassToResourcePath(final String pName) {
        return pName.replace('.',
                '/') + ".class";
    }


}
