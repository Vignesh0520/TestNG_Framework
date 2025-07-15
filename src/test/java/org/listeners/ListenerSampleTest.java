package org.listeners;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ListenerSampleTest {

	@Test
	public void testMethod1() {
		System.out.println("Executing testMethod1...");
		Assert.fail("Failing intentionally to test retry.");
	}

	@Test
	public void testMethod2() {
		System.out.println("Executing testMethod2...");
		Assert.assertTrue(true);
	}

}
