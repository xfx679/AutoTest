package com.test.testng.paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//参数化测试
public class ParameterTest {

	@Test
	@Parameters({"name","age"})
	public void parameter(String name, String age) {
		System.out.println("name = " + name + "，age = " + age);
	}
}
