package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.Role;

/**
 * 角色对应业务层接口
 * Created by Administrator on 2016/11/17 0017.
 */
public interface IRoleService {
    /**
     * 根据主键查询角色信息
     * @param id
     * @return
     */
    Role queryRoleById(Long id);

    /**
     * 根据主键更新角色信息
     * @param role
     * @return
     */
    boolean updateRoleById(Role role);

    /**
     * 新增一条角色信息
     * @param role
     * @return
     */
    boolean insertRole(Role role);
}
