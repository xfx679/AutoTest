package com.course.utils;

import lombok.extern.log4j.Log4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Log4j
public class CookieUtils {

    //获取cookie信息
    public static Boolean cookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            log.info("Cookies为空");
            return false;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                log.info("cookies验证通过");
                return true;
            }
        }
        return false;
    }
}
