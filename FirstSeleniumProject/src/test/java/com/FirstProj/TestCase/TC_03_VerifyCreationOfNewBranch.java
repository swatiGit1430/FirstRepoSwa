package com.FirstProj.TestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.FirstProj.PageObject.Pg_Branch;
import com.FirstProj.PageObject.Pg_Login;

public class TC_03_VerifyCreationOfNewBranch extends BaseClass{
	
	@Test(dataProvider="ExcelDataProvider", dataProviderClass= com.FirstProj.Utilities.DataProviderUtility.class )
	public void TC_CreateNewBranch(String strUserName, String strPassword, String strName, String strCode) throws InterruptedException, IOException {
		
		Pg_Login pg_Login = new Pg_Login(driver);
		pg_Login.verifyLoginToApp(strUserName, strPassword);
		
		Pg_Branch pg_Branch = new Pg_Branch(driver);
		boolean boolverifyBranchCreation = pg_Branch.createNewBranch(strName, strCode);
		
		if(boolverifyBranchCreation) {
			
			Assert.assertTrue(true);
		}else {
			
			captureScreen(driver,"TC_CreateNewBranch");
			Assert.assertTrue(false);
		}
	}

}
