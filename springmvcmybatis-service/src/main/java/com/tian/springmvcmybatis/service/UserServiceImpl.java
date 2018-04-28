package com.tian.springmvcmybatis.service;

import com.tian.common.other.PageParam;
import com.tian.springmvcmybatis.dao.entity.Role;
import com.tian.springmvcmybatis.dao.entity.User;
import com.tian.springmvcmybatis.dao.mapper.UserMapper;
import com.tian.common.other.BusinessException;
import com.tian.springmvcmybatis.service.common.InnerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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
     * 测试事务的方法(主数据源)
     * @return
     */
    @Transactional
    public boolean testTranscation() {
        User user = new User();
        user.setUserName("tset");
        user.setCreateTime(new Date());
        insertUser(user);
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

    /**
     * 测试事务2
     * 默认的注解无法实现,因为无法同时管理两个数据源
     * @return
     */
    @Transactional
    public boolean testTranscation2() {
        User user = new User();
        user.setUserName("tset");
        user.setCreateTime(new Date());
        user.setStatus(InnerConstant.DATA_STATUS_COMMON);
        userMapper.insert(user);
        Role role = new Role();
        role.setStatus(InnerConstant.DATA_STATUS_COMMON);
        role.setName("testRole");
        roleService.insertRole(role);
        if(new Random().nextBoolean()){
            throw new BusinessException(500,"测试事务,拋出异常");
        }

        return true;
    }

    /**
     * 测试数据源3(从数据源)
     * 结论, 先入库, 再查询, 虽然后面切换了数据源, 如果出现异常, 前面的入库仍然可以回滚.
     * @return
     */
    @Transactional
    public boolean testTransaction3(){
        Role role = new Role();
        role.setCreateTime(new Date());
        role.setStatus(InnerConstant.DATA_STATUS_COMMON);
        role.setName("testRole");
        // 先做一次插入,
        roleService.insertRole(role);
        // 再做一次查询, 因为实现了读写分离, 所以查询的时候,数据源切换了, 看看上面的插入事务是否还有效
        User user = queryUserById(1L);
        System.out.println(user.toString());
        if(new Random().nextBoolean()){
            throw new BusinessException(500,"测试事务,拋出异常");
        }
        return true;
    }

    /**
     * 测试两次插入不同的数据源中, 是否可以同时管理事务
     *
     * 结论,按照普通的事务配置方法, 是无法管理两个数据源的事务的, 默认只能管理配置的默认数据源
     * @return
     */
    @Transactional
    public boolean testTransaction4(){
        // 在切面中会对下面两个插入方法,切换到不同的数据源, 当出现异常的时候, 查看两个事务是否都可以回滚.
        Role role = new Role();
        role.setCreateTime(new Date());
        role.setStatus(InnerConstant.DATA_STATUS_COMMON);
        role.setName("testRole");
        // 先做一次插入,
        roleService.insertRole(role);
        User user = new User();
        user.setUserName("tset");
        user.setCreateTime(new Date());
        user.setStatus(InnerConstant.DATA_STATUS_COMMON);
        userMapper.insert(user);
        if(new Random().nextBoolean()){
            throw new BusinessException(500,"测试事务,拋出异常");
        }
        return true;
    }

    public PageParam<User> queryUserPage(PageParam<User> pageParam) {
        List<User> userList = userMapper.queryByPage(pageParam);
        pageParam.setResult(userList);
        return pageParam;
    }

    public User queryUserByUserNameAndPassword(String userName, String password) {
        return userMapper.queryByUserNameAndPassword(userName,password);
    }

    public User queryUserByMobile(String mobile) {
        return userMapper.queryByMobile(mobile);
    }
}
