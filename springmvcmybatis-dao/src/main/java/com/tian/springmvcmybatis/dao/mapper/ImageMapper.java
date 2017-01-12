package com.tian.springmvcmybatis.dao.mapper;

import com.tian.springmvcmybatis.dao.entity.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

    /**
     * 查询与系统中某一条记录关联的图片
     * @param tableName
     * @param dataId
     * @return
     */
    List<Image> queryByTableNameAndDataId(@Param("tableName")String tableName,@Param("dataId")Long dataId);
}