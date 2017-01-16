package com.tian.springmvcmybatis.controller;

import com.tian.springmvcmybatis.controller.common.ResponseData;
import com.tian.springmvcmybatis.dao.entity.Image;
import com.tian.springmvcmybatis.service.IImageService;
import com.tian.springmvcmybatis.service.common.BusinessException;
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
    @Value("${image_local_pre}")
    private String imageLocalPre;
    @Value("${image_tomcat_pre}")
    private String imageTomcatPre;
    @Autowired
    private IImageService imageService;

    /**
     * 单个文件上传
     * @param file
     * @return
     */
    @RequestMapping("upload_file")
    @ResponseBody
    public ResponseData uploadFile(@RequestParam MultipartFile file,String type){
        if(file == null){
            return failedData.setData("文件参数错误");
        }
        // TODO: 2017/1/11 0011 这里通过加一个文件类型的参数,可以拼接不同的路径,使整个系统不同类型的文件放在不同的文件夹下,方便管理
        // 存储图片的物理路径
        String imagePath = this.imageLocalPre+"/image";
        // 拿到上传的文件的名字
        String imageName = file.getOriginalFilename();
        // 重新命名文件名,防止同名冲突
        String newName = System.currentTimeMillis() + imageName.substring(imageName.lastIndexOf("."));
        // 新的文件的全名(包括路径)
        File f = new File(imagePath +"/"+ newName);
        // 存储文件
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(500,"上传文件失败");
        }
        return successData.setData(this.imageTomcatPre + "/image/" + newName);
    }

    /**
     * 在图片表中插入一条记录
     * @return
     */
    @RequestMapping("insert_image")
    @ResponseBody
    public ResponseData insertImage(Image image){
        imageService.insertImage(image);
        return success;
    }
}