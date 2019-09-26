package com.course.httpclent.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class MyCookiesGet {

    private String url;
    private ResourceBundle bundle;
    //来存储cookie信息的变量
    private CookieStore cookieStore;

    @BeforeTest
    public void getProperties(){
        //获取配置文件
        bundle = ResourceBundle.getBundle("application");
        //给url赋值
        url = bundle.getString("test.url");
    }

    @Test
    public void getCookies() throws IOException {
        String result;
        String testUrl = this.url+bundle.getString("getCookies.uri");
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
        this.cookieStore = client.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        for(Cookie cookie:cookies){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name = "+name+"    cookie value = " + value);
        }
    }

    @Test(dependsOnMethods = "getCookies")
    public void getWithCookies() throws IOException {
        String testUrl = this.url + bundle.getString("test.get.cookies");
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();

        //设置cookie信息
        client.setCookieStore(this.cookieStore);

        HttpResponse response = client.execute(get);
        //获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode："+statusCode);
        if(statusCode == 200){
            String  result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }
    }
}
