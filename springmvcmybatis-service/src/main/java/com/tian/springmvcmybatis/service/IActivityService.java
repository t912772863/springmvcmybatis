package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.dto.ActivityDto;
import com.tian.springmvcmybatis.dao.entity.Activity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/15 0015.
 */
public interface IActivityService {
    boolean insertActivity(Activity activity);

    boolean deleteActivityById(Long id);

    boolean updateActivityById(Activity activity);

    Activity queryById(Long id);

    List<ActivityDto> queryActivityNeedUpdateStatus(String startTime, String endTime);
}
