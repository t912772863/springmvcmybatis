package com.tian.springmvcmybatis.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class Activity implements Serializable {
    private static final long serialVersionUID = 5604464426541526306L;
    private Long id;

    private String name;

    private String address;

    private String remark;

    private Date createTime;

    private Date time1;

    private Date time2;

    private Date time3;

    private Date updateTime;

    private Integer activityStatus;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getTime1() {
        return time1;
    }

    public void setTime1(Date time1) {
        this.time1 = time1;
    }

    public Date getTime2() {
        return time2;
    }

    public void setTime2(Date time2) {
        this.time2 = time2;
    }

    public Date getTime3() {
        return time3;
    }

    public void setTime3(Date time3) {
        this.time3 = time3;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", time1=" + time1 +
                ", time2=" + time2 +
                ", time3=" + time3 +
                ", updateTime=" + updateTime +
                ", activityStatus=" + activityStatus +
                ", status=" + status +
                '}';
    }
}