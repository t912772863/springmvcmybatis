package com.tian.springmvcmybatis.service;

import com.tian.common.other.PageParam;
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
     * 测试事务方法(主数据源)
     * @return
     */
    boolean testTranscation();

    /**
     * 测试事务方法(多个数据源)
     * @return
     */
    boolean testTranscation2();

    /**
     * 测试事务方法(从数据源)
     * @return
     */
    boolean testTransaction3();
    /**
     * 分页查询用户信息
     * @param pageParam
     * @return
     */
    PageParam<User> queryUserPage(PageParam<User> pageParam);

    /**
     * 根据用户名和密码查询用户
     * @param userName
     * @param password
     * @return
     */
    User queryUserByUserNameAndPassword(String userName,String password);

    /**
     * 根据手机号查询用户信息
     * @param mobile
     * @return
     */
    User queryUserByMobile(String mobile);
}
