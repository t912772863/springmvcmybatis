package com.tian.springmvcmybatis.service.common;

import com.tian.springmvcmybatis.dao.entity.SystemMenu;
import com.tian.springmvcmybatis.service.ISystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统中一些不会改变,直接初始化的值
 * Created by Administrator on 2016/11/28 0028.
 */
@Component
public class SystemInitData {
    public static List<SystemMenu> menuList = new ArrayList<SystemMenu>();

    @Autowired
    private ISystemMenuService systemMenuService;

    @PostConstruct
    private void initData(){
        menuList = systemMenuService.querySystemMenuList();
    }
}
