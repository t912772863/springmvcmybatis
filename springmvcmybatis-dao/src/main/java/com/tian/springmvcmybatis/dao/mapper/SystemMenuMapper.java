package com.tian.springmvcmybatis.dao.mapper;

import com.tian.common.datasource.DataSource;
import com.tian.springmvcmybatis.dao.entity.SystemMenu;

import java.util.List;

@DataSource("dataSourceMaster")
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
    @DataSource("dataSourceMaster")
    List<SystemMenu> queryList();
}