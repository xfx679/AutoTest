package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

//get请求的工具类
public class GetUtils {

    public static  Map<String,String>  getAutoTest(String url) throws IOException, URISyntaxException {
        //声明一个Client的对象获取连接客户端的工具
        HttpClient client  = new ();

        //添加参数
        Map<String,String> map  = new HashMap<String, String>();

        if (map.toString().equals("")){
            //没有参数信息
            //声明一个get方法
            HttpGet get = new HttpGet(url);
            //执行get方法
            HttpResponse response = client.execute(get);
            //获取响应结果
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            return map;
        }else{
            //有参数信息
            //定义请求的参数
            URIBuilder builder = new URIBuilder(url);
            HttpGet get = new HttpGet(builder.build());

        }

    }
}
