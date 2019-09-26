package com.course.httpclent.httpclient.cookies;


import org.testng.annotations.BeforeTest;

import java.util.ResourceBundle;

//获取配置文件的工具类
public class PropertiesUtils {

    @BeforeTest
    public static String testProperties(String ucl){
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String url = bundle.getString("test.url");
        String getUrl = url + bundle.getString(ucl);
        return  getUrl;
    }
}
