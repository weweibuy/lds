package com.weweibuy.lds.op.core;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author durenhao
 * @date 2021/9/4 16:20
 **/
public class ModelClassLoader extends URLClassLoader {

    private final Map<String, Class<?>> loadedClasses = new ConcurrentHashMap<String, Class<?>>();


    public ModelClassLoader(URL... urls) {
        super(urls);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> cls = loadedClasses.get(name);
        if (cls != null) {
            return cls;
        }
        // TODO 类不存在长时间卡死
        cls = super.loadClass(name, resolve);
        return cls;
    }
}
