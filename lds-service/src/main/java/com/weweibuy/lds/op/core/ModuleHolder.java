package com.weweibuy.lds.op.core;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author durenhao
 * @date 2021/9/4 9:20
 **/
@Component
public class ModuleHolder {

    private final Map<String, OpLogConvertModule> moduleMap = new ConcurrentHashMap<>(32);


    /**
     * 获取当前的模块
     *
     * @return
     */
    public Map<String, OpLogConvertModule> currentModule() {
        return Collections.unmodifiableMap(moduleMap);
    }

    /**
     * 查找模块
     *
     * @param systemId
     * @return
     */
    public Optional<OpLogConvertModule> findModule(String systemId) {
        return Optional.ofNullable(moduleMap.get(systemId));
    }

    /**
     * 增加模块
     *
     * @param systemId
     * @param module
     * @return 如果模块以及存在返回之前的模块
     */
    public synchronized OpLogConvertModule addModule(String systemId, OpLogConvertModule module) {
        return moduleMap.put(systemId, module);
    }

    public void removeModule(String systemId) {
        moduleMap.remove(systemId);
    }

}
