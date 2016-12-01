package com.tian.springmvcmybatis.dao.mapper;

import com.tian.springmvcmybatis.dao.common.PageParam;
import com.tian.springmvcmybatis.dao.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 分页查询用户信息
     * @param pageParam
     */
    List<User> queryPage(PageParam<User> pageParam);

    /**
     * 分页查询时统计总数
     * @param pageParam
     * @return
     */
    int queryPageCount(PageParam<User> pageParam);

    /**
     * 根据用户名和密码查询用户
     * @param userName
     * @param password
     * @return
     */
    User queryByUserNameAndPassword(@Param("userName")String userName,@Param("password")String password);
}