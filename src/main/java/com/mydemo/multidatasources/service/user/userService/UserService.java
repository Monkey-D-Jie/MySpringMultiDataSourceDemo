package com.mydemo.multidatasources.service.user.userService;

import com.mydemo.multidatasources.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-30 16:07
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public interface UserService {
    List<User> getAllUsers();

    List<User> getAllUsers2(String dsCode);
}
