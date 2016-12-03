package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.Order;

import java.util.List;

/**
 * order订单相关业务层
 * Created by Administrator on 2016/12/3 0003.
 */
public interface IOrderService {
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    Order queryOrderById(Long id);

    /**
     * 综合条件查询接口
     * @param userId
     * @return
     */
    List<Order> queryOrderByRule(Long userId);
}
