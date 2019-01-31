package com.mydemo.multidatasources.controller;

import com.mydemo.multidatasources.dao.UserDao;
import com.mydemo.multidatasources.entity.ResponseBean;
import com.mydemo.multidatasources.service.user.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-31 14:45
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */
@Controller
//@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "你的配置有点小问题")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/getAllUser",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public ResponseBean getAllUser(){
        //在指明了 默认的数据源的情况下，是能够执行这种不经过impl直接用dao层方法的
        //因为 没有指定数据源 ，所以就用默认的，
//        return new ResponseBean(this.userDao.queryUsers());
        //当经过impl 指定了业务方法的数据源后，就以方法上指定的数据源为准
        return new ResponseBean(this.userService.getAllUsers());
    }

    /**
     * 请求方法
     http://localhost:8080/user/getAllUserWithDsCode/ds2
     这里为了更好的测试，直接是把请求的类型 设置成 GET，正常的业务场景下，应该设置为[POST]
     因为要到impl那边切，这里的service方法中传入参数后【getAllUsers2(dsCode)中的dsCode】，
     会被带到切面处理的过程中，通过joinPoint.getArgs的方法，可以获取到数据源的key，设置对应的数据源即可
     */
    @RequestMapping(value = "/user/getAllUserWithDsCode/{dsCode}",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public ResponseBean getAllUserWithDsCode(@PathVariable("dsCode") String dsCode){
        //
        return new ResponseBean(this.userService.getAllUsers2(dsCode));
    }
}
