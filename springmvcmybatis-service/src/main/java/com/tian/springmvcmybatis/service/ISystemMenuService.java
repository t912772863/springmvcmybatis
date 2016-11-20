package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.SystemMenu;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public interface ISystemMenuService {
    List<SystemMenu> querySystemMenuList();
}
