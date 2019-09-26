package com.course.httpclent.httpclient.cookies;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PostJsonUtils {

    public static String postAutoTest(String url, Map<String,String> map, CookieStore cookieStore) throws IOException {

        System.out.println(cookieStore);
        //声明post方法
        HttpPost post = new HttpPost(url);

        //添加参数
        JSONObject json = new JSONObject();
        Set<String> set = map.keySet();
        for(Iterator iter = set.iterator();iter.hasNext();){
            String key = (String)iter.next();
            String value = map.get(key);
            json.put(key,value);
        }
        System.out.println(json.toString());
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(json.toString(),"utf-8");
        post.setEntity(entity);

        //添加请求头信息
        post.setHeader("Content-Type","application/json");
        if (cookieStore != null) {
            //获取连接客户端的工作
            CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
            //执行post方法
            CloseableHttpResponse response = httpClient.execute(post);
            System.out.println(response);
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            return result;
        }else {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //执行post方法
            CloseableHttpResponse response = httpClient.execute(post);
            System.out.println(response);
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            return result;

        }



    }
}
