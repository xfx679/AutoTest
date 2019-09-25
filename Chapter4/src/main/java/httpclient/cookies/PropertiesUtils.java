package com.course.httpclient.cookies;


import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

//获取配置文件的工具类
public class PropertiesUtils {

    @BeforeTest
    public static Map<String,ResourceBundle> testProperties(){
        Map<String,ResourceBundle> map = new HashMap<String, ResourceBundle>();
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String url = bundle.getString("test.url");
        map.put(url,bundle);
        return  map;
    }
}
