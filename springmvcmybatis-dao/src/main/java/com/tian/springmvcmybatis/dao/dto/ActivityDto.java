package com.tian.springmvcmybatis.dao.dto;

import java.util.Date;

/**
 * 活动状态变迁时用到的dto
 * Created by Administrator on 2016/12/15 0015.
 */
public class ActivityDto {
    /**
     * id
     */
    private Long id;
    /**
     * 时间
     */
    private Date theTime;
    /**
     * 要修改成的状态
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTheTime() {
        return theTime;
    }

    public void setTheTime(Date theTime) {
        this.theTime = theTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ActivityDto{" +
                "id=" + id +
                ", theTime=" + theTime +
                ", status=" + status +
                '}';
    }
}
