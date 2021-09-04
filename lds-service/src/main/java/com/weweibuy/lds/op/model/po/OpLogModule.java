package com.weweibuy.lds.op.model.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OpLogModule {
    /**
     * id
     */
    private Long id;

    /**
     * 系统id
     */
    private String systemId;

    /**
     * 模块 maven artifactId
     */
    private String mvnArtifactId;

    /**
     * 模块 maven version
     */
    private String mvnVersion;

    /**
     * 模块 maven groupId
     */
    private String mvnGroupId;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}