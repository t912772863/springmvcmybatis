package com.tian.springmvcmybatis.controller.test;

import com.tian.springmvcmybatis.controller.BaseController;
import com.tian.springmvcmybatis.controller.common.ResponseData;
import com.tian.springmvcmybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试用的一些方法入口
 * Created by Administrator on 2016/11/28 0028.
 */
@Controller("testController")
@RequestMapping("test")
public class TestController extends BaseController{
    @Autowired
    private IUserService userService;
    /**
     * 测试getBean
     * @param request
     * @return
     */
    @RequestMapping("test_get_bean")
    @ResponseBody
    public ResponseData testGetBean(HttpServletRequest request){
        ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        TestController o = (TestController)ac2.getBean("testController");
        System.out.println(o.toString());
        return success;
    }

    /**
     * 测试事务控制(单个数据源)
     * @return
     */
    @RequestMapping("test_trancation")
    @ResponseBody
    public ResponseData testTrancation(){
        userService.testTranscation();
        return success;
    }

    /**
     * 测试事务控制(多个数据源)
     * @return
     */
    @RequestMapping("test_trancation2")
    @ResponseBody
    public ResponseData testTrancation2(){
        userService.testTranscation2();
        return success;
    }

    /**
     * 测试事务控制(从数据源)
     * @return
     */
    @RequestMapping("test_trancation3")
    @ResponseBody
    public ResponseData testTransaction3(){
        userService.testTransaction3();
        return success;
    }

    /**
     * 测试Session相关的一些功能
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("test_session")
    @ResponseBody
    public ResponseData testSession(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getSession());
        System.out.println(request.getSession().getId());
        System.out.println(request.getSession().getAttributeNames());
        System.out.println(request.getSession().toString());
        return successData.setData(request.getSession().getId());
    }

}
