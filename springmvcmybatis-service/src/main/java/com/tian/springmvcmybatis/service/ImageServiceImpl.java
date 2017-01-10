package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.Image;
import com.tian.springmvcmybatis.dao.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 图片业务层实现类
 * Created by Administrator on 2017/1/10 0010.
 */
@Service
public class ImageServiceImpl implements IImageService{
    @Autowired
    private ImageMapper imageMapper;
    public boolean insertImage(Image image) {
        image.setCreateTime(new Date());
        imageMapper.insertSelective(image);
        return true;
    }

    public boolean deleteImageById(Long id) {
        imageMapper.deleteByPrimaryKey(id);
        return true;
    }

    public boolean updateImageById(Image image) {
        image.setUpdateTime(new Date());
        imageMapper.updateByPrimaryKeySelective(image);
        return true;
    }

    public Image queryImageById(Long id) {
        return imageMapper.selectByPrimaryKey(id);
    }
}
