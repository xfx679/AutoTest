package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取userId为1的用户信息")
    public void getUserInfo() throws IOException, InterruptedException {
        GetUserInfoCase getUserInfoCase = TestConfig.sqlSession.selectOne("getUserInfoCase",1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserListUrl);

        //下边写完接口的代码
        JSONObject json = getJsonResult(getUserInfoCase);
        Thread.sleep(2000);
        User user = TestConfig.sqlSession.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);
        System.out.println("自己查库获取用户信息:"+user.toString());

//        List resultList = new ArrayList();
//        resultList.add(user);
        JSONObject jsonArray = new JSONObject(user);
        System.out.println(jsonArray);
        System.out.println("获取用户信息："+jsonArray.toString());
        System.out.println("调用接口获取用户信息："+json.toString());
        if (jsonArray.toString().equals(json.toString())){
            System.out.println("断言成功");
        }else {
            System.out.println("断言失败");
        }
        Assert.assertEquals(jsonArray.toString(),json.toString());

    }


    private JSONObject getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {
        //创建post请求
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        //添加参数
        JSONObject json = new JSONObject();
        json.put("userId",getUserInfoCase.getId());
        //添加请求头
        post.setHeader("Content-type","application/json;charset=GBK");
        //将参数添加到方法中
        StringEntity entity = new StringEntity(json.toString(),"utf-8");
        post.setEntity(entity);
        //执行post方法，并且添加Cookies信息
        HttpResponse response = TestConfig.httpClientBuilder.setDefaultCookieStore(TestConfig.cookieStore)
                .build().execute(post);
        //声明一个对象对响应结果进行存储
        String result;
        //获取状态码
        Integer code = response.getStatusLine().getStatusCode();
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("响应结果："+result);
//        List resultList = Arrays.asList(result);
//        System.out.println("resultList="+resultList);
        JSONObject array = new JSONObject(result);
        return array;
    }
}
