package com.mydemo.multidatasources.service.user.userService.userServiceImpl;

import com.mydemo.multidatasources.dao.UserDao;
import com.mydemo.multidatasources.datasource.DataSource;
import com.mydemo.multidatasources.entity.User;
import com.mydemo.multidatasources.service.user.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-30 16:08
 * @Description: 查询用户信息的实现类
 * To change this template use File | Settings | File and Templates.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 在这里，可以通过注解，手动的切换在spring-mybatis中注册的数据源。
     * 如果没有指定，则使用默认的数据源。
     * 指定后，以这里指定的为准
     */
    @DataSource("ds2")
    @Override
    public List<User> getAllUsers() {
        return this.userDao.queryUsers();
    }

    @Override
    public List<User> getAllUsers2(String dsCode) {
        return this.userDao.queryUsers();
    }
}
