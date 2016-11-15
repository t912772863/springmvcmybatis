package com.tian.springmvcmybatis.dao.mapper;

import com.tian.springmvcmybatis.dao.entity.SystemMenu;

import java.util.List;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMenu record);

    int insertSelective(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemMenu record);

    int updateByPrimaryKey(SystemMenu record);

    /**
     * 查询所有
     * @return
     */
    List<SystemMenu> queryList();
}