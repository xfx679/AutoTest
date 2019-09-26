package com.course.httpclent.httpclient.cookies;


import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class MyHeadersPost {

    private CookieStore cookieStore = new BasicCookieStore();

    @Test
    public void getParam() throws IOException, URISyntaxException {
        String testUrl = PropertiesUtils.testProperties("test.get.param");
        Map<String,String> getMap = new HashMap<String,String>();
        getMap.put("name","zhangshan");
        getMap.put("age","20");
        System.out.println(testUrl);
        String result = GetUtils.getAutoTest(testUrl,getMap,null);
        System.out.println(result);
    }

    @Test
    public void getTest() throws IOException, URISyntaxException {
        String testUrl = PropertiesUtils.testProperties("test.get.demo");
        System.out.println(testUrl);
        String result = GetUtils.getAutoTest(testUrl, null,null);
        System.out.println(result);
    }

    @Test
    public void postTest() throws IOException, URISyntaxException {
        String postUrl = PropertiesUtils.testProperties("test.post.demo");
        String result = PostUtils.postAutoTest(postUrl,null);
        System.out.println(result);
    }

    @Test
    public void postParam() throws IOException, URISyntaxException {
        String postUrl = PropertiesUtils.testProperties("test.post.param");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("name","zhangshan"));
        list.add(new BasicNameValuePair("sex","man"));
        String result = PostUtils.postAutoTest(postUrl,list);
        System.out.println(result);
    }

    @Test
    public void getCookie() throws IOException, URISyntaxException {
        String getUrl = PropertiesUtils.testProperties("test.get.cookie");
        String result = GetUtils.getAutoTest(getUrl,null,this.cookieStore);
        System.out.println(result);
    }

    //依赖于getCookie方法
    @Test(dependsOnMethods = "getCookie")
    public void postJsonParam() throws IOException {
        String postUrl = PropertiesUtils.testProperties("test.post.cookies");
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","wangwu");
        map.put("age","20");
        String result = PostJsonUtils.postAutoTest(postUrl, map, this.cookieStore);
        System.out.println(result);

    }
}
