package com.course.httpclent;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PostHttpClient {

    private String url;
    private ResourceBundle bundle;
    private CookieStore cookieStore = new BasicCookieStore();
    private CloseableHttpClient httpClient;


    //获取配置信息
    @BeforeTest
    public void postTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    //返回cookies信息的post请求
    @Test
    public void postCookies() throws IOException {
        //用来存放结果
        String result;
        //完整的url
        String postCookiesUrl = this.url + bundle.getString("test.post.cookie");
        //创建post请求
        HttpPost post = new HttpPost(postCookiesUrl);
        //执行post方法
        httpClient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
        HttpResponse response = httpClient.execute(post);
        //获取响应状态码
        int code = response.getStatusLine().getStatusCode();
        if (code == 200) {
            //获取响应结果
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
            //获取cookie信息
            List<Cookie> cookies = cookieStore.getCookies();
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println("cookie的name:" + name + ";cookie的value:" + value);
            }
        } else {
            System.out.println("携带cookie信息失败");
        }
    }

    //携带cookies的post请求
    @Test(dependsOnMethods = "postCookies")
    public void postPramCookies() throws IOException {
        //存储响应结果
        String result;
        //完整url
        String getCookiesUrl = this.url + this.bundle.getString("test.post.cookies");
        //创建post请求
        HttpPost post = new HttpPost(getCookiesUrl);
        //添加headers信息
        post.setHeader("Content-Type","application/json");
        //添加参数
        JSONObject json = new JSONObject();
        json.put("name","wangwu");
        json.put("age","20");
        System.out.println(json.toString());
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(json.toString(),"utf-8");
        post.setEntity(entity);
        //执行post方法
        httpClient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
        HttpResponse response = httpClient.execute(post);
        //获取状态码
        int code = response.getStatusLine().getStatusCode();
        if (code == 200){
            //获取响应结果
            result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println("响应结果："+ result);
            //处理结果，判断返回结果是否符合预期
            //将返回来的字符串传转换成一个json对象
            JSONObject jsonObject = new JSONObject(result);
            System.out.println(jsonObject.get("wangwu"));
            String success = (String)jsonObject.get("wangwu");
            Integer status = (Integer)jsonObject.get("status");
            //判断返回结果值是否正确
            Assert.assertEquals("success",success);
            Assert.assertSame(1,status);
            System.out.println("断言成功");
        }else{
            System.out.println("携带cookie信息失败");
        }
    }

    //携带cookie信息非json参数的post请求
    @Test(dependsOnMethods = "postCookies")
    public void postPramsCookies() throws IOException {
        //存储响应结果
        String result;
        //完整Url
        String postPramsCookies = this.url + bundle.getString("test.post.prams.cookies");
        //创建post请求
        HttpPost post = new HttpPost(postPramsCookies);
        //添加参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("name","xiaoxiao"));
        list.add(new BasicNameValuePair("age","18"));
        //将参数添加到方法中
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"utf-8");
//        StringEntity entity = new StringEntity(list.toString());
        post.setEntity(entity);
        //添加headers信息
//        post.setHeader("Content_type","application/json");

        httpClient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        //获取状态码
        int code = response.getStatusLine().getStatusCode();
        if(code == 200){
            //获取返回结果
            result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println("响应结果："+result);
            //将赶回结果字符串转换成json格式
            JSONObject json = new JSONObject(result);
            //判断返回结果和预期结果是否一致
            Integer status = (Integer)json.get("status");
            String success = (String)json.get("xiaoxiao");
            Assert.assertSame(1,status);
            Assert.assertEquals("success",success);
            System.out.println("断言成功");
        }else
            System.out.println("获取返回结果失败");
    }
}
