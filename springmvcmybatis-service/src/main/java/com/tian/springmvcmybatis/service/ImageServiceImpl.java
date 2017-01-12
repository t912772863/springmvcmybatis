package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.Image;
import com.tian.springmvcmybatis.dao.mapper.ImageMapper;
import com.tian.springmvcmybatis.service.common.InnerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        image.setStatus(InnerConstant.DATA_STATUS_COMMON);
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

    public List<String> queryImageByTableNameAndDataId(String tableName, Long dataId) {
        List<Image> imageList = imageMapper.queryByTableNameAndDataId(tableName,dataId);
        List<String> imageUrlList = new ArrayList<String>();
        for(Image i : imageList){
            imageUrlList.add(i.getUrl());
        }
        return imageUrlList;
    }
}
