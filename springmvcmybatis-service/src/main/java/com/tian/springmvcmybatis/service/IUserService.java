package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.User;

/**
 * Created by tian on 2016/10/12.
 */
public interface IUserService {
    public User queryUserById(Long id);
}
