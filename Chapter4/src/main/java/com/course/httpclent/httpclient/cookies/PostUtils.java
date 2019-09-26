package com.course.httpclent.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PostUtils {

    public static String postAutoTest(String url, List<NameValuePair> list) throws IOException, URISyntaxException {
        //获取连接客户端的工作
        CloseableHttpClient client = HttpClients.createDefault();
        //声明post方法
        HttpPost post = new HttpPost(url);
        if(list != null){
             //添加参数
//            for (NameValuePair pair: list) {
//                String name = pair.getName();
//                String value = pair.getValue();
//            }
            post = new HttpPost(url);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"utf-8");
            post.setEntity(entity);
        }
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        return result;
    }
}
