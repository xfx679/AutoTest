package com.test.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {
		
	@Test(groups = "server")
	public void test1() {
		System.out.println("这是服务端的测试方法11111");
	}
	
	@Test(groups = "server")
	public void test2() {
		System.out.println("这是服务端的测试方法2222");
	}
	
	@Test(groups = "client")
	public void test3() {
		System.out.println("这是客户端的测试方法333");
	}
	
	@Test(groups = "client")
	public void test4() {
		System.out.println("这是客户端的测试方法44");
	}
	
	@BeforeGroups("server")
	public void beforGroupsOnServer() {
		System.out.println("这是服务端组运行之前运行的方法");
	}
	
	@BeforeGroups("client")
	public void beforGroupsOnClient() {
		System.out.println("这是客户端组运行之前运行的方法");
	}
	
	@AfterGroups("server")
	public void afterGroupsOnServer() {
		System.out.println("这是服务端运行之后运行的方法");
	}
	
	@AfterGroups("client")
	public void afterGroupsOnClent() {
		System.out.println("这是客户端运行之后运行的方法");
	}
}
