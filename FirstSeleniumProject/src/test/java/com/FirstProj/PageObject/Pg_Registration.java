package com.FirstProj.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pg_Registration {
	
	WebDriver driver;
	AdditionalMethods additionalMethods;
	
	public Pg_Registration(WebDriver driver) {
		
		this.driver=driver ;
		PageFactory.initElements(driver, this);
		additionalMethods = new AdditionalMethods(this.driver);
	}
	
	
	
	@FindBy(xpath="//a[text()='Register a new account']") WebElement linkToRegister;
	@FindBy(xpath="//div[@class=\"well ng-scope\"]//h1") WebElement pageTitle;
	@FindBy(xpath="//input[@name=\"login\"]") WebElement inputName;
	@FindBy(xpath="//input[@name=\"email\"]") WebElement inputEmail;
	@FindBy(xpath="//input[@name=\"password\"]") WebElement password;
	@FindBy(xpath="//input[@name=\"confirmPassword\"]") WebElement confirmPassword;
	@FindBy(xpath="//button[text()='Register']") WebElement btnRegister;
	By confirmError = By.xpath("//*[@class=\"form-group\"]/following-sibling::div/div/p[@class=\"help-block ng-scope\"]");
	@FindBy(xpath="(//*[@class=\"form-group\"]/following-sibling::div/div)[1]") WebElement errMsgList;
	@FindBy(xpath="//form[@name=\"form\"]/descendant::div[2]") WebElement errMsgListOnName;
	By confirmErrOnName = By.xpath("//form[@name=\"form\"]/descendant::div/p[@class=\"help-block ng-scope\"]");
	By errorMsgOnRegister = By.xpath("//div/h1/following-sibling::div[@class=\"alert alert-danger ng-scope\"]");
	@FindBy(xpath="//button[text()='Register' and @disabled=\"disabled\"]") WebElement btnRegisterDisable;
	
	public boolean registerNewUser(String strName, String strEmail, String strPassword, String CnfPassword) {
		boolean verifyTitle=false, verifyErrorMsgName=false, verifyErrorMsgEmail=false, verifySuccessRegistration=false, verifyRegisterBtn=true;
		this.additionalMethods.clickWhenVisible(linkToRegister);
		this.additionalMethods.waitUntilVisible(pageTitle);
		String pgTitle = pageTitle.getText();
		if(pgTitle.equals("Registration")) {
			
			verifyTitle=true;
		}
		
		this.additionalMethods.enterValueWhenVisible(inputName, strName);
		List<WebElement> list_errMsgOnName = errMsgListOnName.findElements(By.tagName("p"));
		
		if(this.additionalMethods.isElementPresent(confirmErrOnName)) {
			
			String errMasgOnName = this.additionalMethods.getTextValue(confirmErrOnName);
			
			for(WebElement listErrorOnName : list_errMsgOnName) {
				
				if(listErrorOnName.getText().contains(errMasgOnName)) {
					verifyErrorMsgName=true;
					break;
				}
			}
			
			}else {
				
				System.out.println("Valid value in Name field");
			}
			
		
		this.additionalMethods.enterValueWhenVisible(inputEmail, strEmail);
		
		List<WebElement> list_errMsg = errMsgList.findElements(By.tagName("p"));
		
		if(this.additionalMethods.isElementPresent(confirmError)) {
			
			String errMasg1 = this.additionalMethods.getTextValue(confirmError);
			
	
	for(WebElement listError : list_errMsg) {
		
		if(listError.getText().contains(errMasg1)) {
			verifyErrorMsgEmail=true;
			break;
		}
	}
	
	}else {
		
		System.out.println("Valid value in Email field");
	}
	
		this.additionalMethods.enterValueWhenVisible(password, strPassword);
		this.additionalMethods.enterValueWhenVisible(confirmPassword, CnfPassword);
		
		if(this.additionalMethods.isElementVisible(btnRegisterDisable)) {
			
			verifyRegisterBtn=false;
		}else {
		this.additionalMethods.clickWhenVisible(btnRegister);
		
		if(this.additionalMethods.isElementPresent(errorMsgOnRegister)) {
			
			String errMsg = this.additionalMethods.getTextValue(errorMsgOnRegister);
			
			if(errMsg.contains("The password and its confirmation do not match!")) {
				
				System.out.println("The password and its confirmation do not match!");
			}else if(errMsg.contains("Registration failed!")) {
				
				System.out.println("Registration failed!-Please try again later");
			}
			
			else {
				
				verifySuccessRegistration=true;				
			}
		}
		}
		
		if(verifyTitle & verifyErrorMsgName & verifySuccessRegistration & verifyRegisterBtn) {
			return true;
		}else {
			return false;
		}
	}
	
	
	

}
