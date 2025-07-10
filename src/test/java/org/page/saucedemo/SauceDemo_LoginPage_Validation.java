package org.page.saucedemo;

import org.base.BaseClass; // Base Class
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebElement;
import org.page.saucedemo.pojo.SauceDemoLoginPage; // POJO Class for Sauce Demo Login Page
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

// Main Implementation class for Login Page Validation
public class SauceDemo_LoginPage_Validation extends BaseClass {

	SauceDemoLoginPage sauceDemoLoginPage;

	// Test case 1: Valid login with standard_user, expects successful navigation
	@Test(groups = "HardAssert", enabled = true, priority = 1)
	public void testCase1() {
		browserLaunch("chrome", "https://www.saucedemo.com/v1/");
		sauceDemoLoginPage = new SauceDemoLoginPage(driver);
		WebElement username = sauceDemoLoginPage.getUsername();
		WebElement password = sauceDemoLoginPage.getPassword();
		WebElement loginBtn = sauceDemoLoginPage.getLoginBtn();
		username.sendKeys("standard_user");
		password.sendKeys("secret_sauce");
		loginBtn.click();
		String expectedUrl = "https://www.saucedemo.com/v1/inventory.html";
		@Nullable
		String actualtUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualtUrl, expectedUrl); // Hard assert for URL match
		System.out.println("Testcase 1 Validated - Hard Assert");
		closeAllBrowserWindow();
	}

	// Test case 2: Login with locked_out_user, expects error message
	@Test(groups = "HardAssert", enabled = true, priority = 0)
	public void testCase2() throws InterruptedException {
		browserLaunch("chrome", "https://www.saucedemo.com/v1/");
		sauceDemoLoginPage = new SauceDemoLoginPage(driver);
		WebElement username = sauceDemoLoginPage.getUsername();
		WebElement password = sauceDemoLoginPage.getPassword();
		WebElement loginBtn = sauceDemoLoginPage.getLoginBtn();
		username.sendKeys("locked_out_user");
		password.sendKeys("secret_sauce");
		loginBtn.click();
		staticWait(200); // Wait for error message to appear
		WebElement error = findElementByXpath("//h3[@data-test='error']");
		boolean displayed = error.isDisplayed();
		Assert.assertTrue(displayed); // Hard assert for error display
		System.out.println("Testcase 2 Validated - Hard Assert");
		closeAllBrowserWindow();
	}

	// Test case 3: Negative test, expects URL mismatch (wrong expected URL)
	@Test(groups = "HardAssert", enabled = true, priority = -1)
	public void testCase3() {
		browserLaunch("chrome", "https://www.saucedemo.com/v1/");
		sauceDemoLoginPage = new SauceDemoLoginPage(driver);
		WebElement username = sauceDemoLoginPage.getUsername();
		WebElement password = sauceDemoLoginPage.getPassword();
		WebElement loginBtn = sauceDemoLoginPage.getLoginBtn();
		username.sendKeys("standard_user");
		password.sendKeys("secret_sauce");
		loginBtn.click();

		// Negative Testing: Intentionally wrong expected URL
		String expectedUrl = "https://www.saucedemo.com/inventory.html"; // Wrong URL
		String actualtUrl = driver.getCurrentUrl();
		boolean equals = expectedUrl.equals(actualtUrl);
		Assert.assertFalse(equals); // Hard assert for URL mismatch
		System.out.println("Testcase 3 Validated - Hard Assert");
		closeAllBrowserWindow();
	}

	// Test case 4: Soft assert example, login with locked_out_user and check URL
	@Test(groups = "SoftAssert", priority = 5)
	public void testCase4() {
		browserLaunch("chrome", "https://www.saucedemo.com/v1/");
		sauceDemoLoginPage = new SauceDemoLoginPage(driver);
		WebElement username = sauceDemoLoginPage.getUsername();
		WebElement password = sauceDemoLoginPage.getPassword();
		WebElement loginBtn = sauceDemoLoginPage.getLoginBtn();
		username.sendKeys("locked_out_user");
		password.sendKeys("secret_sauce");
		loginBtn.click();

		// Negative Testing: Check if URL matches expected (should not)
		String expectedUrl = "https://www.saucedemo.com/v1/inventory.html";
		String actualtUrl = driver.getCurrentUrl();
		boolean equals = expectedUrl.equals(actualtUrl);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(equals); // Soft assert for URL match
		System.out.println("Testcase 4 Validated - Soft Assert");
		closeAllBrowserWindow();
		softAssert.assertAll(); // Assert all soft assertions
	}

}
