package com.tian.springmvcmybatis.service.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**与文件相关的工具类
 * Created by Administrator on 2016/12/28 0028.
 */
public class FileUtil {
    private static List<String> imageSuffixList = new ArrayList<String>();
    private static List<String> videoSuffixList = new ArrayList<String>();
    private static List<String> audioSuffixList = new ArrayList<String>();
    private static List<String> documentSuffixList = new ArrayList<String>();
    static {
        imageSuffixList.add("jpg");
        imageSuffixList.add("gif");
        imageSuffixList.add("png");

        videoSuffixList.add("mp4");
        videoSuffixList.add("avi");
        videoSuffixList.add("3gp");

        audioSuffixList.add("mp3");

        documentSuffixList.add("txt");
        documentSuffixList.add("xls");
        documentSuffixList.add("xlsx");
        documentSuffixList.add("doc");
        documentSuffixList.add("ppt");
        documentSuffixList.add("pdf");
    }

    /**
     *
     * @param srcfile 文件名数组
     * @param zipfile 压缩后文件
     */
    public static void ZipFiles(java.io.File[] srcfile, java.io.File zipfile) {
        byte[] buf = new byte[1024];
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                    zipfile));
            for (int i = 0; i < srcfile.length; i++) {
                FileInputStream in = new FileInputStream(srcfile[i]);
                out.putNextEntry(new ZipEntry(srcfile[i].getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据文件扩展名,区分文件类型(图片image/视频video/音频audio/文档document/其它other)
     * @param suffix 文件扩展名
     * @return
     */
    public static String getFileType(String suffix){
        suffix = suffix.toLowerCase();
        if(imageSuffixList.contains(suffix)){
            return "image";
        }

        if(videoSuffixList.contains(suffix)){
            return "video";
        }

        if(audioSuffixList.contains(suffix)){
            return "audio";
        }

        if(documentSuffixList.contains(suffix)){
            return "document";
        }

        return "other";
    }
}
