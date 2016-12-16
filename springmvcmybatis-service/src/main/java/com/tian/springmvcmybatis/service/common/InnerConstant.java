package com.tian.springmvcmybatis.service.common;

/**
 * 系统内部常量
 * Created by Administrator on 2016/11/19 0019.
 */
public class InnerConstant {
    /**-------------------------------------------系统中所有表的数据状态----------------------------------------------------------------*/
    /**
     * 数据状态: 正常
     */
    public static final int DATA_STATUS_COMMON = 1;
    /**
     * 数据状态: 删除
     */
    public static final int DATA_STATUS_DELETE = -1;

    /**-------------------------------------------------------活动状态----------------------------------------------------------------*/

    /**
     * 活动状态: 新建的,报名中
     */
    public static final int ACTIVITY_STATUS_ENROLLING = 0;
    /**
     * 活动状态: 报名结束了,准备中,等待开始
     */
    public static final int ACTIVITY_STATUS_WAITOPEN = 1;
    /**
     * 活动状态: 活动进行中
     */
    public static final int ACTIVITY_STATUS_DOING = 2;
    /**
     * 活动状态: 活动结束了
     */
    public static final int ACTIVITY_STATUS_OVER = 3;

}
