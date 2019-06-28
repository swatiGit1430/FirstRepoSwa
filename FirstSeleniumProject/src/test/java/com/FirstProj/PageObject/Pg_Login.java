package com.FirstProj.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pg_Login {
	
	WebDriver driver;
	AdditionalMethods additionalMethods;
	
	public Pg_Login(WebDriver driver) {
		
		this.driver=driver ;
		PageFactory.initElements(driver, this);
		additionalMethods = new AdditionalMethods(this.driver);
	}
	
	
	/** Create Objects here*/
	
	@FindBy(xpath="//a[text()='login']") WebElement linkToLogin;
	@FindBy(xpath="//input[@id='username']") WebElement username;
	@FindBy(xpath="//input[@id='password']") WebElement password;
	@FindBy(xpath="//button[text()='Authenticate']") WebElement submitBtn;
	@FindBy(xpath="//div[@class=\"well ng-scope\"]//h1") WebElement pgTitle;
	@FindBy(xpath="//a//span[text()='Entities']") WebElement entityHome;
	
	@FindBy(xpath="//li/a/descendant::span[text()='Account']") WebElement account;
	@FindBy(xpath="//*[@id=\"navbar-collapse\"]/ul/li/descendant::span[text()='Log out']") WebElement logoutLink;	
	
	/** Create Methods here
	 * @throws InterruptedException */
	
	public boolean verifyLoginToApp(String UserName, String Password) {
		this.additionalMethods.waitUntilVisible(linkToLogin);
		linkToLogin.click();
		this.additionalMethods.waitUntilVisible(username);
		username.sendKeys(UserName);
		this.additionalMethods.waitUntilVisible(password);
		password.sendKeys(Password);
		submitBtn.click();
		this.additionalMethods.waitUntilVisible(entityHome);
		String title = pgTitle.getText();
		System.out.println("title"+title);	
		if(title.contains("Welcome to Gurukula!")) {
			return true;
		}else {
			
			return false;
		}
			
		
	}
	
	public boolean verifyLogout() {
		
		this.additionalMethods.clickWhenVisible(account);
		this.additionalMethods.clickWhenVisible(logoutLink); 
		this.additionalMethods.waitUntilVisible(linkToLogin);
		
		if(this.additionalMethods.isElementVisible(linkToLogin)) {
			return true;
		}else {
			
			return false;
		}
		
	}


}
