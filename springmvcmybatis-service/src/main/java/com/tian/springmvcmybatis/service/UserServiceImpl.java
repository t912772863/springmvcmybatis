package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.common.PageParam;
import com.tian.springmvcmybatis.dao.entity.Role;
import com.tian.springmvcmybatis.dao.entity.User;
import com.tian.springmvcmybatis.dao.mapper.UserMapper;
import com.tian.springmvcmybatis.service.common.BusinessException;
import com.tian.springmvcmybatis.service.common.InnerConstant;
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
    @Autowired
    private IRoleService roleService;

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
            if(new Random().nextBoolean()){
                throw new BusinessException(500,"测试事务,拋出异常");
            }else {
                // 一个空指针异常
                User u = null;
                System.out.println(u.getId());
            }

        }

        return true;
    }
    @Transactional
    public boolean testTranscation2() {
        User user = new User();
        user.setUserName("tset");
        user.setCreateTime(new Date());
        user.setStatus(InnerConstant.DATA_STATUS_COMMON);
        userMapper.insert(user);
        Role role = new Role();
        role.setCreateTime(new Date());
        role.setStatus(InnerConstant.DATA_STATUS_COMMON);
        role.setName("testRole");
        roleService.insertRole(role);
        if(new Random().nextBoolean()){
            throw new BusinessException(500,"测试事务,拋出异常");
        }

        return true;
    }

    public PageParam<User> queryUserPage(PageParam<User> pageParam) {
        userMapper.queryPage(pageParam);
        return pageParam;
    }
}
