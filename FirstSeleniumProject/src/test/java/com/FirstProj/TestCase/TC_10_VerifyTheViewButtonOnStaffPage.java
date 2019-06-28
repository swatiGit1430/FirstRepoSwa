package com.FirstProj.TestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.FirstProj.PageObject.Pg_Login;
import com.FirstProj.PageObject.Pg_Staff;

public class TC_10_VerifyTheViewButtonOnStaffPage extends BaseClass{
	
	@Test(dataProvider="ExcelDataProvider")
	public void TC_ViewStaff(String strUserName, String strPassword, String strStaffName,String strBranchName, String strBranchCd) throws InterruptedException, IOException {
		
		Pg_Login pg_Login = new Pg_Login(driver);
		pg_Login.verifyLoginToApp(strUserName, strPassword);
		
		Pg_Staff pg_Staff = new Pg_Staff(driver);
		pg_Staff.createStaff(strStaffName, strBranchName, strBranchCd);
		boolean boolVerifyStaffView = pg_Staff.viewStaff(strStaffName);
		
		if(boolVerifyStaffView) {
			
			Assert.assertTrue(true);
			
		}else {		
						
			captureScreen(driver,"TC_ViewStaff");
			Assert.assertTrue(false);
		}
	
	
	}

}
