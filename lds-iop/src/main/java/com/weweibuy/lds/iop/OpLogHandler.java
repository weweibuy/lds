package com.weweibuy.lds.iop;

/**
 * opLog 处理
 *
 * @author durenhao
 * @date 2021/9/2 20:23
 **/
public interface OpLogHandler {

    /**
     * 初始化方法
     *
     * @throws Exception
     */
    void init() throws Exception;

    /**
     * 处理网关操作日志
     *
     * @param logParam
     * @return
     */
    OpLog handlerGwOpLog(LogParam logParam);

    /**
     * 处理接口日志
     *
     * @param logParam
     * @return
     */
    OpLog handlerHttpOpLog(LogParam logParam);

    /**
     * 处理Mq日志
     *
     * @param logParam
     * @return
     */
    OpLog handlerMqOpLog(LogParam logParam);

    /**
     * 销毁方法
     *
     * @throws Exception
     */
    void destroy() throws Exception;

}
