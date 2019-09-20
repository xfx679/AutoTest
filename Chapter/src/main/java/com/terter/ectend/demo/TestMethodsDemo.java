package com.terter.ectend.demo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

//生成测试报告
public class TestMethodsDemo {

    @Test
    public void test(){
        Assert.assertEquals(1,2);
    }

    @Test
    public void test2(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void test3(){
        Assert.assertEquals("aaa","aaa");
    }

    @Test
    public void logDemo(){
        Reporter.log("这是我们自己写的日志");
        throw new RuntimeException("这是我自己运行是的异常");
    }
}
