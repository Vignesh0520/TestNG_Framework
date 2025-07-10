package org.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

// BaseClass provides a set of common utility methods for Selenium WebDriver-based automation.

public class BaseClass {

	public static WebDriver driver;
	
	public static Actions action;
	
	public static Select select;
	
	public static JavascriptExecutor jsExecutor;

	// Browser Launch
	public static void browserLaunch(String BrowserName, String url) {

		if (BrowserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BrowserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BrowserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.navigate().to(url);
		driver.manage().window().maximize();
	}

	// Quit()
	public static void closeAllBrowserWindow() {
		driver.quit();
	}
	
	// Static Wait - Thread.sleep()
	public static void staticWait(int milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);
	}

	// scrollIntoView(true)
	public static void scrollIntoViewTrue(WebElement element) {

		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);

	}

	// locate the element using xpath
	public static WebElement findElementByXpath(String xpathExpression) {
		WebElement element = driver.findElement(By.xpath(xpathExpression));
		return element;
	}

	// Close()
	public static void closeCurrentBrowserWindow() {
		driver.close();
	}

	// Implicitly Wait
	public static void implicitlyWait(String unit, int quantity) {

		if (unit.equalsIgnoreCase("seconds")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(quantity));
		} else if (unit.equalsIgnoreCase("minutes")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(quantity));
		}

	}

	// Refreshes the Browser
	public static void refresh() {
		driver.navigate().refresh();
	}

	// locate the element using cssSelector
	public static WebElement findElementByCssSelector(String cssSelector) {

		WebElement element = driver.findElement(By.cssSelector(cssSelector));
		return element;

	}

	// Mouse hover action
	public static void moveMouseToElement(WebElement element) {
		action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	// sendKeys()
	public static void textboxInput(WebElement element, String input) {

		element.sendKeys(input);

	}

	// Select by value
	public static void selectOptionByValue(WebElement element, String value) {

		select = new Select(element);
		boolean multiple = select.isMultiple();
		if (multiple == true) {
			System.out.println("Given Select is Multiselect");
		}
		select.selectByValue(value);

	}

	// Select by Index
	public static void selectOptionByIndex(WebElement element, int index) {

		select = new Select(element);
		boolean multiple = select.isMultiple();
		if (multiple == true) {
			System.out.println("Given Select is Multiselect");
		}
		select.selectByIndex(index);

	}

	// Screenshot
	public static void screenshot(String fileName, String filePath) throws IOException {

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(filePath + fileName + ".png");
		FileUtils.copyFile(src, dest);
	}

	// Scrolls to Bottom of the page
	public static void scrollToBottom() {

		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	}

	// Scrolls to Top of the page
	public static void scrollToTop() {

		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(0, 0);");

	}

	// scrollIntoView(false)
	public static void scrollIntoViewFalse(WebElement element) {

		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", element);

	}

	// WebDriverWait
	public static void WaitForElementVisibility(WebElement element, int seconds) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
