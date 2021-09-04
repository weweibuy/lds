package com.weweibuy.lds.op.service;

import com.weweibuy.lds.iop.LogParam;
import com.weweibuy.lds.iop.OpLog;
import com.weweibuy.lds.op.core.ModuleDispatch;
import com.weweibuy.lds.op.support.OpLogHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 操作日志转化服务
 *
 * @author durenhao
 * @date 2021/9/2 20:42
 **/
@Service
@RequiredArgsConstructor
public class OpLogConvertService {

    private final ModuleDispatch moduleDispatch;

    public OpLog convert(String logStr) {
        LogParam logParam = OpLogHelper.opLogStrToLogParam(logStr);
        return moduleDispatch.findModule(logParam.getSystemId())
                .map(m -> m.convert(logParam))
                .orElse(null);
    }
}
