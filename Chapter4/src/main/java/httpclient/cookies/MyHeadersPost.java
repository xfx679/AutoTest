package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class MyHeadersPost {

    private String url;
    //获取配置文件的类
    private ResourceBundle bundle;

    @Test
    public void postHeaders() throws IOException {
        Map<String, ResourceBundle> map = PropertiesUtils.testProperties();
        Set<String> set = map.keySet();
        //获取工具类中的信息
        for (Iterator iter = set.iterator();iter.hasNext();){
            url = (String) iter.next();
            bundle = map.get(url);
            System.out.println(url + bundle);
        }
        String testUrl = this.url + this.bundle.getString("test.get.param");
        System.out.println(testUrl);
        Map<String, String> getMap = GetUtils.getAutoTest(testUrl);
        getMap.put("name","zhangshan");
        getMap.put("age","20");

    }

}
