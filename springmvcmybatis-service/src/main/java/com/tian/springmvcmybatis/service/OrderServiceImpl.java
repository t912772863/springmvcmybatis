package com.tian.springmvcmybatis.service;

import com.tian.common.other.PageParam;
import com.tian.springmvcmybatis.dao.entity.Order;
import com.tian.springmvcmybatis.dao.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    public Order queryOrderById(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    public List<Order> queryOrderByRule(Long userId) {
        List<Order> list = orderMapper.queryByRule(userId);
        if(list == null){
            return new ArrayList<Order>();
        }
        return list;
    }

    public PageParam<Order> queryOrderByPage(PageParam<Order> pageParam) {
        List<Order> list = orderMapper.queryByPage(pageParam);
        pageParam.setResult(list);
        return pageParam;
    }
}
