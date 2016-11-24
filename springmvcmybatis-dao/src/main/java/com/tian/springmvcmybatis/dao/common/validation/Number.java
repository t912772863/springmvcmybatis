package com.tian.springmvcmybatis.dao.common.validation;

import java.lang.annotation.*;

/**
 * 值的大小范围校验
 * Created by Administrator on 2016/11/24 0024.
 *
 * 注解可用在哪些类型上: 所有数字类型int ,和其对应的包装类型
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Number {
    int minValue() default -100000000;
    int maxValue() default 100000000;

    /**
     * 是否可以为空
     * @return
     */
    boolean nullAble() default true;
}
