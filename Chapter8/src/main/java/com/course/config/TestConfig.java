package com.course.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.ibatis.session.SqlSession;

public class TestConfig {

    //登录接口
    public static String loginUrl;
    //更新用户信息接口
    public static String updateUserInfoUrl;
    //添加用户信息接口
    public static String addUserUrl;
    //获取用户信息接口
    public static String getUserInfoUrl;
    //添加用户信息接口
    public static String getUserListUrl;
    //声明http客户端
    public static HttpClientBuilder httpClientBuilder;
    //用来存储cookie信息的变量
    public static CookieStore cookieStore;

    public static SqlSession sqlSession;


}
