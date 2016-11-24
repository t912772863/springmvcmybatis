package com.tian.springmvcmybatis.dao.common.validation;

import java.lang.annotation.*;

/**
 * 正则表达式匹配
 * Created by Administrator on 2016/11/24 0024.
 *
 * 注解可用在哪些类型上: string
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Regular {
    String value();

    /**
     * 是否可以为空
     * @return
     */
    boolean nullAble() default true;

}
