package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.File;
import com.tian.springmvcmybatis.dao.mapper.FileMapper;
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
public class FileServiceImpl implements IFileService {
    @Autowired
    private FileMapper fileMapper;
    public boolean insertFile(File file) {
        file.setCreateTime(new Date());
        file.setStatus(InnerConstant.DATA_STATUS_COMMON);
        fileMapper.insertSelective(file);
        return true;
    }

    public boolean deleteFileById(Long id) {
        fileMapper.deleteByPrimaryKey(id);
        return true;
    }

    public boolean updateFileById(File file) {
        file.setUpdateTime(new Date());
        fileMapper.updateByPrimaryKeySelective(file);
        return true;
    }

    public File queryFileById(Long id) {
        return fileMapper.selectByPrimaryKey(id);
    }

    public List<String> queryFileByTableNameAndDataId(String tableName, Long dataId) {
        List<File> fileList = fileMapper.queryByTableNameAndDataId(tableName,dataId);
        List<String> fileUrlList = new ArrayList<String>();
        for(File i : fileList){
            fileUrlList.add(i.getUrl());
        }
        return fileUrlList;
    }
}
