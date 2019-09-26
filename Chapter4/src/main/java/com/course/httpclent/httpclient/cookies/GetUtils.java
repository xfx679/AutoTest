package com.course.httpclent.httpclient.cookies;


import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//get请求的工具类
public class GetUtils {

    public static String getAutoTest(String url, Map<String, String> map,CookieStore cookieStore) throws IOException, URISyntaxException {
        HttpGet get;
        //声明一个Client的对象获取连接客户端的工具
//        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        if (map == null) {
            //没有参数信息
            get = new HttpGet(url);
        } else {
            //有参数信息
            URIBuilder builder = new URIBuilder(url);
            //定义请求的参数
            Set<String> set = map.keySet();
            for (Iterator iter = set.iterator(); iter.hasNext(); ) {
                String key = (String) iter.next();
                String value = map.get(key);
                builder.addParameter(key, value);
            }
            get = new HttpGet(builder.build());
        }
        HttpResponse response = client.execute(get);
        //获取响应结果
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        //获取cookies信息
        List<Cookie> cookies = cookieStore.getCookies();
        if (cookies != null) {
            for (Cookie co : cookies) {
                String name = co.getName();
                String value = co.getValue();
                System.out.println("cookie name = " + name + ";cookie value = " + value);
            }
        }
        return result;
    }
}
