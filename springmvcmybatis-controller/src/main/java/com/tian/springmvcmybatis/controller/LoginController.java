package com.tian.springmvcmybatis.controller;

import com.tian.common.other.ResponseData;
import com.tian.common.util.JedisUtil;
import com.tian.common.util.RandomUtil;
import com.tian.common.validation.NotNull;
import com.tian.common.validation.Regular;
import com.tian.springmvcmybatis.dao.entity.User;
import com.tian.springmvcmybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 与登录相关的控制层(其它不需要做登录校验的请求也放这里,方便统一处理)
 * Created by Administrator on 2016/12/13 0013.
 */
@Controller
@RequestMapping("login")
@SessionAttributes("testAttr")
public class LoginController extends BaseController{
    @Autowired
    private IUserService userService;

    /**
     * 跳转到登录页面
     * 所有的跳转页面,直接在页面中用window.location.href实现
     * 这个后端只返回json,不用再处理页面跳转等问题.
     * @return
     */
    @RequestMapping("to_login_view")
    public ModelAndView toLoginView(){
        ModelAndView mav =new ModelAndView("index");
        /*
        默认情况下mav的addObject方法所添加的键值值对, 只在本次请求内有效.
        但是在类上面加了@SessionAttributes("testAttr")
        也就是本类的请求中, 如果有某个方法设置了一个属性名为testAttr的值.那么这个键值对将会被保存
        到request的session对象中的attribute中去.
         */
        mav.addObject("testAttr", "attrValue");
        return mav;
    }

    /**
     * 用户登录接口(用户名,密码)
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public ResponseData login(@NotNull String userName, @NotNull String password, HttpServletRequest request){
        User user = userService.queryUserByUserNameAndPassword(userName,password);
        if(user != null){
            // 用户登录成功,把当前登录用户信息放入Session中管理
            request.getSession().setAttribute("user",user);
            return successData.setData(user);
        }
        return failed.setData("登录失败");
    }

    /**
     * 通过手机号,验证码登录
     * @param mobile
     * @param code
     * @return
     */
    @RequestMapping("login_code")
    @ResponseBody
    public ResponseData loginCode(@Regular("^(1[0-9]{10})$")String mobile,@NotNull String code,HttpServletRequest request){
        String c = JedisUtil.get(mobile);
        if(c != null && c.equals(code)){
            User user = userService.queryUserByMobile(mobile);
            request.getSession().setAttribute("user",user);
            return successData.setData(user);
        }
        return failed.setData("验证码无效");
    }

    /**
     * 模拟获取验证码
     * @return
     */
    @RequestMapping("get_check_code")
    @ResponseBody
    public ResponseData getCheckCode(@Regular("^(1[0-9]{10})$") String mobile){
        String code = RandomUtil.getRandomCode(4,0);
        // 把验证码5213发送到手机上.
        System.out.println("验证码: "+code+" 已经发送到手机 "+ mobile+"上了");
        // 把验证码保存在redis缓存中
        JedisUtil.set(mobile,code,60);
        return successData.setData(code);
    }
}
