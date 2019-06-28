package com.FirstProj.TestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.FirstProj.PageObject.Pg_Login;
import com.FirstProj.PageObject.Pg_Staff;

public class TC_09_VerifySearchFunctionalityInStaffPage extends BaseClass{
	
	@Test(dataProvider="ExcelDataProvider")
	public void TC_SearchStaff(String strUserName, String strPassword,String strStaffName) throws InterruptedException, IOException {
		
		Pg_Login pg_Login = new Pg_Login(driver);
		pg_Login.verifyLoginToApp(strUserName, strPassword);
		
		Pg_Staff pg_Staff = new Pg_Staff(driver);
		boolean boolVerifyStaffSearch = pg_Staff.searchStaff(strStaffName);
		
		if(boolVerifyStaffSearch) {
			
			Assert.assertTrue(true);
			
		}else {		
						
			captureScreen(driver,"TC_SearchStaff");
			Assert.assertTrue(false);
		}
	
	
	}

}
