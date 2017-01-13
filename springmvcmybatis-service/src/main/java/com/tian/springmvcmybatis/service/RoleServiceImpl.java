package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.Role;
import com.tian.springmvcmybatis.dao.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据主键更新数据
     * @param role
     * @return
     */
    public boolean updateRoleById(Role role) {
        role.setUpdateTime(new Date());
        roleMapper.updateByPrimaryKey(role);
        return true;
    }

    /**
     * 根据主键查询角色信息
     * @param id
     * @return
     */
    public Role queryRoleById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public boolean insertRole(Role role) {
        roleMapper.insertSelective(role);
        return true;
    }
}
