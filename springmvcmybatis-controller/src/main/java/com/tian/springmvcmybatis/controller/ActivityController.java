package com.tian.springmvcmybatis.controller;

import com.tian.springmvcmybatis.controller.common.ResponseData;
import com.tian.springmvcmybatis.dao.entity.Activity;
import com.tian.springmvcmybatis.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 活动控制层
 * Created by Administrator on 2016/12/15 0015.
 */
@Controller
@RequestMapping("activity")
public class ActivityController extends BaseController {
    @Autowired
    private IActivityService activityService;

    /**
     * 新增一个活动
     * @param activity
     */
    @RequestMapping("insert_activity")
    @ResponseBody
    public ResponseData insertActivity(Activity activity){
        activityService.insertActivity(activity);
        return success;
    }
}
