package com.hyj.demo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hyj.demo.common.enums.Status;
import com.hyj.demo.common.exception.BaseException;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * <p>
 * 通用的 API 接口封装
 * </p>
 */
@Data
public class RestResponse<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回内容
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 无参构造函数
     */
    public RestResponse() {
        this.code = Status.OK.getCode();
        this.message = Status.OK.getMessage();
        this.data = null;
    }

    public RestResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 全参构造函数
     *
     * @param code    状态码
     * @param message 返回内容
     * @param data    返回数据
     */
    private RestResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造一个自定义的API返回
     *
     * @param code    状态码
     * @param message 返回内容
     * @param data    返回数据
     * @return ApiResponse
     */
    public static <T> RestResponse<T> of(Integer code, String message, Object data) {
        return new RestResponse(code, message, data);
    }

    /**
     * 构造一个成功且带数据的API返回
     *
     * @param data 返回数据
     * @return ApiResponse
     */
    public static <T> RestResponse<T> ofSuccess(Object data) {
        return ofStatus(Status.OK, data);
    }
    /**
     * 构造一个成功且带数据的API返回
     *
     * @param data 返回数据
     * @return ApiResponse
     */
    public static <T> RestResponse<T> ofStatus(Object status) {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("status",status.equals(Integer.valueOf(0))?String.valueOf(status).concat("0"):status);
        return ofStatus(Status.OK, objectObjectHashMap);
    }
    /**
     * 构造一个成功且自定义消息的API返回
     *
     * @param message 返回内容
     * @return ApiResponse
     */
    public static <T> RestResponse<T> ofMessage(String message) {
        return of(Status.OK.getCode(), message, null);
    }

    /**
     * 构造一个有状态的API返回
     *
     * @param status 状态 {@link Status}
     * @return ApiResponse
     */
    public static <T> RestResponse<T> ofStatus(Status status) {
        return ofStatus(status, null);
    }

    /**
     * 构造一个有状态且带数据的API返回
     *
     * @param status 状态 {@link Status}
     * @param data   返回数据
     * @return ApiResponse
     */
    public static <T> RestResponse<T> ofStatus(Status status, Object data) {
        return of(status.getCode(), status.getMessage(), data);
    }

    /**
     * 构造一个异常且带数据的API返回
     *
     * @param t    异常
     * @param data 返回数据
     * @param <T>  {@link BaseException} 的子类
     * @return ApiResponse
     */
    public static <T extends BaseException> RestResponse ofException(T t, Object data) {
        return of(t.getCode(), t.getMessage(), data);
    }

    /**
     * 构造一个异常且带数据的API返回
     *
     * @param t   异常
     * @param <T> {@link BaseException} 的子类
     * @return ApiResponse
     */
    public static <T extends BaseException> RestResponse ofException(T t) {
        return ofException(t, null);
    }

    public static <T> RestResponse<T> success() {
        return ofStatus(Status.OK);
    }

    public static <T> RestResponse<T> success(T data) {
        return ofStatus(Status.OK, data);
    }

    public static <T> RestResponse<T> error(Integer code, String message) {
        return of(code, message, null);
    }

    public static <T> RestResponse<T> error(String message,T data,Integer code) {
        return of(Status.UNKNOWN_ERROR.getCode(), message, data);
    }
    public static <T> RestResponse<T> error(String message) {
        return of(Status.UNKNOWN_ERROR.getCode(), message, null);
    }
    public static <T> RestResponse<T> error(Status status) {
        return of(status.getCode(), status.getMessage(), null);
    }

    @JsonIgnore
    public boolean isSuccess() {
        if (null == this.code) {
            return false;
        }
        if (Status.OK.getCode().equals(this.code)) {
            return true;
        }
        return false;
    }
}
