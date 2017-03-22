package com.tian.springmvcmybatis.controller;

import com.tian.springmvcmybatis.service.common.ResponseData;
import com.tian.springmvcmybatis.dao.common.PageParam;
import com.tian.springmvcmybatis.dao.common.validation.Number;
import com.tian.springmvcmybatis.dao.entity.Activity;
import com.tian.springmvcmybatis.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 分页查询活动
     * @param pageParam
     * @param request
     * @return
     */
    @RequestMapping("query_activity_page")
    @ResponseBody
    public ResponseData queryActivityPage(PageParam<Activity> pageParam, HttpServletRequest request){
        pageParam.getParams().put("startTime",request.getParameter("startTime"));
        pageParam.getParams().put("endTime",request.getParameter("endTime"));
        pageParam.getParams().put("name",request.getParameter("name"));
        pageParam.getParams().put("status",request.getParameter("status"));
        activityService.queryActivityPage(pageParam);
        return successData.setData(pageParam);
    }

    /**
     * 根据id查询单个活动详情
    * @param id
     * @return
     */
    @RequestMapping("query_activity_by_id")
    @ResponseBody
    public ResponseData queryActivityById(@Number Long id){
        return successData.setData(activityService.queryById(id));
    }

    /**
     * 根据id更新活动信息
     * @param activity
     * @return
     */
    @RequestMapping("update_activity_by_id")
    @ResponseBody
    public ResponseData updateActivityById(Activity activity){
        activityService.updateActivityById(activity);
        return success;
    }


}
