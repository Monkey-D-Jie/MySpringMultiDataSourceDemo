package com.mydemo.multidatasources.service.user.userService.userServiceImpl;

import com.mydemo.multidatasources.datasource.DataSource;
import com.mydemo.multidatasources.entity.User;
import com.mydemo.multidatasources.service.user.userService.UserService;
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

    //用自定义注解指定查询用的数据源
    @DataSource("ds1")
    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
