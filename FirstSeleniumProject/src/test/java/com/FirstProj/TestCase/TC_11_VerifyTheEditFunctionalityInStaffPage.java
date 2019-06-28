package com.FirstProj.TestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.FirstProj.PageObject.Pg_Login;
import com.FirstProj.PageObject.Pg_Staff;

public class TC_11_VerifyTheEditFunctionalityInStaffPage extends BaseClass{
	
	@Test(dataProvider="ExcelDataProvider")
	public void TC_EditStaff(String strUserName, String strPassword,String strStaffName, String strBranchName, String strBranchCd) throws InterruptedException, IOException {
		
		Pg_Login pg_Login = new Pg_Login(driver);
		pg_Login.verifyLoginToApp(strUserName, strPassword);
		
		Pg_Staff pg_Staff = new Pg_Staff(driver);
		pg_Staff.createStaff(strStaffName, strBranchName, strBranchCd);
		pg_Staff.searchStaff(strStaffName);
		boolean boolVerifyStaffEdit = pg_Staff.editStaff(strStaffName, strBranchName);
		
		if(boolVerifyStaffEdit) {
			
			Assert.assertTrue(true);
			
		}else {		
						
			captureScreen(driver,"TC_EditStaff");
			Assert.assertTrue(false);
		}
	
	
	}

}
