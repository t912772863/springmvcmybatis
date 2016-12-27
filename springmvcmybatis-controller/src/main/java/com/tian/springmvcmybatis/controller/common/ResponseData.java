package com.tian.springmvcmybatis.controller.common;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回到页面的数据封装
 * Created by tian on 2016/11/2.
 */
public class ResponseData {
    /**
     * 一个成功返回的实例
     */
    public static ResponseData successData = new ResponseData(200,"success");
    /**
     * 一个失败返回的实例
     */
    public static ResponseData failedData = new ResponseData(500,"failed");

    public ResponseData(int code,String message){
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public ResponseData setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
