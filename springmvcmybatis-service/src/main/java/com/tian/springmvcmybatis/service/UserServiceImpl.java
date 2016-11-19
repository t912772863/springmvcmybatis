package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.User;
import com.tian.springmvcmybatis.dao.mapper.UserMapper;
import com.tian.springmvcmybatis.service.common.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

/**
 * Created by tian on 2016/10/12.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ISystemMenuService systemMenuService;

    public boolean insertUser(User user) {
        user.setCreateTime(new Date());
        userMapper.insert(user);
        return true;
    }

    public User queryUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 测试事务的方法
     * @return
     */
    @Transactional
    public boolean testTranscation() {
        User user = new User();
        user.setUserName("tset");
        user.setCreateTime(new Date());
        userMapper.insert(user);
        if(new Random().nextBoolean()){
            throw new BusinessException(500,"测试事务,拋出异常");
        }

        return true;
    }
}
