package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {

    public static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceName name) {
        String address = bundle.getString("test.url");
        String uri = "";
        if (name == InterfaceName.GETUSERLISTCASE) {
            uri = bundle.getString("getUserList.uri");
        }
        if (name == InterfaceName.LOGINCASE) {
            uri = bundle.getString("login.uri");
        }
        if (name == InterfaceName.UPDATEUSERINFOCASE) {
            uri = bundle.getString("updateUserInfo.uri");
        }
        if (name == InterfaceName.GETUSERINFOCASE) {
            uri = bundle.getString("getUserInfo.uri");
        }
        if (name == InterfaceName.ADDUDERCASE) {
            uri = bundle.getString("addUserInfo.uri");
        }
        //最终的测试地址
        String testUrl = address + uri;
        return testUrl;
    }
}
