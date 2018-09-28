package com.youma.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 获取属性的中文名字
 * 配合exl类使用,将属性名用name中的中文代替
 * 将int转为对应的文字
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Czq {
    //列名
    public String name() default "";
    //int转为对应文字
    public String [] value() default {""};
    //列宽
    public int length() default 4;
}
