package com.course.httpclent.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class MyCookiesPost {

    private String url;
    private ResourceBundle bundle;
    private CookieStore cookie;

    @BeforeTest
    public void testProperties(){
        bundle = ResourceBundle.getBundle("application");
        url = bundle.getString("test.url");
    }

    @Test
    public void getCookies() throws IOException {
        String testUrl = this.url+bundle.getString("getCookies.uri");
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        String resultGet = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(resultGet);

        //获取cookies信息
        this.cookie = client.getCookieStore();
        List<Cookie> cookies = cookie.getCookies();
        for(Cookie cookie:cookies){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name = "+name+"    cookie value = " + value);
        }
    }
    @Test(dependsOnMethods = "getCookies")
    public void postCookies() throws IOException {
        //拼接最终的测试地址
        String testUrl = this.url + bundle.getString("test.post.cookies");
//        System.out.println(testUrl);
        //声明一个client对象,用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法，post方法
        HttpPost post = new HttpPost(testUrl);
        //添加参数
        JSONObject json = new JSONObject();
        json.put("name","wangwu");
        json.put("age","20");
        //设置请求头信息(headers)
        post.setHeader("Content-Type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(json.toString(),"utf-8");
        post.setEntity(entity);
        //声明对象来进行响应结果的存储
        String resultPost;
        //设置cookie信息
        client.setCookieStore(this.cookie);
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        resultPost = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("返回值："+resultPost);
        //处理结果，判断返回结果是否符合预期
        //将返回来的字符串传换成一个json对象
        JSONObject jsonObject = new JSONObject(resultPost);
        //获取结果值
        String success = (String) jsonObject.get("wangwu");
        Integer status = (Integer) jsonObject.get("status");
        System.out.println(status instanceof Integer);
        //具体的判断返回结果的值
        Assert.assertEquals("success",success);
        Assert.assertSame(1,status);
        System.out.println("断言成功");
    }
}
