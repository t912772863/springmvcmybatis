package com.tian.springmvcmybatis.controller;

import com.tian.springmvcmybatis.controller.common.ResponseData;
import com.tian.springmvcmybatis.dao.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 所有控制层的基类,封装一些公共的方法等
 * Created by tian on 2016/11/2.
 */
public class BaseController {
    /**
     * 成功返回实例:无业务数据
     */
    protected ResponseData success = ResponseData.successData;
    /**
     * 失败返回实例: 无业务数据
     */
    protected ResponseData failed = ResponseData.failedData;

    /**
     * 成功返回,调用set方法加入业务数据
     */
    protected ResponseData successData = new ResponseData(200,"success");

    protected User getSessionUser(HttpServletRequest request){
        return (User)request.getSession(true).getAttribute("user");
    }

}
