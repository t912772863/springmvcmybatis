package com.tian.springmvcmybatis.dao.dto;

import com.tian.springmvcmybatis.dao.entity.Activity;

import java.util.List;

/**
 * 活动详情Dto,(基本信息,图片,等)
 * Created by Administrator on 2017/1/12 0012.
 */
public class ActivityDetailDto extends Activity{
    private List<String> images;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
