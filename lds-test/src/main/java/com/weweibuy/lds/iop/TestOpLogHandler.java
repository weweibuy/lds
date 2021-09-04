package com.weweibuy.lds.iop;

import lombok.extern.slf4j.Slf4j;

/**
 * @author durenhao
 * @date 2021/9/4 17:50
 **/
@Slf4j
public class TestOpLogHandler implements OpLogHandler {


    @Override
    public void init() throws Exception {
        log.info("初始化");
    }

    @Override
    public OpLog handlerGwOpLog(LogParam logParam) {
        log.info("操作日志参数: {}", logParam);
        OpLog opLog = new OpLog();
        opLog.setOpType("test1");
        return opLog;
    }

    @Override
    public OpLog handlerHttpOpLog(LogParam logParam) {
        return null;
    }

    @Override
    public OpLog handlerMqOpLog(LogParam logParam) {
        return null;
    }

    @Override
    public void destroy() throws Exception {
        log.info("销毁");
    }
}
