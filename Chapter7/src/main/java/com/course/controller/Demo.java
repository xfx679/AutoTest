package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@Api(value = "v1", description = "这是我的第一个版本的demo")
public class Demo {

    //获取执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCount", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户数", httpMethod = "GET")
    public int getUserCount() {
        return template.selectOne("getUserCount");
    }

    //添加用户
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public int addUser(@RequestBody User user) {
        int result = template.insert("addUser", user);
        return result;
    }

    //更新用户
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public int updateUser(@RequestBody User user) {
        return template.update("updateUser", user);
    }

    //删除用户
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public int deleteUser(@RequestParam Integer id){
        return template.delete("deleteUser",id);
    }
}
