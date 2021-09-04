package com.weweibuy.lds.op.controller;

import com.weweibuy.framework.common.core.model.dto.CommonCodeResponse;
import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import com.weweibuy.lds.iop.OpLog;
import com.weweibuy.lds.op.service.OpLogConvertService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author durenhao
 * @date 2021/9/2 20:40
 **/
@RestController
@RequestMapping("/op-log")
@RequiredArgsConstructor
public class OpLogController {

    private final OpLogConvertService opLogConvertService;

    @PostMapping("/convert")
    public CommonDataResponse<OpLog> tryConvert(String logStr) {
        if (StringUtils.isBlank(logStr)) {
            return CommonDataResponse.success(null);
        }
        return CommonDataResponse.success(opLogConvertService.convert(logStr));
    }

    @PostMapping("/scan")
    public CommonCodeResponse scan() {
        opLogConvertService.scan();
        return CommonDataResponse.success();
    }


}
