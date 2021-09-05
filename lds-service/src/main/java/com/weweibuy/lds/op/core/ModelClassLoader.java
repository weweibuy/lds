package com.weweibuy.lds.op.core;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author durenhao
 * @date 2021/9/4 16:20
 **/
@Slf4j
public class ModelClassLoader extends URLClassLoader {

    private final Map<String, Class<?>> loadedClasses = new ConcurrentHashMap<String, Class<?>>();


    public ModelClassLoader(URL... urls) {
        super(urls);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
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
        return super.findResources(name);
    }


}
