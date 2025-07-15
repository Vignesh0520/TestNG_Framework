package org.factory_annotation;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.page.saucedemo.pojo.SauceDemoLoginPage;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class SampleTestClass_For_FactoryAnnotation extends BaseClass {

	private String userName;
	private String pass;

	public SampleTestClass_For_FactoryAnnotation(String username, String password) {
		this.userName = username;
		this.pass = password;
	}

	@Epic("Login Module")
	@Story("Valid Login Test")
	@Description("Verifies login with valid credentials")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Vignesh Raja")
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
