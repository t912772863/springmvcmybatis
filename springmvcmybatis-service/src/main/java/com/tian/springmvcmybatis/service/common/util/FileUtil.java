package com.tian.springmvcmybatis.service.common.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 与文件相关的工具类
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
     *
     * @param suffix 文件扩展名
     * @return
     */
    public static String getFileType(String suffix) {
        suffix = suffix.toLowerCase();
        if (imageSuffixList.contains(suffix)) {
            return "image";
        }

        if (videoSuffixList.contains(suffix)) {
            return "video";
        }

        if (audioSuffixList.contains(suffix)) {
            return "audio";
        }

        if (documentSuffixList.contains(suffix)) {
            return "document";
        }

        return "other";
    }

    /**读取一个txt文件,返回行数
     *
     * 返回-1表示异常
     * @param filePath
     * @return
     */
    public static int getTxtLineNumber(String filePath) {
        // 先判断是否是txt文件
        if (!filePath.endsWith(".txt")) {
            // 表示异常
            return -1;
        }
        int lines = 0;
        File file = new File(filePath);
        // 得到文件的字节数
        long fileLength = file.length();
        LineNumberReader lineNumberReader = null;
        try {
            lineNumberReader = new LineNumberReader(new FileReader(file));
            if (lineNumberReader != null) {
                // skip用于跳过指定长度的字符,这里相当于相接跳到文档的最后位置
                lineNumberReader.skip(fileLength);
                // 获取当前的行号
                lines = lineNumberReader.getLineNumber();
                lineNumberReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (lineNumberReader != null) {
                try {
                    lineNumberReader.close();
                } catch (IOException ee) {
                }
            }

        }

        return lines;
    }

    public static void main(String[] args) throws Exception{
        int a = getTxtLineNumber("E:/testPhone.txt");
        System.out.println(a);


        FileReader fileReader = new FileReader("E:/testPhone.txt");
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
        // 只跳行号,读的还是第一行
        lineNumberReader.setLineNumber(222);
        // 指定跳过字符,行号也会变
        lineNumberReader.skip(13*222);
        String str222 = lineNumberReader.readLine();
        System.out.println(str222);
    }
}
