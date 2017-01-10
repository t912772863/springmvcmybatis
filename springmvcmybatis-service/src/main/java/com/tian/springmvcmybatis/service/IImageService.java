package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.Image;

/**
 * 图片相关的业务层
 * Created by Administrator on 2017/1/10 0010.
 */
public interface IImageService {
    /**
     * 新增一条图片记录
     * @param image
     */
    boolean insertImage(Image image);

    /**
     * 根据主键删除一条图片记录
     * @param id
     * @return
     */
    boolean deleteImageById(Long id);

    /**
     * 根据主键更新一条图片记录
     * @param image
     * @return
     */
    boolean updateImageById(Image image);

    /**
     * 根据主键查询一条图片记录
     * @param id
     * @return
     */
    Image queryImageById(Long id);
}
