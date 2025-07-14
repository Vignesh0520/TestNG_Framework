package org.base;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jspecify.annotations.Nullable;
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
	
	// Method to find the broken links in the given url
	@SuppressWarnings("deprecation")
	public void findBrokenLinks(String url) throws IOException {
		int validLinks = 0, brokenLinks = 0, totalLinks = 0;
		browserLaunch("chrome", url);
		List<WebElement> elements = getDriver().findElements(By.tagName("a")); // Find all anchor tags
		for (WebElement element : elements) {
			@Nullable
			String link = element.getAttribute("href"); // Get href attribute
			totalLinks++;
			if ((link != null) && (!link.isEmpty())) { // Check if link is not null or empty
				URL url_link = new URL(link); // Create URL object
				URLConnection openConnection = url_link.openConnection(); // Open connection
				HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection; // Cast to HttpURLConnection
				httpURLConnection.connect(); // Connect to the URL
				int responseCode = httpURLConnection.getResponseCode(); // Get HTTP response code
				if (responseCode >= 400) {
					System.out.println(link + " is broken link");
					brokenLinks++;
				} else {
					System.out.println(link + " is valid link");
					validLinks++;
				}
			} else {
				System.out.println(link + " is null or empty"); // Print if link is null or empty
			}

		}
		System.out.println("Broken Link count : " + brokenLinks);
		System.out.println("Valid Link count : " + validLinks);
		System.out.println("Total links count : " + totalLinks);
		closeAllBrowserWindow();
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
