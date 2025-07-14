package org.page.practicetestautomation.practice_test_login;
import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.page.practicetestautomation.practice_test_login.pojo.PracticeTestLoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class PracticeTestLoginPage_Validation extends BaseClass {

	@Test(dataProvider = "credentials")
	public void Validate_PracticeTestLoginPage(String username, String pass) {
		browserLaunch("chrome", "https://practicetestautomation.com/practice-test-login/");
		PracticeTestLoginPage practiceTestLoginPage = new PracticeTestLoginPage(getDriver());
		WebElement userName = practiceTestLoginPage.getUsername();
		WebElement password = practiceTestLoginPage.getPassword();
		WebElement submitBtn = practiceTestLoginPage.getSubmitBtn();
		WebElement usernameLabel = practiceTestLoginPage.getUsernameLabel();
		scrollIntoViewTrue(usernameLabel);
		userName.sendKeys(username);
		password.sendKeys(pass);
		submitBtn.click();
		closeAllBrowserWindow();
	}

	@DataProvider(name = "credentials")
	public Object[][] getCredentials() {
		Object[][] object = new Object[3][2];
		object[0][0] = "student";
		object[0][1] = "Password123";
		
		object[1][0] = "incorrectUser";
		object[1][1] = "Password123";
		
		object[2][0] = "student";
		object[2][1] = "incorrectPassword";
		return object;
	}

}
