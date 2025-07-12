package com.ui.pages;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JsonUtility;
import com.utility.LoggerUtility;
import static com.utility.PropertiesUtil.*;

public final class HomePage extends BrowserUtility{
	
	
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	
	WebDriver driver;
	public HomePage(Browser browserName ,boolean isHeadless) {
		super(browserName, isHeadless);
		
		//goToWebSite(readProperty(QA, "URL"));
		
		goToWebSite(JsonUtility.readJson(QA).getUrl());
		
		
	}
	
	public HomePage(WebDriver driver) {
		super(driver);
		
		//goToWebSite(readProperty(QA, "URL"));
		
		goToWebSite(JsonUtility.readJson(QA).getUrl());
		
		
	}

	private static final By SIGN_IN_LINK_LOCATOR=By.xpath("//a[contains(text(),\"Sign in\")]");
	
	
	public LoginPage gotoLoginPage(){
		//Page Function u can return Void
		
		logger.info("Trying to perform click to goto sign in Page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		
		LoginPage loginPage= new LoginPage(getDriver());
		
		return loginPage;
		
		
	}

	

}
