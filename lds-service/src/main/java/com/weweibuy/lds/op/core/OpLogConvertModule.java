package com.weweibuy.lds.op.core;

import com.weweibuy.lds.iop.LogParam;
import com.weweibuy.lds.iop.OpLog;

/**
 * 操作日志转化模块
 *
 * @author durenhao
 * @date 2021/9/2 21:41
 **/
public interface OpLogConvertModule {

    /**
     * 匹配
     *
     * @param systemId
     * @return
     */
    boolean match(String systemId);

    /**
     * 转化
     *
     * @param logParam
     * @return
     */
    OpLog convert(LogParam logParam);


}
