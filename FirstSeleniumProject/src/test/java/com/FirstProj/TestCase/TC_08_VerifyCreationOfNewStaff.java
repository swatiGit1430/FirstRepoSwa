package com.FirstProj.TestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.FirstProj.PageObject.Pg_Login;
import com.FirstProj.PageObject.Pg_Staff;

public class TC_08_VerifyCreationOfNewStaff extends BaseClass{
	
	@Test(dataProvider="ExcelDataProvider")
	public void TC_CreateStaff(String strUserName, String strPassword,String strStaffName, String strName, String strCode) throws InterruptedException, IOException {
		
		Pg_Login pg_Login = new Pg_Login(driver);
		pg_Login.verifyLoginToApp(strUserName, strPassword);
		
		Pg_Staff pg_Staff = new Pg_Staff(driver);
		boolean boolVerifyNewStaffCreation = pg_Staff.createStaff(strStaffName, strName, strCode);
		
		if(boolVerifyNewStaffCreation) {
			
			Assert.assertTrue(true);
			
		}else {		
						
			captureScreen(driver,"TC_CreateStaff");
			Assert.assertTrue(false);
		}
	}

}
