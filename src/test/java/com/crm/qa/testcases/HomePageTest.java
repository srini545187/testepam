package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTest extends TestBase {
	ExtentTest logger; 
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;

	public HomePageTest() {
		super();
	}

	//test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	
	//after each test case -- close the browser
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(priority=1)
	public void verifyHomePageTitleTest(){
		logger = extent.startTest("HomePageTest Starting");
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO","Home page title not matched");
		logger.log(LogStatus.INFO, "HomePageTest is PASSED");
		
	}
	
	@Test(priority=2)
	public void verifyUserNameTest(){
		logger = extent.startTest("verifyUserNameTest Starting");
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
		logger.log(LogStatus.INFO, "verifyUserNameTest is PASSED");
	}
	/*
	@Test(priority=3)
	public void verifyContactsLinkTest(){
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}*/
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		if(result.getStatus()==ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "TEST CASE FAILED IS "+ result.getTestClass().getName() + " . " +result.getName()); //to add name in extent report
			logger.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = TestUtil.getScreenshot( result.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getTestClass().getName() + " . " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			logger.log(LogStatus.PASS, "Test Case PASSED IS " + result.getTestClass().getName() + " . " + result.getName());

		}
		
		
		extent.endTest(logger); //ending test and ends the current test and prepare to create html report
		driver.quit();
		extent.flush();
		
		/*try {
			TestUtil.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.quit();
		extent.endTest(logger);
		extent.flush();*/
	}
	
	

}
