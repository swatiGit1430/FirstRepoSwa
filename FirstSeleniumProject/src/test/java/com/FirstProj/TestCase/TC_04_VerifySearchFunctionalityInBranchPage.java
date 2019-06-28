package com.FirstProj.TestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.FirstProj.PageObject.Pg_Branch;
import com.FirstProj.PageObject.Pg_Login;

public class TC_04_VerifySearchFunctionalityInBranchPage extends BaseClass{
	
	@Test(dataProvider="ExcelDataProvider")	
	public void TC_SearchBranch(String strUserName, String strPassword, String strName, String strCode) throws InterruptedException, IOException {
		
		Pg_Login pg_Login = new Pg_Login(driver);
		pg_Login.verifyLoginToApp(strUserName, strPassword);
		
		Pg_Branch pg_Branch = new Pg_Branch(driver);
		pg_Branch.createNewBranch(strName, strCode);
		
		boolean boolBranchSearch = pg_Branch.searchBranch(strName);
		
		if(boolBranchSearch) {
			
			Assert.assertTrue(true);
			
		}else {
			
						
			captureScreen(driver,"TC_SearchBranch");
			Assert.assertTrue(false);
		}
	}

}
