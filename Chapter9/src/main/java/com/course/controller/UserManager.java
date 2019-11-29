package com.course.controller;

import com.course.model.User;
import com.course.utils.CookieUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j
@RestController
@Api(value = "v1", description = "用户管理系统")
@RequestMapping("v1")
public class UserManager {

    //获取执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登录接口", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(HttpServletResponse response, @RequestBody User user) {
        int i = template.selectOne("login", user);
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        log.info("查询到的结果是" + i);
        if (i == 1) {
            log.info("登录的用户是：" + user.getUsername());
            return true;
        }
        return false;
    }

    @ApiOperation(value = "添加用户接口", httpMethod = "POST")
    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request, @RequestBody User user) {
        Boolean cookies = CookieUtils.cookies(request);
        int result = 0;
        if (cookies != null) {
            result = template.insert("addUser", user);
        }
        if (result > 0) {
            log.info("添加用户的数量是：" + result);
            return true;
        }
        return false;
    }

    @ApiOperation(value = "获取用户列表信息接口", httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public List<User> getUserInfo(HttpServletRequest request, @RequestBody User user) {
        Boolean cookies = CookieUtils.cookies(request);
        if (cookies != null) {
            List<User> userInfo = template.selectList("getUserInfo", user);
            log.info("getUserInfo获取到的用户数量是：" + userInfo.size());
            return userInfo;
        } else {
            System.out.println("接口请求失败");
            return null;
        }
    }

    @ApiOperation(value = "获取性别为男的用户信息接口", httpMethod = "POST")
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    public List<User> getUserList(HttpServletRequest request, @RequestBody User user) {
        Boolean cookies = CookieUtils.cookies(request);
        if (cookies != null) {
            List<User> userList = template.selectList("getUserList", user);
            log.info("getUserList获取到的用户数量是：" + userList.size());
            return userList;
        }else {
            System.out.println("接口请求失败");
            return null;
        }
    }

    @ApiOperation(value = "更新/删除用户接口", httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request, @RequestBody User user) throws InterruptedException {
        Boolean cookies = CookieUtils.cookies(request);
        int i = 0;
        if (cookies == true) {
            i = template.update("updateUser", user);
            Thread.sleep(3000);
        }
        log.info("更新数据的条数为：" + i);
        return i;
    }
}
