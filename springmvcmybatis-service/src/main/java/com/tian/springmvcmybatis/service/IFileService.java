package com.tian.springmvcmybatis.service;

import com.tian.springmvcmybatis.dao.entity.File;

import java.util.List;

/**
 * 文件相关的业务层
 * Created by Administrator on 2017/1/10 0010.
 */
public interface IFileService {
    /**
     * 新增一条文件记录
     * @param file
     */
    boolean insertFile(File file);

    /**
     * 根据主键删除一条文件记录
     * @param id
     * @return
     */
    boolean deleteFileById(Long id);

    /**
     * 根据主键更新一条文件记录
     * @param file
     * @return
     */
    boolean updateFileById(File file);

    /**
     * 根据主键查询一条文件记录
     * @param id
     * @return
     */
    File queryFileById(Long id);

    /**
     * 查询系统中某一条记录关联的文件
     * @param tableName
     * @param dataId
     * @return
     */
    List<String> queryFileByTableNameAndDataId(String tableName,Long dataId);
}
