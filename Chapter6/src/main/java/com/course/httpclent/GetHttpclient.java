package com.course.httpclent;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GetHttpclient {
    private String url;
    private ResourceBundle bundle;

    private CookieStore cookieStore = new BasicCookieStore();

    @BeforeTest
    //获取配置文件信息
    public void getProperties() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void getTest() throws IOException {
        //用来存放结果
        String result;
        //获取链接
        HttpGet get = new HttpGet("http://www.baidu.com");
        //执行get方法
        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }

    //返回cookies信息的get请求
    @Test
    public void getCookie() throws IOException {
        //存放结果
        String result;
        //获取完整链接
        String getCookieUrl = this.url + bundle.getString("test.get.cookie");
        //创建get请求
        HttpGet get = new HttpGet(getCookieUrl);
        //执行get方法
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
        HttpResponse execute = httpClient.execute(get);
        //获取结果
        result = EntityUtils.toString(execute.getEntity(), "utf-8");
        //打印结果
        System.out.println("响应结果：" + result);
        //获取cookies信息
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie的name：" + name + "；cookie的value：" + value);
        }
    }

    //携带cookies的信息的get请求
    @Test(dependsOnMethods = "getCookie")
    public void getPramCookie() throws IOException {
        //存放结果
        String result;
        //获取完整的ur链接
        String getCookiesUrl = this.url + bundle.getString("test.get.cookies");
        //创建get请求
        HttpGet get = new HttpGet(getCookiesUrl);
        //执行get方法
        //设置cookies信息
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
        HttpResponse response = httpClient.execute(get);
        //获取响应状态码
        int code = response.getStatusLine().getStatusCode();
        if (code == 200) {
            //获取响应结果
            result = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println("响应结果：" + result);
        }else {
            System.out.println("携带cookie信息失败");
        }
    }

    //携带cookie信息带参数的get请求
    @Test(dependsOnMethods = "getCookie")
    public void getPramsCookies() throws URISyntaxException, IOException {
        //存放结果
        String result;
        //完整URL
        String getPramsCookies = this.url + bundle.getString("test.get.prams.cookies");
        //添加参数
        URIBuilder builder = new URIBuilder(getPramsCookies);
        builder.addParameter("name","lisi");
        builder.addParameter("sex","man");
        //创建get请求
        HttpGet get = new HttpGet(builder.build());
        //执行get方法
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
        HttpResponse response = httpClient.execute(get);
        //获取响应码
        int code = response.getStatusLine().getStatusCode();
        if(code == 200){
            //获取返回结果
            result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }else{
            System.out.println("获取响应结果失败");
        }
    }
}
