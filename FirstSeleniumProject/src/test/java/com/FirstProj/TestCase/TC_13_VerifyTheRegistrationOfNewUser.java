package com.FirstProj.TestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.FirstProj.PageObject.Pg_Login;
import com.FirstProj.PageObject.Pg_Registration;

public class TC_13_VerifyTheRegistrationOfNewUser extends BaseClass{
	
	@Test(dataProvider="ExcelDataProvider")
	public void TC_NewRegistration(String strNewName, String strEmail, String strFPassword, String strCPassword) throws InterruptedException, IOException {
		
		
		Pg_Registration pg_Registration = new Pg_Registration(driver);
		boolean boolVerifyRegistration = pg_Registration.registerNewUser(strNewName, strEmail, strFPassword, strCPassword);
		
		if(boolVerifyRegistration) {
			
			Assert.assertTrue(true);
			
		}else {		
						
			captureScreen(driver,"TC_NewRegistration");
			Assert.assertTrue(false);
		}
	}

}
