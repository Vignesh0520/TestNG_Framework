package org.page.saucedemo.pojo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Page Object Model class for SauceDemo Login Page.
public class SauceDemoLoginPage {
	
	//Constructor initializes web elements using PageFactory.
	public SauceDemoLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Username input field
	@FindBy(xpath = "//input[@placeholder='Username']")
	WebElement username;
	
	// Password input field
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement password;
	
	// Login button
	@FindBy(xpath = "//input[@id='login-button']")
	WebElement loginBtn;

	public WebElement getUsername() {
		return username;
	}

	public void setUsername(WebElement username) {
		this.username = username;
	}

	public WebElement getPassword() {
		return password;
	}

	public void setPassword(WebElement password) {
		this.password = password;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	public void setLoginBtn(WebElement loginBtn) {
		this.loginBtn = loginBtn;
	}
	
}
