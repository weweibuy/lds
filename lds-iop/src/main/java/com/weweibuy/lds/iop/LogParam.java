package com.weweibuy.lds.iop;

import lombok.Data;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 日志的一些参数
 *
 * @author durenhao
 * @date 2021/9/1 15:49
 **/
@Data
public class LogParam {

    private String username;

    private String trace;

    private String ip;

    private HttpMethod httpMethod;

    private Integer httpStatus;

    private String path;

    private String host;

    /**
     * 请求参数
     */
    private String param;

    private String systemId;

    private String routerToUri;

    /**
     * 请求时间
     */
    private LocalDateTime reqTime;

    /**
     * 记录时间
     */
    private LocalDateTime logTime;

    /**
     * 请求 body
     */
    private Map<String, Object> reqBody;

    /**
     * 响应body
     */
    private Map<String, Object> respBody;
}
