package com.FirstProj.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pg_Entities {
	
WebDriver driver;
AdditionalMethods additionalMethods;
	
	public Pg_Entities(WebDriver driver) {
		
		this.driver=driver ;
		PageFactory.initElements(driver, this);
		additionalMethods = new AdditionalMethods(driver);
	}
	
	@FindBy(xpath="//*[@id=\"navbar-collapse\"]/ul/li/descendant::span[text()='Entities']") 
	WebElement EntitiesDD;
	@FindBy(xpath="//ul[@class=\"dropdown-menu\"]") 
	WebElement EntitiesDDMenu;
	
	
	public boolean verifyEntitesDD() throws InterruptedException {
		
		this.additionalMethods.clickWhenVisible(EntitiesDD);
		
		List<WebElement> entityList = EntitiesDDMenu.findElements(By.tagName("li"));
		
		if(entityList.get(0).getText().contains("Branch") && entityList.get(1).getText().contains("Staff")) {
			
			return true;
		}else {
			
			return false;
		}
	}

}
