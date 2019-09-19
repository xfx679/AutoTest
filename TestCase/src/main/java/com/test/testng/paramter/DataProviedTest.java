package com.test.testng.paramter;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviedTest {

	@Test(dataProvider = "data")
	public void testDataprovied(String name, int age) {
		System.out.println("name = " + name + "，age = " + age);
	}

	@DataProvider(name = "data")
	public Object[][] dataProvied() {
		Object[][] o = new Object[][] { { "zhangsan", 10 }, { "lisi", 20 }, { "wangwu", 30 } };
		return o;
	}

	@Test(dataProvider = "method")
	public void test1(String name, int age) {
		System.out.println("test11111 name = " + name + ";age = " + age);
	}

	@Test(dataProvider = "method")
	public void test2(String name, int age) {
		System.out.println("test2222 name = " + name + ";age = " + age);
	}

	@DataProvider(name = "method")
	public Object[][] methodProvied(Method method) {
		Object[][] result = null;
		if (method.getName().equals("test1")) {
			result = new Object[][] { { "zhangsan", 20 }, { "lisi", 25 } };
		}
		if (method.getName().equals("test2")) {
			result = new Object[][] { { "wangwu", 40 }, { "liliu", 60 } };
		}
		return result;
	}
}
