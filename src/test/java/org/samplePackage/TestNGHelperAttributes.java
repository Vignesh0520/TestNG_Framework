package org.samplePackage;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class TestNGHelperAttributes {
	
	@Test(priority=-3)
	public void method1() {
		System.out.println("method 1, priority = -3");
	}
	
	@Test(priority=4)
	public void method2() {
		System.out.println("method 2, priority = 4");
	}
	
	@Test(priority=3)
	public void method3() {
		System.out.println("method 3, priority = 3");
	}
	
	@Test(enabled = false)
	public void method5() {
		System.out.println("method 5, priority = 0, enabled = false");
	}
	
	@Test(enabled = true)
	public void method4() {
		System.out.println("method 4, priority = 0, enabled = true");
	}
	
	@Ignore
	@Test(enabled = true)
	public void method6() {
		System.out.println("method 6, priority = 0, enabled = true");
	}
	
	@Test(invocationCount= 4)
	public void method7() {
		System.out.println("method 7, priority = 0, invocationCount = 4");
	}
	
	@Test()
	public void method8() {
		Assert.assertTrue(false);
		System.out.println("method 8, priority = 0");
	}
	
	@Test(dependsOnMethods = "method8")
	public void method9() {
		System.out.println("method 9, priority = 0");
	}
	
	@Test(dependsOnMethods = "method8", alwaysRun = true)
	public void method10() {
		System.out.println("method 10, priority = 0");
		System.out.println("method 10 - dependsOnMethods = method8, alwaysRun = true");
	}
	
	@Test(dependsOnMethods = "method6", ignoreMissingDependencies=true)
	public void method11() {
		System.out.println("method 11, priority = 0");
		System.out.println("method 11, dependsOnMethods = method6, ignoreMissingDependencies=true");
	}
	
}