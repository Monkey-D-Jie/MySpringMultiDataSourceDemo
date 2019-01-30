package com.mydemo.multidatasources.service.userServiceImpl;


import com.mydemo.multidatasources.entity.User;
import com.mydemo.multidatasources.service.user.userService.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-30 16:25
 * @Description: userService部分的测试类
 * To change this template use File | Settings | File and Templates.
 */

public class UserServiceImplTest {
    /**
     * 打印日志用logger-用debug级别
     *
     */
    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImplTest.class.getName());

    private ApplicationContext act = null;

    /**
     * 注入 UserService，执行方法，查看结果
     */
    @Autowired
    private UserService userService;

    @Before
    public void before() throws Exception {
        //加载配置文件，打印声明等操作
        LOGGER.debug("*******************程序已启动*******************");
        LOGGER.debug("*******************配置文件冷状态加载开始*******************");
        //指定加载某个配置文件
        act=new ClassPathXmlApplicationContext("classpath:spring-*.xml");
        LOGGER.debug("*******************配置文件冷状态加载完成*******************");
        //项目已启动的情况下，可以不指定：因为这里我程序中的配置文件已经在项目启动时被加载好了，可以不用再去指定
    }

    @After()
    public void after() throws Exception {
        LOGGER.debug("*******************程序已结束*******************");
    }

    /**
     *
     * Method: getAllUsers()
     *
     */
    @Test
    public void testGetAllUsers() throws Exception {
        List<User> list = this.userService.getAllUsers();
        if( null != list && list.size() > 0){
            System.out.println("查找到的用户信息为："+list.toString());
        }
    }
}
