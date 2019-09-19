package com.test.testng.suite;

import org.testng.annotations.Test;

public class IgnoreTest {
	
	@Test
	public void ignore1() {
		System.out.println("ignore 1 执行");
	}
	
	//忽略测试
	@Test(enabled = false)
	public void ignore2() {
		System.out.println("ignore 2 执行");
	}
	
	@Test(enabled = true)
	public void ignore3() {
		System.out.println("ignore 3执行");
	}
}
