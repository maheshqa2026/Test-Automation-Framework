package com.ui.tests;

import static com.constants.Browser.CHROME;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {

	protected HomePage homePage;
	Logger logger = LoggerUtility.getLogger(this.getClass());

	private boolean isLamdaTest;

	@Parameters({ "browser", "isLamdaTest", "isHeadless" })
	@BeforeMethod(description = "Load the website")
	public void setup(
			@Optional("chrome") String browser, 
			@Optional("false") boolean isLamdatest,
			@Optional("flase") boolean isHeadless, 
			ITestResult result) {

		this.isLamdaTest = isLamdatest;
		WebDriver lamdaDriver;
		if (isLamdaTest) {

			lamdaDriver = LambdaTestUtility.initializeLamdaTestSession(browser, result.getMethod().getMethodName());

			homePage = new HomePage(lamdaDriver);

		} else {

			// running te test on Local machine
			logger.info("Load the Home page of the Website");

			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
		}
	}

	public BrowserUtility getInstance() {

		return homePage;
	}

	@AfterMethod(description = "tear down the Browser")
	public void testDown() {

		if (isLamdaTest) {

			LambdaTestUtility.quiteSession();

		} else {
			homePage.quit();
		}
	}

}
