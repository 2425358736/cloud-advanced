package com.cloud.common.core.result;


/**
 * 操作消息提醒
 * 
 * @author
 */
public class AjaxResult<T>
{
    /**
     * 响应状态
     */
    private Integer status;

    /**
     * 响应描述
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;


    public AjaxResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public AjaxResult(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> AjaxResult<T> success() {
        return AjaxResult.success("success");
    }
    public static <T> AjaxResult<T> success(String msg) {
        return new AjaxResult<>(HttpStatus.SUCCESS, msg);
    }

    public static <T> AjaxResult<T> success(String msg, T data ) {
        return new AjaxResult<>(HttpStatus.SUCCESS, msg, data);
    }

    public static <T> AjaxResult<T> successData(T data) {
        return new AjaxResult<>(HttpStatus.SUCCESS, "success", data);
    }

    public static <T> AjaxResult<T> error(String msg) {
        return new AjaxResult<>(HttpStatus.ERROR, msg);
    }

    public static <T> AjaxResult<T> error(Integer status, String msg) {
        return new AjaxResult<>(status, msg);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
