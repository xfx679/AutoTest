package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserCaseTest {

    @Test(dependsOnGroups = "loginTrue", description = "添加用户接口")
    public void addUser() throws IOException, InterruptedException {
        System.out.println(134);
        AddUserCase addUserCase = TestConfig.sqlSession.selectOne("addUserCase", 1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);

        //下边的代码为写完接口的测试代码
        String result = addResult(addUserCase);

        //查看用户是否添加成功
        Thread.sleep(2000);
        User user =TestConfig.sqlSession.selectOne("addUser",addUserCase);
        System.out.println(user.toString());
        Assert.assertEquals(addUserCase.getExpected(),result);
        System.out.println("断言成功");
    }

    private String addResult(AddUserCase addUserCase) throws IOException {
        //创建post请求
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        //添加请求头信息
        post.setHeader("Content-Type", "application/json;charset=GBK");
        //添加参数
        JSONObject json = new JSONObject();
        json.put("username", addUserCase.getUsername());
        json.put("password", addUserCase.getPassword());
        json.put("age", addUserCase.getAge());
        json.put("sex", addUserCase.getSex());
        json.put("permission", addUserCase.getPermission());
        json.put("isDelete", addUserCase.getIsDelete());
        //将参数添加到方法中
        StringEntity entity = new StringEntity(json.toString(), "utf-8");
        post.setEntity(entity);
        //执行post方法，并且添加Cookies信息
        HttpResponse response = TestConfig.httpClientBuilder.setDefaultCookieStore(TestConfig.cookieStore).build().execute(post);
        //获取响应码
        Integer code = response.getStatusLine().getStatusCode();
        //判断响应码是否为200
        if (code == 200) {
            //声明一个对象，对返回结果进行存储
            String result;
            //获取返回结果
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("响应结果：" + result);
            return result;
        }
        return "";
    }
}
