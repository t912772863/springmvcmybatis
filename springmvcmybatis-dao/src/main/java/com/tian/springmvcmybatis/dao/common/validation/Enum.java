package com.tian.springmvcmybatis.dao.common.validation;

import java.lang.annotation.*;

/**
 * 枚举校验,用户给定可先值,包含则通过,不包含则不通过
 *  可兼容字符格式的数字可选值
 *  不给默认值,让用户显式定义
 *
 *  注解可以用在哪些类型的参数上: string, 8大基本类型
 * Created by Administrator on 2016/11/24 0024.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Enum {
    /**
     * 可选值数组
     * @return
     */
    String[] enumeration();

    /**
     * 如果是字符串,是否忽略大小写校验
     * @return
     */
    boolean ignoreCase() default false;
}
