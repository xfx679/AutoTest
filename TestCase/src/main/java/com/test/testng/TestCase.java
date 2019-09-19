package com.test.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCase {
	
	@Test
	public void testCase1() {
		System.out.println("Test这是测试用例1");
	}
	
	@Test
	public void testCase2() {
		System.out.println("Test这是测试用例2");
	}
	
	@BeforeMethod
	public void beforMethod() {
		System.out.println("BeforeMethod这是在测试方法之前运行的");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("AfterMeshod这是在测试方法之后运行的");
	}
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("BefoeClass这是在类之前运行的");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("AfterClass这是在类之后运行的");
	}
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("BeforeSuite测试套件");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("AfterSuire测试套件");
	}
}
