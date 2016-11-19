package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.Role;

/**
 * 角色对应业务层接口
 * Created by Administrator on 2016/11/17 0017.
 */
public interface IRoleService {
    Role queryRoleById(Long id);

    boolean updateRoleById(Role role);
}
