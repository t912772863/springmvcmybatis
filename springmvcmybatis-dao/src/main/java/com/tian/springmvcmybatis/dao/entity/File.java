package com.tian.springmvcmybatis.dao.entity;

import java.util.Date;

public class File {
    /** 系统文件ID*/
    private Long id;
    /** 所属记录的主键*/
    private Long dataId;
    /** 属于系统哪张表*/
    private String tableName;
    /** 文件类型: 1图片文件, 2文本文件, 3视频文件, */
    private Integer fileType;
    /** 文件扩展名*/
    private String suffix;
    /** 扩展属性,二级分类*/
    private String extendType;
    /** 图片的相对路径*/
    private String url;
    /** 创建时间*/
    private Date createTime;
    /** 更新时间*/
    private Date updateTime;
    /** 数据状态:-1删除,   1正常*/
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getExtendType() {
        return extendType;
    }

    public void setExtendType(String extendType) {
        this.extendType = extendType == null ? null : extendType.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", dataId=" + dataId +
                ", tableName='" + tableName + '\'' +
                ", fileType=" + fileType +
                ", suffix='" + suffix + '\'' +
                ", extendType='" + extendType + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}