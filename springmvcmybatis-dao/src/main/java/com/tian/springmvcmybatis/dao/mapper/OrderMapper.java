package com.tian.springmvcmybatis.dao.mapper;

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
}