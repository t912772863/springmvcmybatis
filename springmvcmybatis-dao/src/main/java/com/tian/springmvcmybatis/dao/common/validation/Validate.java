package com.tian.springmvcmybatis.dao.common.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 验证方法
 * Created by Administrator on 2016/11/23 0023.
 */
public class Validate {
    /**
     * 注解的有有效性校验统一入口
     * @param a 注解
     * @param o 对应的值
     * @return
     *
     */
    public static boolean verify(Annotation a, Object o) {
        String typeName = a.annotationType().toString();
        String subStr = typeName.substring(typeName.lastIndexOf(".")+1);
        if("Length".equals(subStr)){
            return validateLength(o,(Length)a);
        }else if("NotNull".equals(subStr)){
            return validateNotNull(o,(NotNull)a);
        }else if("Number".equals(subStr)){
            return validateNumber(o,(Number)a);
        }else if("Regular".equals(subStr)){
            return validateRegular(o,(Regular)a);
        }else if("Enum".equals(subStr)){
            return validateEnum(o,(Enum)a);
        }

        return true;
    }



    /**
     * Length注解的校验
     * @param o
     * @param a
     * @return
     */
    private static boolean validateLength(Object o, Length a) {


        // 空值是否合法
        if(o == null || "".equals(o.toString())){
            return a.nullAble();
        }
        String str = o.toString();
        int maxLength = a.maxLength();
        int minLength = a.minLength();
        if(str.length()>maxLength || str.length()<minLength){
            return false;
        }
        return true;
    }

    /**
     * 非空校验:null和空串都返回false
     * @param o
     * @param notNull
     * @return
     */
    private static boolean validateNotNull(Object o,NotNull notNull){
        if(o==null || "".equals(o)){
            return false;
        }
        return true;
    }

    /**
     * 数字值大小范围校验
     * @param o
     * @param a
     * @return
     */
    private static boolean validateNumber(Object o, Number a) {

        // 空值是否合法
        if(o == null || "".equals(o.toString())){
            return a.nullAble();
        }
        int value = Integer.parseInt(o.toString());
        int minValue = a.minValue();
        int maxValue = a.maxValue();
        if(value< minValue || value>maxValue){
            return false;
        }
        return true;
    }

    /**
     * 校验是否符合给定的正则表达式
     * @param o
     * @param a
     * @return
     */
    private static boolean validateRegular(Object o, Regular a) {
        // 空值是否合法
        if(o == null || "".equals(o.toString())){
            return a.nullAble();
        }

        String regular = a.value();
        if("".equals(regular) || regular == null){
            return true;
        }
        Pattern pattern = Pattern.compile(regular);
        if(!(pattern.matcher(o.toString()).find())){
            return false;
        }
        return true;
    }

    /**
     * 校验参数值,是否在给定的可选范围内
     * @param o
     * @param a
     * @return
     */
    private static boolean validateEnum(Object o, Enum a) {
        // 空值是否合法
        if(o == null || "".equals(o.toString())){
            return a.nullAble();
        }
        String[] values = a.enumeration();
        boolean b = a.ignoreCase();
        List<String> valueList = Arrays.asList(values);
        // 先直接匹配,
        if(!valueList.contains(o.toString())){
            // 如果匹配不上,看是否忽略大小写,
            if(b){
                // 如果忽略大小写为trur,则忽略后再匹配,
                for (String s : valueList){
                    if(s.equalsIgnoreCase(o.toString())){
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }


}
