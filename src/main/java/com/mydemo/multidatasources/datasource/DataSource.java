package com.mydemo.multidatasources.datasource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-30 16:00
 * @Description: 自定义动态数据源的注解
 * To change this template use File | Settings | File and Templates.
 */

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源
 *
 * @author llb 2017-03-30
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface DataSource {

    String value() default "";

}
