package com.tian.springmvcmybatis.service;

import com.tian.common.other.PageParam;
import com.tian.springmvcmybatis.dao.dto.ActivityDetailDto;
import com.tian.springmvcmybatis.dao.dto.ActivityDto;
import com.tian.springmvcmybatis.dao.entity.Activity;
import com.tian.springmvcmybatis.dao.mapper.ActivityMapper;
import com.tian.springmvcmybatis.service.common.InnerConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/15 0015.
 */
@Service
public class ActivityServiceImpl implements IActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private IFileService fileService;

    public boolean insertActivity(Activity activity) {
        activity.setCreateTime(new Date());
        activity.setStatus(InnerConstant.DATA_STATUS_COMMON);
        activity.setActivityStatus(InnerConstant.ACTIVITY_STATUS_ENROLLING);
        activityMapper.insertSelective(activity);
        return true;
    }

    public boolean deleteActivityById(Long id) {
        activityMapper.deleteByPrimaryKey(id);
        return true;
    }

    public boolean updateActivityById(Activity activity) {
        activity.setUpdateTime(new Date());
        activityMapper.updateByPrimaryKeySelective(activity);
        return true;
    }

    public ActivityDetailDto queryById(Long id) {
        Activity activity = activityMapper.selectByPrimaryKey(id);
        if(activity == null){
            return new ActivityDetailDto();
        }
        ActivityDetailDto detailDto = new ActivityDetailDto();
        BeanUtils.copyProperties(activity, detailDto);
        // 查询出活动的图片
        List<String> fileList = fileService.queryFileByTableNameAndDataId("activity", id);
        detailDto.setImages(fileList);
        return detailDto;
    }

    public List<ActivityDto> queryActivityNeedUpdateStatus(String startTime, String endTime) {
        List<ActivityDto> list = activityMapper.queryNeedUpdateStatus(startTime, endTime);
        if (list == null) {
            return new ArrayList<ActivityDto>();
        }
        return list;
    }

    public PageParam<Activity> queryActivityPage(PageParam<Activity> pageParam) {
        List<Activity> list = activityMapper.queryByPage(pageParam);
        pageParam.setResult(list);
        return pageParam;
    }

    public List<Activity> queryByName(String name) {
        return activityMapper.queryByName(name);
    }
}
