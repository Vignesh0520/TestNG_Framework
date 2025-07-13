package org.page.saucedemo;

import org.base.BaseClass; // Base Class
import org.openqa.selenium.WebElement;
import org.page.saucedemo.pojo.SauceDemoLoginPage; // POJO Class for Sauce Demo Login Page
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SauceDemo_LoginPage_Validation extends BaseClass {

	// Test case 1: Valid login with standard_user
	@SuppressWarnings("deprecation")
	@Test(groups = "HardAssert", enabled = true, priority = 1)
	@Parameters({ "username1", "password", "browser_name" })
	public void testCase1(@Optional("standard_user") String username1, @Optional("secret_sauce") String password,
			@Optional("firefox") String browserName) throws InterruptedException {

		browserLaunch(browserName, "https://www.saucedemo.com/");
		SauceDemoLoginPage loginPage = new SauceDemoLoginPage(getDriver());
		loginPage.getUsername().sendKeys(username1);
		loginPage.getPassword().sendKeys(password);
		loginPage.getLoginBtn().click();

		staticWait(500); // Optional: Use WebDriverWait for production code

		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		String actualUrl = getDriver().getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "TestCase1 Failed: URL mismatch");
		System.out.println("Test Case 1 - Thread ID: " + Thread.currentThread().getId());
		System.out.println("Testcase 1 Validated - Hard Assert");
		closeAllBrowserWindow();
	}

	// Test case 2: Login with locked_out_user, expect error
	@SuppressWarnings("deprecation")
	@Test(groups = "HardAssert", enabled = true, priority = 0)
	@Parameters({ "username2", "password", "browser_name_2" })
	public void testCase2(@Optional("locked_out_user") String username2, @Optional("secret_sauce") String password,
			@Optional("edge") String browserName2) throws InterruptedException {

		browserLaunch(browserName2, "https://www.saucedemo.com/");
		SauceDemoLoginPage loginPage = new SauceDemoLoginPage(getDriver());
		staticWait(300); // Wait for page to fully load

		loginPage.getUsername().sendKeys(username2);
		loginPage.getPassword().sendKeys(password);
		loginPage.getLoginBtn().click();

		staticWait(1000); // Wait for error to appear

		WebElement error = findElementByXpath("//h3[@data-test='error']");
		Assert.assertTrue(error.isDisplayed(), "TestCase2 Failed: Error not displayed");
		System.out.println("Test Case 2 - Thread ID: " + Thread.currentThread().getId());
		System.out.println("Testcase 2 Validated - Hard Assert");
		closeAllBrowserWindow();
	}

	// Test case 3: Intentionally fail with incorrect expected URL
	@SuppressWarnings("deprecation")
	@Test(groups = "HardAssert", enabled = true, priority = -1)
	@Parameters({ "browser_name" })
	public void testCase3(@Optional("chrome") String browserName) {

		browserLaunch(browserName, "https://www.saucedemo.com/");
		SauceDemoLoginPage loginPage = new SauceDemoLoginPage(getDriver());

		loginPage.getUsername().sendKeys("standard_user");
		loginPage.getPassword().sendKeys("secret_sauce");
		loginPage.getLoginBtn().click();

		String expectedUrl = "https://www.saucedemo.com/wrong-url.html"; // Intentional fail
		String actualUrl = getDriver().getCurrentUrl();
		Assert.assertFalse(expectedUrl.equals(actualUrl), "TestCase3 Failed: URLs should not match");
		System.out.println("Test Case 3 - Thread ID: " + Thread.currentThread().getId());
		System.out.println("Testcase 3 Validated - Hard Assert");
		closeAllBrowserWindow();
	}

	// Test case 4: Soft assert URL check
	@SuppressWarnings("deprecation")
	@Test(groups = "SoftAssert", priority = 5)
	@Parameters({ "browser_name" })
	public void testCase4(@Optional("chrome") String browserName) {

		browserLaunch(browserName, "https://www.saucedemo.com/");
		SauceDemoLoginPage loginPage = new SauceDemoLoginPage(getDriver());

		loginPage.getUsername().sendKeys("locked_out_user");
		loginPage.getPassword().sendKeys("secret_sauce");
		loginPage.getLoginBtn().click();

		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		String actualUrl = getDriver().getCurrentUrl();

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualUrl, expectedUrl, "TestCase4 Failed: URL mismatch");
		System.out.println("Test Case 4 - Thread ID: " + Thread.currentThread().getId());
		System.out.println("Testcase 4 Validated - Soft Assert");

		closeAllBrowserWindow();
		softAssert.assertAll(); // Required for SoftAssert to take effect
	}
}
