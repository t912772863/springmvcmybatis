package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.User;

/**
 * Created by tian on 2016/10/12.
 */
public interface IUserService {
    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    public User queryUserById(Long id);

    /**
     * 新增用户
     * @param user
     * @return
     */
    public boolean insertUser(User user);

    /**
     * 测试事务方法
     * @return
     */
    boolean testTranscation();
}
