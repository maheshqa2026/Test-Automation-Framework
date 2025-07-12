package com.ui.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static com.constants.Browser.*;
import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;   // <-- Use Log4j2 Logger here
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;

@Listeners({com.ui.listners.TestListner.class})
public class LoginTest extends TestBase{

	Logger logger = LoggerUtility.getLogger(this.getClass());
  

    @Test(description = "Verify login with valid user credential", groups = { "e2e", "sanity" }, 
          dataProviderClass = com.ui.dataprovider.LoginDataProvider.class, 
          dataProvider = "LoginTestDataProvider")
    public void loginTest(User user) {
        assertEquals(homePage.gotoLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
                "Mahesh khole");
    }

    @Test(description = "Verify login with valid user credential", groups = { "e2e", "sanity" }, 
          dataProviderClass = com.ui.dataprovider.LoginDataProvider.class, 
          dataProvider = "LoginTestCSVDataProvider")
    public void loginCSVTest(User user) {
        assertEquals(homePage.gotoLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
                "Mahesh khole");
    }

    @Test(description = "Verify login with valid user credential with", groups = { "e2e", "sanity" }, 
          dataProviderClass = com.ui.dataprovider.LoginDataProvider.class, 
          dataProvider = "LoginTestExcelDataProvider",
          retryAnalyzer = com.ui.listners.MyRetryAnalyzer.class)
    public void loginExcelTest(User user) {
        
        

        assertEquals(homePage.gotoLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
                "Mahesh khole");
       
    }
}
