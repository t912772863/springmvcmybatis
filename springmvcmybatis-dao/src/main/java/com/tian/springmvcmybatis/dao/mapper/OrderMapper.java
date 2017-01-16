package com.tian.springmvcmybatis.dao.mapper;

import com.tian.springmvcmybatis.dao.common.PageParam;
import com.tian.springmvcmybatis.dao.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     * 综合查询订单接口
     * @param userId
     * @return
     */
    List<Order> queryByRule(@Param("userId") Long userId);

    /**
     * 分页查询订单信息
     * @param pageParam
     * @return
     */
    List<Order> queryByPage(PageParam<Order> pageParam);
}