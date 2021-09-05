package com.weweibuy.lds.op;

import com.weweibuy.lds.iop.LogParam;
import com.weweibuy.lds.iop.OpLog;
import com.weweibuy.lds.iop.OpLogHandler;

/**
 * @author durenhao
 * @date 2021/9/4 19:56
 **/
public class DemoOpLogHandler implements OpLogHandler {

    @Override
    public void init() throws Exception {

    }

    @Override
    public OpLog handlerGwOpLog(LogParam logParam) {
        return null;
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

    }
}
