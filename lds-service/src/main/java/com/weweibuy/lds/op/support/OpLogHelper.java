package com.weweibuy.lds.op.support;

import com.weweibuy.framework.common.core.utils.DateTimeUtils;
import com.weweibuy.framework.common.core.utils.JackJsonUtils;
import com.weweibuy.lds.iop.LogParam;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * @author durenhao
 * @date 2021/9/2 20:59
 **/
public class OpLogHelper {

    private static final String STR1 = "操作日志: ";
    private static final String STR2 = ", req: ";
    private static final String STR3 = ", resp: ";


    /**
     * 日志字符转化
     *
     * @param str
     * @return
     */
    public static LogParam opLogStrToLogParam(String str) {
        String logDateStr = str.substring(0, 23);
        LocalDateTime logTime = DateTimeUtils.stringToLocalDateTime(logDateStr, "yyyy-MM-dd HH:mm:ss.SSS");
        int i1 = str.indexOf(STR1);
        int i2 = str.indexOf(STR2);
        int i3 = str.indexOf(STR3);

        String reqInfoJson = str.substring(i1 + 6, i2);
        String reqBodyJson = str.substring(i2 + 7, i3);
        String respBodyJson = str.substring(i3 + 7, str.length());

        LogParam logParam = JackJsonUtils.readCamelCaseValue(reqInfoJson, LogParam.class);
        logParam.setLogTime(logTime);

        Map<String, Object> reqMap = Optional.ofNullable(reqBodyJson)
                .filter(StringUtils::isNotBlank)
                .map(s -> JackJsonUtils.readCamelCaseValue(s, Map.class))
                .orElse(Collections.emptyMap());
        logParam.setReqBody(reqMap);

        Map<String, Object> respMap = Optional.ofNullable(respBodyJson)
                .filter(StringUtils::isNotBlank)
                .map(s -> JackJsonUtils.readCamelCaseValue(s, Map.class))
                .orElse(Collections.emptyMap());
        logParam.setRespBody(respMap);

        return logParam;
    }


}
