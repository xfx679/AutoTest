package com.test.testng;

import org.testng.annotations.Test;

//线程测试
public class BaseThreadTest {

	@Test
	public void test1() {
		System.out.println("线程测试before");
		System.out.printf("Tfread Id ：%s%n",Thread.currentThread().getId());
		System.out.println("线程测试after");
	}
}
