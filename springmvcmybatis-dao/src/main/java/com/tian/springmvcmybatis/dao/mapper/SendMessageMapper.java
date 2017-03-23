package com.tian.springmvcmybatis.dao.mapper;

import com.tian.common.other.PageParam;
import com.tian.springmvcmybatis.dao.entity.SendMessage;

import java.util.List;
import java.util.Map;

public interface SendMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SendMessage record);

    SendMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SendMessage record);

    /**
     * 分页查询方法
     * @param pageParam
     * @return
     */
    List<SendMessage> queryByPage(PageParam<SendMessage> pageParam);

    /**
     * 条件查询发送消息
     * @param map
     * @return
     */
    List<SendMessage> queryByRule(Map<String,Object> map);
}