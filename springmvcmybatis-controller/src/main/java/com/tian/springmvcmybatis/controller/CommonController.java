package com.tian.springmvcmybatis.controller;

import com.tian.common.other.BusinessException;
import com.tian.common.other.ResponseData;
import com.tian.common.validation.Enum;
import com.tian.springmvcmybatis.service.IFileService;
import com.tian.springmvcmybatis.service.common.InnerConstant;
import com.tian.springmvcmybatis.service.common.OuterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**一些通用的方法入口
 * Created by Administrator on 2017/1/11 0011.
 */
@Controller
@RequestMapping("common")
public class CommonController extends BaseController{
    @Value("${file_local_pre}")
    private String fileLocalPre;
    @Value("${file_tomcat_pre}")
    private String fileTomcatPre;
    @Autowired
    private IFileService fileService;

    /**
     * 单个文件上传
     * @param file
     * @return
     */
    @RequestMapping("upload_file")
    @ResponseBody
    public ResponseData uploadFile(@RequestParam MultipartFile file, String type){
        if(file == null){
            return failedData.setData("文件参数错误");
        }
        // TODO: 2017/1/11 0011 这里通过加一个文件类型的参数,可以拼接不同的路径,使整个系统不同类型的文件放在不同的文件夹下,方便管理

        // 拿到上传的文件的名字
        String fileName = file.getOriginalFilename();
        //拿到文件扩展名
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        // 重新命名文件名,防止同名冲突
        String newName = System.currentTimeMillis() + "."+suffix;
        // 存储文件的物理路径
        String filePath = this.fileLocalPre+"/"+suffix;
        // 查看文件夹是否存在,不存在则先创建
        File temp = new File(filePath);
        if(!temp.exists()){
            temp.mkdirs();
        }

        // 新的文件的全名(包括路径)
        File f = new File(filePath +"/"+ newName);
        // 存储文件
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(500,"上传文件失败");
        }
        return successData.setData(this.fileTomcatPre + "/"+suffix+"/" + newName);
    }

    /**
     * 在文件表中插入一条记录
     * @return
     */
    @RequestMapping("insert_file")
    @ResponseBody
    public ResponseData insertFile(com.tian.springmvcmybatis.dao.entity.File file,@Enum(enumeration = {"IMAGE","VIDEO","OTHER","AUDIO","DOCUMENT"}) String type){
        if(OuterConstant.FILE_IMAGE.equals(type)){
            file.setFileType(InnerConstant.FILE_TYPE_IMAGE);
        }else if(OuterConstant.FILE_DOCUMENT.equals(type)){
            file.setFileType(InnerConstant.FILE_TYPE_DOCUMENT);
        }else if(OuterConstant.FILE_VIDEO.equals(type)){
            file.setFileType(InnerConstant.FILE_TYPE_VIDEO);
        }else if(OuterConstant.FILE_AUDIO.equals(type)){
            file.setFileType(InnerConstant.FILE_TYPE_AUDIO);
        }else if(OuterConstant.FILE_OTHER.equals(type)){
            file.setFileType(InnerConstant.FILE_TYPE_OTHER);
        }
        fileService.insertFile(file);
        return success;
    }

}
