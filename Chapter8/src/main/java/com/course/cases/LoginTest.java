package com.course.cases;


import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class LoginTest {

    @BeforeTest(groups = "loginTrue", description = "测试准备工作，获取httpClient对象")
    public void beforeTest() throws IOException {
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFOCASE);
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFOCASE);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLISTCASE);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUDERCASE);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGINCASE);
        TestConfig.sqlSession = DataBaseUtil.getSqlSession();
        TestConfig.httpClientBuilder = HttpClients.custom();
        TestConfig.cookieStore = new BasicCookieStore();
    }

    @Test(groups = "loginTrue", description = "用户成功登录接口")
    public void loginTrue() throws IOException {
        LoginCase loginCase = TestConfig.sqlSession.selectOne("loginCase", 1);
        System.out.println("数据库中id为1的数据：" + loginCase.toString());
        System.out.println("url：" + TestConfig.loginUrl);
        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
        //获取cookie信息
        List<Cookie> cookies = TestConfig.cookieStore.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("Cookie_name="+name+"；Cookie_value="+value);
        }
        //处理结果，判断返回结果是否复核预期结果
        Assert.assertEquals(loginCase.getExpected(), result);

    }

    @Test(description = "用户失败的登录接口")
    public void LoginFalse() throws IOException {
        LoginCase loginCase = TestConfig.sqlSession.selectOne("loginCase", 2);
        System.out.println("数据库中id为2的数据：" + loginCase.toString());
        System.out.println("url：" + TestConfig.loginUrl);
        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
        //处理结果，判断返回结果是否复核预期结果
        Assert.assertEquals(loginCase.getExpected(), result);
    }

    private String getResult(LoginCase loginCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        //添加参数
        JSONObject json = new JSONObject();
        json.put("username", loginCase.getUsername());
        json.put("password", loginCase.getPassword());
        //设置请求头信息 设置header
        post.setHeader("Content-Type", "application/json;charset=GBK");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(json.toString(), "utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.httpClientBuilder.setDefaultCookieStore(TestConfig.cookieStore).build().execute(post);
        //获取状态码
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200){
            //获取响应结果
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("响应结果：" + result);
            return result;
        }
        return "接口报错";

    }
}
