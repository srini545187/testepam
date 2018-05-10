package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest extends TestBase{
	ExtentTest logger; 
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();	
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		logger = extent.startTest("loginPageTitleTest Starting");		
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "#1 Free CRM for Any Business: Online Customer Relationship Software abcbb");
		logger.log(LogStatus.INFO, "loginPageTitleTest is PASSED");
	}
	/*
	@Test(priority=2)
	public void crmLogoImageTest(){
		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag);
	}*/
	
	@Test(priority=3)
	public void loginTest(){
		logger = extent.startTest("LoginTest Starting");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		logger.log(LogStatus.INFO, "LoginTest is PASSED");
		
	}
	
	
	
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
