package com.leemon.mall.common.api;

import com.leemon.mall.mbg.model.PmsBrand;

import java.util.List;

/**
 * @author limenglong
 * @create 2019-08-05 09:57
 * @desc 通用返回对象
 **/
public class CommenResult<T> {

    private long code;
    private String message;
    private T data;

    protected CommenResult(List<PmsBrand> pmsBrands) {

    }

    protected CommenResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommenResult<T> success(T data) {
        return new CommenResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> CommenResult<T> success(T data, String message) {
        return new CommenResult<>(ResultCode.SUCCESS.getCode(), message, data);
    }


    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommenResult<T> failed(String message) {
        return new CommenResult<>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> CommenResult<T> failed(IErrorCode errorCode) {
        return new CommenResult<>(errorCode.getCode(), errorCode.getMessage(), null);
    }


    /**
     * 失败返回结果
     */
    public static <T> CommenResult<T> failed() {
        return new CommenResult<>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), null);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommenResult<T> validateFailed() {
        return new CommenResult<>(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMessage(), null);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommenResult<T> validateFailed(String message) {
        return new CommenResult<>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommenResult<T> unauthorized(T data) {
        return new CommenResult<>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommenResult<T> forbidden(T data) {
        return new CommenResult<>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
