package com.course.server;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class MyGetMethod {

    //返回cookies信息的get请求
    //访问的路径
    @RequestMapping(value = "/get/cookies", method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response) {
        //添加cookie信息
        Cookie cookie = new Cookie("login", "get");
        //将cookie信息添加到响应结果中
        response.addCookie(cookie);
        return "恭喜你返回cookie信息成功";
    }

    //携带cookies信息不带参数的get请求
    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        //判断cookie是否为空
        if (Objects.isNull(cookies)) {
            return "你需求携带cookies信息来";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("get")) {
//                System.out.println(cookie.getName()+cookie.getValue());
                return "恭喜你访问成功";
            }
        }
        return "你需求携带cookies信息来";
    }

    //第一种
    //携带cookies信息带参数的get请求
    //获取商品列表
    @RequestMapping(value = "/get/with/param/cookies", method = RequestMethod.GET)
    public Map<String,Integer> getWithParamCookies(@RequestParam Integer start,@RequestParam Integer end) {
        Map<String,Integer> map = new HashMap();
        map.put("T恤",300);
        map.put("干脆面",1);
        map.put("运动鞋",400);
        return map;
    }

    //第二种
    //携带cookies信息带参数的get请求
    @RequestMapping(value = "/myGet/with/param/cookies/{start}/{end}")
    public Map<String,Integer> myGetWithParamCookies(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer> map = new HashMap();
        map.put("T恤",300);
        map.put("干脆面",1);
        map.put("运动鞋",400);
        return map;
    }
}
