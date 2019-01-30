package com.mydemo.multidatasources.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-30 16:01
 * @Description: 动态切换数据源
 * To change this template use File | Settings | File and Templates.
 */

@Order(1)
@Aspect
@Repository
public class DataSourceAspect {

    @Pointcut("execution(* com..service..*Impl.*(..))")
    private void anyMethod() {
    }

    @AfterReturning(value = "anyMethod()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        DataSourceHolder.clearDataSourceType();
    }

    @Before(value="anyMethod()")
    public void before(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //如果方法体上使用了DataSource注解
        if (method.isAnnotationPresent(DataSource.class)) {
            //获取该方法上的注解名
            DataSource datasource = method.getAnnotation(DataSource.class);
            //将方法体上的注解的值赋予给DataSourceHolder数据源持有类
            DataSourceHolder.setDataSourceType(datasource.value());
        }
    }

}