package com.hyj.demo.common.enums;

import lombok.Getter;

@Getter
public enum Status {
    /**
     * 操作成功
     */
    OK(0, "操作成功"),
    /**
     * 入参错误
     */
    PARAMS_ERROR(406, "传参错误"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR(500, "服务器出错啦"),

    /**
     * 状态码
     *用户信息失效,请重新登录
     */
    BIZ_ERROR(9999,"业务异常"),

    ;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 内容
     */
    private String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
