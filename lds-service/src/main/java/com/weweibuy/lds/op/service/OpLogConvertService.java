package com.weweibuy.lds.op.service;

import com.weweibuy.lds.iop.LogParam;
import com.weweibuy.lds.iop.OpLog;
import com.weweibuy.lds.op.support.OpLogHelper;
import org.springframework.stereotype.Service;

/**
 * 操作日志转化服务
 *
 * @author durenhao
 * @date 2021/9/2 20:42
 **/
@Service
public class OpLogConvertService {


    public OpLog convert(String logStr) {
        LogParam logParam = OpLogHelper.opLogStrToLogParam(logStr);

        return null;
    }
}
