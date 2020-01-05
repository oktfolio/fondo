package com.oktfolio.fondo.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/01/06
 */
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求方法名
     */
    private String name;

    /**
     * 日志类型 0 登陆日志 1 操作日志
     */
    private Integer logType;

    /**
     * 请求路径
     */
    private String requestUri;

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求用户
     */
    private String username;

//    @ApiModelProperty(value = "ip")
//    private String ip;
//
//    @ApiModelProperty(value = "ip信息")
//    private String ipInfo;

    /**
     * 花费时间
     */
    private Integer costTime;

}
