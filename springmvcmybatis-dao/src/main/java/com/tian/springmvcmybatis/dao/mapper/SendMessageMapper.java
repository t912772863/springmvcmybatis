package com.tian.springmvcmybatis.dao.mapper;

import com.tian.springmvcmybatis.dao.common.PageParam;
import com.tian.springmvcmybatis.dao.entity.SendMessage;

import java.util.List;

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
}