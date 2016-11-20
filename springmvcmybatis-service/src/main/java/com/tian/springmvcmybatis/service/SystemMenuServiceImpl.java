package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.SystemMenu;
import com.tian.springmvcmybatis.dao.mapper.SystemMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单业务层
 * Created by Administrator on 2016/11/15 0015.
 */
@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;
    /**
     * 查询系统所有的菜单
     * @return
     */
    public List<SystemMenu> querySystemMenuList() {
        List<SystemMenu> list = systemMenuMapper.queryList();
        if(list == null){
            return new ArrayList<SystemMenu>();
        }
        return list;
    }
}
