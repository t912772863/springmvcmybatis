package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.User;
import com.tian.springmvcmybatis.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by tian on 2016/10/12.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    public boolean insertUser(User user) {
        user.setCreateTime(new Date());
        userMapper.insert(user);
        return true;
    }

    public User queryUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
