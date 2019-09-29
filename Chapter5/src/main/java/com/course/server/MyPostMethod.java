package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/", description = "这是我全部的post方法")
public class MyPostMethod {

    //这个变量是来装cookie信息的
    private static Cookie cookie;

    //用户登录成功后获取cookies信息，然后在访问其他接口获取到列表
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功后获取cookie信息", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password) {
        if (username.equals("zhangshan") && password.equals("123")) {
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "恭喜你登录成功了";
        }
        return "用户名和密码错误！";
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    public String getUserList(HttpServletRequest request, @RequestBody User u) {
        User user ;
        //获取cookies
        Cookie[] cookies = request.getCookies();
        //验证cookie是否合法
        for (Cookie co : cookies) {
            if (co.getName().equals("login") && co.getValue().equals("true")
            && u.getUsername().equals("wangwu") && u.getPassword().equals("123456")){
                user = new User();
                user.setName("lisi");
                user.setAge("20");
                user.setSex("man");
                return  user.toString();
            }
        }
        return "获取用户列表失败";
    }
}
