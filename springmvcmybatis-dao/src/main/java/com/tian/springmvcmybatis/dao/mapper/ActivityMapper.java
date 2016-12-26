package com.tian.springmvcmybatis.dao.mapper;

import com.tian.springmvcmybatis.dao.dto.ActivityDto;
import com.tian.springmvcmybatis.dao.entity.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    /**
     * 查询出所有需要进行状态修改的数据
     * @return
     */
    List<ActivityDto> queryNeedUpdateStatus(@Param("startTime") String startTime, @Param("endTime") String endTime);
}