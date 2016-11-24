package com.tian.springmvcmybatis.dao.common.validation;

import java.lang.annotation.*;

/**
 * 验证非空,非空串
 * Created by Administrator on 2016/11/24 0024.
 *
 * 注解可用在哪些类型上: 所有数据类型
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {
}
