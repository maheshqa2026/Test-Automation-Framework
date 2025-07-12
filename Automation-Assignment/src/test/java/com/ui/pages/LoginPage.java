package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class LoginPage extends BrowserUtility {

private	static final By EMAIL_TEXT = By.id("email");

private static final By PASSWORD_TEXT = By.id("passwd");

private static final By SIGN_BUTTON = By.id("SubmitLogin");

	public LoginPage(WebDriver driver) {
		super(driver);

	}

	public MyAccountPage doLoginWith(String emailAddress,String Password) {
		
		enterText(EMAIL_TEXT, emailAddress);
		enterText(PASSWORD_TEXT, Password);
		clickOn(SIGN_BUTTON);
		
		MyAccountPage myAccountPage=new MyAccountPage(getDriver());
		return myAccountPage;
	}
}
