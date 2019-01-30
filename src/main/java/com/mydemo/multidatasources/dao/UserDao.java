package com.mydemo.multidatasources.dao;

import com.mydemo.multidatasources.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-30 16:03
 * @Description: 获取到用户的数据库操作类
 * To change this template use File | Settings | File and Templates.
 */
@Component
public interface UserDao {

    List<User> queryUsers();
}
