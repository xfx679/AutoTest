package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.model.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserInfoListTest {

    @Test(dependsOnGroups = "loginTrue", description = "获取性别为男的用户信息")
    public void getUserInfoList() throws InterruptedException, IOException {
        GetUserListCase getUserListCase = TestConfig.sqlSession.selectOne("getUserListCase", 1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);

        //下面写完接口的代码
        JSONArray userResult = getJsonResult(getUserListCase);
        Thread.sleep(2000);
        List<User> user = TestConfig.sqlSession.selectList(getUserListCase.getExpected(), getUserListCase);
        System.out.println("list获取的user数据：" + user);
        JSONArray userJsonList = new JSONArray(user);
        System.out.println("userResult.length()=" + userResult.toString());
        System.out.println("userJsonList.length()=" + userJsonList.toString());
        Assert.assertEquals(userResult.length(), userJsonList.length());
        for (int i = 0; i < userResult.length(); i++) {
            JSONObject expect = (JSONObject) userResult.get(i);
            JSONObject actual = (JSONObject) userJsonList.get(i);
            Assert.assertEquals(expect.toString(),actual.toString());
        }
        System.out.println("断言成功");
    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        //创建post请求
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        //添加参数
        JSONObject json = new JSONObject();
        json.put("sex", getUserListCase.getSex());
        //讲参数添加到方法中
        StringEntity entity = new StringEntity(json.toString(), "utf-8");
        post.setEntity(entity);
        //添加请求头
        post.setHeader("Content-Type", "application/json;charset=GBK");
        //执行post方法，并添加cookies信息
        HttpResponse response = TestConfig.httpClientBuilder.setDefaultCookieStore(TestConfig.cookieStore).build().execute(post);
        //声明一个对象，对返回结果进行存储
        String result;
        //响应码
        Integer code = response.getStatusLine().getStatusCode();
        System.out.println("响应结果：" + code);
        //判断返回结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        //将返回结果设置成json格式
        JSONArray array = new JSONArray(result);
        System.out.println("调用接口 list result:" + result);
        return array;
    }
}
