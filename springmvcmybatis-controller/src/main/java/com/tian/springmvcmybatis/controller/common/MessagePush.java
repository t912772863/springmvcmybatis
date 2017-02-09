package com.tian.springmvcmybatis.controller.common;

import com.tian.springmvcmybatis.service.common.ThirdConstant;
import io.goeasy.GoEasy;

/**
 * java后台往页面推送消息
 *
 * 这里主要用的是goeasy的三方服务
 * 网址: https://goeasy.io
 * 用户名,密码: 912772863@qq.com
 * Created by Administrator on 2017/2/9 0009.
 */
public class MessagePush {
    public static void push(String message,String channel){
        GoEasy goEasy = new GoEasy(ThirdConstant.GOEASY_SUPERKEY);
        goEasy.publish(channel, message);
    }
}
