package com.weweibuy.lds.iop;

import lombok.Data;

import java.util.List;

/**
 * 操作日志
 *
 * @author durenhao
 * @date 2021/9/2 20:29
 **/
@Data
public class OpLog {

    /**
     * 用户名
     */
    private String username;

    /**
     * 操作类型
     */
    private String opType;

    /**
     * 操作内容
     */
    private String opContent;

    /**
     * 业务id
     */
    private List<String> bizId;

}
