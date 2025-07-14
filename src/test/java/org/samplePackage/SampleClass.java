package org.samplePackage;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;

import java.io.IOException;

import org.base.BaseClass;
import org.listeners.IRetryAnalyzerClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.AfterSuite;

public class SampleClass extends BaseClass {

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before Suite");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class\n");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method");
	}

	@Test(priority = 2, retryAnalyzer = IRetryAnalyzerClass.class)
	public void testMethod1() {
		System.out.println("TestCase1, Priority = 2");
		System.out.println("Executing testMethod1");
		Assert.assertTrue(false);
	}

	@Test(priority = 1)
	public void testMethod2() {
		System.out.println("TestCase2, Priority = 1");
		System.out.println("Executing testMethod2");
	}

	@Test
	public void testMethod3() throws IOException {
		System.out.println("TestCase3, Priority = 0");
		// find broken links from given webpage
		// findBrokenLinks("https://www.facebook.com/");
		System.out.println("Executing testMethod3");
	}

	@Test
	public void testMethod4() {
		System.out.println("TestCase4, Priority = 0");
		System.out.println("Executing testMethod4");
	}

	@Test
	public void testMethod5() {
		System.out.println("TestCase5, Priority = 0");
		System.out.println("Executing testMethod5");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method\n");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("After Class");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("After Test");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite");
	}

}