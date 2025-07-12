package com.utility;

import static com.constants.Env.QA;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.constants.Browser;

import freemarker.template.SimpleDate;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);// initialize the instance variable driver

	}

	public BrowserUtility(Browser browserName) {
		logger.info("Launchin Browser for " + browserName);
		if (browserName == Browser.CHROME) {

			driver.set(new ChromeDriver());

		} else if (browserName == Browser.EDGE) {

			driver.set(new EdgeDriver());
		}

		else if (browserName == Browser.SAFARI) {

			driver.set(new SafariDriver());
		} else {
			logger.error("Invalid Browser Name ...Please select chrome ,edge,safari only ");

			System.err.println("Invalid Browser Name ...Please select chrome ,edge,safari only");
		}

	}

	public BrowserUtility(String browserName) {
		logger.info("Launchin Browser for " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {

			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {

			driver.set(new EdgeDriver());
		}

		else if (browserName.equalsIgnoreCase("safari")) {

			driver.set(new SafariDriver());
		} else {
			logger.error("Invalid Browser Name ...Please select chrome ,edge,safari only ");
			System.err.println("please select valid browsername...like cheome ,edge,safari only...");
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
        logger.info("Launching Browser: " + browserName);

        switch (browserName) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }
                driver.set(new ChromeDriver(chromeOptions));
                break;

            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) {
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("disable-gpu");
                }
                driver.set(new EdgeDriver(edgeOptions));
                break;

            case SAFARI:
                if (isHeadless) {
                    logger.warn("Safari does not support headless mode. Launching normally.");
                }
                driver.set(new SafariDriver());
                break;

            default:
                logger.error("Invalid Browser Name. Please select CHROME, EDGE, or SAFARI.");
                throw new IllegalArgumentException("Invalid browser: " + browserName);
        }
    }

	public void goToWebSite(String url) {
		logger.info("Visiting the website  : " + url);

		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the Browser Window");
		driver.get().manage().window().maximize();

	}

	public void clickOn(By locator) {
		logger.info("Finding element with the Locator  : " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info(" element found and performing click : ");
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with the Locator  : " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info(" element found and Enter Text : " + textToEnter);
		element.sendKeys(textToEnter);
	}

	public String getVisibleText(By locator) {
		logger.info("Finding element with the Locator  : " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("element found and returning the visible text : " + element.getText());
		return element.getText();

	}

	public String takeScreenshot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = formatter.format(date);

		String fileName = name + "-" + timeStamp + ".png";
		String directoryPath = System.getProperty("user.dir") + File.separator + "screenshot";
		File screenshotFile = new File(directoryPath, fileName);

		// Create directory if it doesn't exist
		new File(directoryPath).mkdirs();

		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return screenshotFile.getAbsolutePath();
	}
	public void quit() {
		driver.get().quit();
	}

}
