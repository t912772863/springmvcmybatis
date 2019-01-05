package com.tian.springmvcmybatis.service;

import com.tian.common.other.PageParam;
import com.tian.springmvcmybatis.dao.dto.ActivityDetailDto;
import com.tian.springmvcmybatis.dao.dto.ActivityDto;
import com.tian.springmvcmybatis.dao.entity.Activity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15 0015.
 */
public interface IActivityService {
    boolean insertActivity(Activity activity);

    boolean deleteActivityById(Long id);

    boolean updateActivityById(Activity activity);

    /**
     * 查询单个活动的详情
     * @param id
     * @return
     */
    ActivityDetailDto queryById(Long id);

    List<ActivityDto> queryActivityNeedUpdateStatus(String startTime, String endTime);

    /**
     * 分页查询活动
     * @param pageParam
     * @return
     */
    PageParam<Activity> queryActivityPage(PageParam<Activity> pageParam);

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    List<Activity> queryByName(String name);
}
