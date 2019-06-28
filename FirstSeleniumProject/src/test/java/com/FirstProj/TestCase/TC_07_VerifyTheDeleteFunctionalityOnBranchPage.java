package com.FirstProj.TestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.FirstProj.PageObject.Pg_Branch;
import com.FirstProj.PageObject.Pg_Login;

public class TC_07_VerifyTheDeleteFunctionalityOnBranchPage extends BaseClass{
	
	@Test(dataProvider="ExcelDataProvider")
	public void TC_DeleteBranch(String strUserName, String strPassword, String strName, String strCode) throws InterruptedException, IOException {
		
		Pg_Login pg_Login = new Pg_Login(driver);
		pg_Login.verifyLoginToApp(strUserName, strPassword);
		
		Pg_Branch pg_Branch = new Pg_Branch(driver);
		pg_Branch.createNewBranch(strName, strCode);
		pg_Branch.searchBranch(strName);
		boolean boolDeleteBranch = pg_Branch.deleteBranch();
		
		if(boolDeleteBranch) {
			
			Assert.assertTrue(true);
			
		}else {		
						
			captureScreen(driver,"TC_DeleteBranch");
			Assert.assertTrue(false);
		}
	}

}
