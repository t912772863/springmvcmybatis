package com.tian.springmvcmybatis.dao.common.validation;

import java.lang.annotation.*;

/**
 * 验证长度
 * Created by Administrator on 2016/11/23 0023.
 *
 * 注解可用在哪些类型的参数上: string,8大基本类型
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Length {
    /**
     * 最小长度
     * @return
     */
    int minLength() default 1;

    /**
     * 最大长度
     * @return
     */
    int maxLength() default 50;

    /**
     * 是否可以为空
     * @return
     */
    boolean nullAble() default true;
}
