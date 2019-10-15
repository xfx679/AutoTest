package com.course.server;

import com.course.bean.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MyPostMethod {

    //存放cookie信息
    private Cookie cookie;

    //返回cookies信息的post请求
    //用户登录成功后返回信息
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletResponse response
            , @RequestParam(value = "username", required = true) String username
            , @RequestParam(value = "password", required = true) String password) {
        //添加cookie信息
        cookie = new Cookie("login", "post");
        response.addCookie(cookie);
        //判断用户名是否正确
        if (username.equals("lisi") && password.equals("123")) {
            return "登录成功";
        }
        return "用户名或者密码错误";
    }

    //获取用户列表
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    public String getUserList(HttpServletRequest request, @RequestBody User u) {
        User user;
        //获取cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            //判断cookie信息、用户名和密码是否正确
            if (cookie.getName().equals("login") && cookie.getValue().equals("post")
            && u.getUsername().equals("lisi") && u.getPassword().equals("123")){
                user = new User();
                user.setName("wangwu");
                user.setAge(20);
                user.setSex("man");
                return user.toString();
            }
        }
        return "参数不合法";
    }
}
