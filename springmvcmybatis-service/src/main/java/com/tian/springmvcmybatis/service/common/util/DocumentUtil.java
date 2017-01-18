package com.tian.springmvcmybatis.service.common.util;

import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文档导出工具类
 * Created by Administrator on 2016/12/3 0003.
 */
public class DocumentUtil {

    /**
     * excel表格的导出
     * @param title 对应表格sheet页上面的标题
     * @param headers 文件第一行的内容标题
     * @param dataList 数据集合
     * @param out httpServletResponse获取到的输出流
     * @param pattern 对于时间格式化的样式
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public static void exportExcel(String title, String[] headers, List dataList, OutputStream out, String pattern) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(title);
        //产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            RichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for(int j=0;j<dataList.size();j++){
            row = sheet.createRow(j+1);
            Class clazz = dataList.get(j).getClass();
            //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                XSSFCell cell = row.createCell(i);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method getMethod = clazz.getMethod(getMethodName, new Class[] {});
                Object value = getMethod.invoke(dataList.get(j), new Object[] {});
                // 如果值 为空,跳 过
                if(value == null){
                    continue;
                }
                //判断值的类型后进行强制类型转换
                String textValue = null;
                if (value instanceof Date) {
                    Date date = (Date) value;
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    textValue = sdf.format(date);
                }  else if (value instanceof byte[]) {
                    XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0,1023, 255, (short) 6, j, (short) 6, j);
                    anchor.setAnchorType(2);
                } else{
                    //其它数据类型都当作字符串简单处理
                    textValue = value.toString();
                }
                //如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                if(textValue!=null){
                    Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                    Matcher matcher = p.matcher(textValue);
                    if(matcher.matches()){
                        //是数字当作double处理
                        cell.setCellValue(Double.parseDouble(textValue));
                    }else{
                        RichTextString richString = new XSSFRichTextString(textValue);
                        cell.setCellValue(richString);
                    }
                }
            }
        }
        workbook.write(out);
    }

}
