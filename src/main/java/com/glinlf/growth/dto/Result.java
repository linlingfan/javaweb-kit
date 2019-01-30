package com.glinlf.growth.dto;

import com.glinlf.growth.utils.JsonHelper;

/**
 * @author glinlf
 * @create 2018/1/30
 * 统一响应结果封装
 */
public class Result {

    private Integer code;
    private String message;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JsonHelper.toJson(this);
    }

}
