package com.test.testng.multithred;

import org.testng.annotations.Test;

public class MultiThreadOnXml {
	
	@Test
	public void test1() {
//		  按照格式输出
		System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
	}
	
	@Test
	public void test2() {
//		  按照格式输出
		System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
	}
	
	@Test
	public void test3() {
//		  按照格式输出
		System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
	}
}
