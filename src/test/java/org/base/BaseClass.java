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

public class BaseClass {

	private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
	private static final ThreadLocal<Actions> threadActions = new ThreadLocal<>();
	private static final ThreadLocal<Select> threadSelect = new ThreadLocal<>();
	private static final ThreadLocal<JavascriptExecutor> threadJsExecutor = new ThreadLocal<>();

	// Get driver for current thread
	public WebDriver getDriver() {
		return threadDriver.get();
	}

	public void browserLaunch(String browserName, String url) {
		WebDriver driver;
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}

		threadDriver.set(driver);
		driver.manage().window().maximize();
		driver.get(url);
	}

	public void closeAllBrowserWindow() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			threadDriver.remove();
		}
	}

	public void staticWait(int milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);
	}

	public void scrollIntoViewTrue(WebElement element) {
		threadJsExecutor.set((JavascriptExecutor) getDriver());
		threadJsExecutor.get().executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public WebElement findElementByXpath(String xpath) {
		return getDriver().findElement(By.xpath(xpath));
	}

	public void closeCurrentBrowserWindow() {
		getDriver().close();
	}

	public void implicitlyWait(String unit, int quantity) {
		if (unit.equalsIgnoreCase("seconds")) {
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(quantity));
		} else if (unit.equalsIgnoreCase("minutes")) {
			getDriver().manage().timeouts().implicitlyWait(Duration.ofMinutes(quantity));
		}
	}

	public void refresh() {
		getDriver().navigate().refresh();
	}

	public WebElement findElementByCssSelector(String css) {
		return getDriver().findElement(By.cssSelector(css));
	}

	public void moveMouseToElement(WebElement element) {
		threadActions.set(new Actions(getDriver()));
		threadActions.get().moveToElement(element).perform();
	}

	public void textboxInput(WebElement element, String input) {
		element.sendKeys(input);
	}

	public void selectOptionByValue(WebElement element, String value) {
		threadSelect.set(new Select(element));
		threadSelect.get().selectByValue(value);
	}

	public void selectOptionByIndex(WebElement element, int index) {
		threadSelect.set(new Select(element));
		threadSelect.get().selectByIndex(index);
	}

	public void screenshot(String fileName, String filePath) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) getDriver();
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(filePath + fileName + ".png");
		FileUtils.copyFile(src, dest);
	}

	public void scrollToBottom() {
		threadJsExecutor.set((JavascriptExecutor) getDriver());
		threadJsExecutor.get().executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public void scrollToTop() {
		threadJsExecutor.set((JavascriptExecutor) getDriver());
		threadJsExecutor.get().executeScript("window.scrollTo(0, 0);");
	}

	public void scrollIntoViewFalse(WebElement element) {
		threadJsExecutor.set((JavascriptExecutor) getDriver());
		threadJsExecutor.get().executeScript("arguments[0].scrollIntoView(false);", element);
	}

	public void WaitForElementVisibility(WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
