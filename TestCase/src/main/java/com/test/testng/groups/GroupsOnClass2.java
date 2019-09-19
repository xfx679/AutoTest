package com.test.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {

	public void stu1() {
		System.out.println("GroupsOnClass2中的stu1运行");
	}
	
	public void stu2() {
		System.out.println("GroupsOnClass2中的stu2运行");
	}
}
