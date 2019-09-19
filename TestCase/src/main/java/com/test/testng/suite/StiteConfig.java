package com.test.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class StiteConfig {
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("before suite运行了");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("after suiter运行了");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("before test");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("after test");
	}
}
