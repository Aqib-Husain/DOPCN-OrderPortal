package com.dopCN.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.dopCN.BaseClass.BaseClass;
import com.dopCN.pageObjects.HomePage;
import com.dopCN.utilities.CaptureScreenShot;

public class HomePageTest extends BaseClass{
	
	//WebDriver driver;

	HomePage homePage;	
	SoftAssert softAsert = new SoftAssert();
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		initilizeBrowzer();
		homePage = new HomePage();
		
	}
	
	@Test(priority = 1)
	public void validateLogoOnHomeP() {
		eLogger = extent.createTest("Check Logo on HomePage");
		eLogger.info("Checking logo");
		homePage.checkHomeLogo();		
		Assert.assertTrue(homePage.checkHomeLogo());
		eLogger.info("Validation completed");
		logger.debug("Debuggin");
	}
	
	@Test(priority = 2, enabled = true)
	public void validateLanguageDropdown() throws InterruptedException {
		eLogger = extent.createTest("Check language Drop down");
		eLogger.info("Clicking on Language Dropdown");
		homePage.clickOnlanguageDropDown();
		homePage.selectLangDdValues("English");
		eLogger.info("Selecting English in drop down");
		Thread.sleep(5000);
		softAsert.assertEquals(homePage.homeMenuText(), "Home");
		softAsert.assertEquals(homePage.homeMenuSignInText(),"Sign In");
		
		softAsert.assertAll("Translation is not happening");
		
	}
	
	@Test(priority = 3, enabled = true)
	public void validateLogin() {
		eLogger = extent.createTest("Check Login");
		eLogger.info("Clicking on Sign in link");
		homePage.clickOnSignInLink();
		eLogger.info("Entering the User credentials");
		homePage.enterUserName(userName);
		homePage.enterPassword(password);
		eLogger.info("Clicked on login button");
		homePage.clickOnLoginButton();
		if (driver.getPageSource().contains("/cart-preview")) {
			Assert.assertTrue(true);
			eLogger.pass("Login Successful");
			eLogger.info("Login Successful");
		} else {
			Assert.assertTrue(false);
			eLogger.fail("Login Failed");
			eLogger.info("Login Failed");
			CaptureScreenShot.getScreenShot();
			eLogger.info("ScreenShot : ", MediaEntityBuilder.createScreenCaptureFromPath("./ScreenShots/failedTC.png").build());

		}
		
	}
	
	@AfterClass
	public void tearDown() {
		eLogger.info("Closing the browser");
		closeSession();
		extent.flush();
	}

}
