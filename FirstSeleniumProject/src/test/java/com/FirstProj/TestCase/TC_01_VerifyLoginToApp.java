package com.FirstProj.TestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.FirstProj.PageObject.Pg_Login;
import com.FirstProj.Utilities.Reporting;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TC_01_VerifyLoginToApp extends BaseClass {
	ExtentReports extent;	
	ExtentTest test;
	
	Reporting reporting = new Reporting();
	
	@Test(dataProvider="ExcelDataProvider")	
	public void TC_LoginTest(String strUserName, String strPassword) throws IOException {
		
		Pg_Login pg_Login = new Pg_Login(driver);
		
		
		//boolean boolVerifylogin = pg_Login.verifyLoginToApp(userName, password);
		boolean boolVerifylogin = pg_Login.verifyLoginToApp(strUserName, strPassword);
		
		if(boolVerifylogin) {
			logger.info("Successfully login to Gurukula Application");
			Assert.assertTrue(true);				
		}else {
								
			captureScreen(driver,"TC_LoginTest");
			logger.info("Failed to login in Gurukula Application");
			Assert.assertTrue(false);
		}
		
		boolean boolVerifyLogout = pg_Login.verifyLogout();
		
		if(boolVerifyLogout) {
			logger.info("Successfully loggedout from Gurukula Application");
			Assert.assertTrue(true);				
		}else {
								
			captureScreen(driver,"TC_LoginTest");
			logger.info("Failed to log out in Gurukula Application");
			Assert.assertTrue(false);
		}
	}

}
