package org.factory_annotation;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.page.saucedemo.pojo.SauceDemoLoginPage;
import org.testng.annotations.Test;

public class SampleTestClass_For_FactoryAnnotation extends BaseClass {

	private String userName;
	private String pass;

	public SampleTestClass_For_FactoryAnnotation(String username, String password) {
		this.userName = username;
		this.pass = password;
	}

	@Test
	public void sauceDemoLoginPageValidation() {
		browserLaunch("chrome", "https://www.saucedemo.com/");
		SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(getDriver());
		WebElement username = sauceDemoLoginPage.getUsername();
		WebElement password = sauceDemoLoginPage.getPassword();
		WebElement loginBtn = sauceDemoLoginPage.getLoginBtn();
		username.sendKeys(userName);
		password.sendKeys(pass);
		loginBtn.click();
		System.out.println("username : " + userName);
		System.out.println("password : " + pass);
		System.out.println("sauce demo login page validated");
		closeAllBrowserWindow();
	}

}
