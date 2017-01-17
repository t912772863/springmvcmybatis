package com.tian.springmvcmybatis.dao.mapper;

import com.tian.springmvcmybatis.dao.entity.File;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(File record);

    /**
     * 查询与系统中某一条记录关联的图片
     * @param tableName
     * @param dataId
     * @return
     */
    List<File> queryByTableNameAndDataId(@Param("tableName")String tableName,@Param("dataId")Long dataId);
}