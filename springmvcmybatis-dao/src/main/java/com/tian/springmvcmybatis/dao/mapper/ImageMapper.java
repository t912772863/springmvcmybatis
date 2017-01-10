package com.tian.springmvcmybatis.dao.mapper;

import com.tian.springmvcmybatis.dao.entity.Image;

public interface ImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);


}