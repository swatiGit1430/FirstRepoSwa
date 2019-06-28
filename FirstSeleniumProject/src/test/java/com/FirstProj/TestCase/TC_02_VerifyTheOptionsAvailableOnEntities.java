package com.FirstProj.TestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.FirstProj.PageObject.Pg_Entities;
import com.FirstProj.PageObject.Pg_Login;

public class TC_02_VerifyTheOptionsAvailableOnEntities extends BaseClass{
	
	@Test(dataProvider="ExcelDataProvider", dataProviderClass= com.FirstProj.Utilities.DataProviderUtility.class )
	public void TC_VerifyEntities(String strUserName, String strPassword) throws InterruptedException, IOException {
		
		Pg_Login pg_Login = new Pg_Login(driver);
		pg_Login.verifyLoginToApp(strUserName, strPassword);
		
		Pg_Entities pg_Entities = new Pg_Entities(driver);
		boolean boolVerifyEntitiesDD = pg_Entities.verifyEntitesDD();
		
		if(boolVerifyEntitiesDD) {
			
			Assert.assertTrue(true);
		}else {
			
			captureScreen(driver,"TC_VerifyEntities");
			Assert.assertTrue(false);
		}
		
		
	}

}
