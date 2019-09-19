package com.test.testng;

import org.testng.annotations.Test;

public class Exception {
	
	/**
	 * 什么时候会用到异常测试
	 * 在我们期望一个结果为某一个异常的时候
	 * 比如：传入一个不合法的参数，程序抛出了异常
	 * 预期结果就是异常
	 */
	
//	这是一个测试结果会失败的异常测试
	@Test(expectedExceptions = RuntimeException.class)
	public void runTimeException() {
		System.out.println("这是一个失败的测试");
	}
	
//	这是一个成功的异常测试
	@Test(expectedExceptions = RuntimeException.class)
	public void runTimeExceptionsuccess() {
		System.out.println("这是我的异常测试");
		throw new RuntimeException();
	}
}

