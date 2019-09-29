package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
//        HttpServletRequest  装请求信息的类
//        HttpServletResponse  装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获取cookies信息成功";
    }

    /**
     * 要求客户携带cookies访问
     */
    @ApiOperation(value = "要求客户携带cookies访问",httpMethod = "GET")
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "你必须携带cookie信息来";
        }
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("login") && cookie.getValue().equals("GET")){
            return "这是一个需要携带cookie信息才能访问的get请求";
            }
        }
        return "你必须携带cookie信息来";
    }

    /**
     * 需要携带参数才能访问的get请求
     * 第一种：url:key=value
     */
    @RequestMapping(value = "get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> map = new HashMap<>();
        map.put("鞋",400);
        map.put("干脆面",1);
        map.put("衬衫",300);
        return map;
    }

    /**
     * 第二种需要携带参数的get请求
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    @ApiOperation(value = "需要携带参数才能访问的get请求的第二种实现",httpMethod = "GET")
    public Map<String,Integer> myGetList(@PathVariable Integer start, @PathVariable Integer end){
        Map<String,Integer> map = new HashMap<>();
        map.put("鞋",400);
        map.put("干脆面",1);
        map.put("衬衫",300);
        return map;
    }
}
