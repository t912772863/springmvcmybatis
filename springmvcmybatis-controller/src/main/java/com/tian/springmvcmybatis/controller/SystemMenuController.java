package com.tian.springmvcmybatis.controller;

import com.tian.springmvcmybatis.controller.common.ResponseData;
import com.tian.springmvcmybatis.dao.entity.SystemMenu;
import com.tian.springmvcmybatis.service.ISystemMenuService;
import com.tian.springmvcmybatis.service.common.SystemInitData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统菜单控制层
 * Created by Administrator on 2016/11/15 0015.
 */
@Controller
@RequestMapping("system_menu")
public class SystemMenuController extends BaseController{
    @Autowired
    private ISystemMenuService systemMenuService;

    /**
     * 查询系统所有的菜单
     * @return
     */
    @RequestMapping("query_system_menu")
    @ResponseBody
    public ResponseData querySystemMenu(){
        List<SystemMenu> list = SystemInitData.menuList;
        return successData.setData(list);
    }
}
