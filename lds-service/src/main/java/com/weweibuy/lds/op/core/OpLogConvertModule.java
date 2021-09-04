package com.weweibuy.lds.op.core;

import com.weweibuy.lds.iop.LogParam;
import com.weweibuy.lds.iop.OpLog;
import com.weweibuy.lds.op.model.vo.ModuleInfo;

/**
 * 操作日志转化模块
 *
 * @author durenhao
 * @date 2021/9/2 21:41
 **/
public interface OpLogConvertModule {

    /**
     * 转化
     *
     * @param logParam
     * @return
     */
    OpLog convert(LogParam logParam);

    /**
     * 模块信息
     *
     * @return
     */
    ModuleInfo moduleInfo();

    /**
     * 初始化模块
     *
     * @throws Exception
     */
    void init() throws Exception;

    /**
     * 销毁方法
     *
     * @throws Exception
     */
    void destroy() throws Exception;


}
