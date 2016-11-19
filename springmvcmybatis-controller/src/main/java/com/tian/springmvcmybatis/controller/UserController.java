package com.tian.springmvcmybatis.controller;

import com.tian.springmvcmybatis.controller.common.ResponseData;
import com.tian.springmvcmybatis.dao.entity.User;
import com.tian.springmvcmybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tian on 2016/10/12.
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    /**
     * 根据主键查询user
     * @param id
     * @return
     */
    @RequestMapping("query_user_by_id")
    public ModelAndView queryUserById(Long id){
        userService.queryUserById(id);
        return new ModelAndView("index");
    }

    /**
     * 新增一个用户
     * @param user
     * @return
     */
    @RequestMapping("insert_user")
    @ResponseBody
    public ResponseData insertUser(User user){
        userService.insertUser(user);
        return successData;
    }

    /**
     * 测试事务控制
     * @return
     */
    @RequestMapping("test_trancation")
    @ResponseBody
    public ResponseData testTrancation(){
        userService.testTranscation();
        return success;
    }

    /**
     * 测试直接返回一个String中文乱码的问题
     * @return
     */
    @RequestMapping("test_luan_ma")
    @ResponseBody
    public String testLuanMa(){
        return "乱码不?";
    }

}
