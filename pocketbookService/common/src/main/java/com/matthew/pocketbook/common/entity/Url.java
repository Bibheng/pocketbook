package com.matthew.pocketbook.common.entity;

import lombok.Data;

/**
 * Url实体类
 *
 * @author Matthew
 * @date 2021-02-24 17:45
 **/
@Data
public class Url {
    /**
     * 主键id
     */
    private String urlId;
    /**
     * 方法
     */
    private String method;
    /**
     * url
     */
    private String url;
}
