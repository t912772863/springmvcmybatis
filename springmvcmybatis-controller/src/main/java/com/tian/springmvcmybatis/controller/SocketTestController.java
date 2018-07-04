package com.tian.springmvcmybatis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 测试一些socket的功能
 * Created by Administrator on 2018/6/28 0028.
 */
@RequestMapping("socket_test")
@RestController
@SessionAttributes("userid")
public class SocketTestController extends BaseController{


}
