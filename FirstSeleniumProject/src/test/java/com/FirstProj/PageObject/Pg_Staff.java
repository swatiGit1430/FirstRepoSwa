package com.FirstProj.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pg_Staff {
	
	WebDriver driver;
	AdditionalMethods additionalMethods;
	
	public Pg_Staff(WebDriver driver) {
		
		this.driver=driver ;
		PageFactory.initElements(driver, this);
		additionalMethods = new AdditionalMethods(driver);
	}
	
	
	@FindBy(xpath="//*[@id=\"navbar-collapse\"]/ul/li/descendant::span[text()='Entities']") 
	WebElement EntitiesDD;
	@FindBy(xpath="//*[@class=\"dropdown pointer ng-scope open\"]//ul/descendant::span[text()='Staff']") 
	WebElement linkToStaff;
	@FindBy(xpath="//button//span[text()='Create a new Staff']") WebElement btnCreateStaff;
	@FindBy(xpath="//*[@id=\"myStaffLabel\"]") WebElement titlePopup;
	@FindBy(xpath="//input[@name=\"name\"]") WebElement name;
	@FindBy(xpath="//Select[@name=\"related_branch\"]") WebElement selBranchDD;
	@FindBy(xpath="//button[@type=\"submit\"]//span[text()='Save']") WebElement btnSave;
	@FindBy(xpath="//div[@class=\"table-responsive\"]/table") WebElement tblSearchResult;
	
	public boolean createStaff(String strStaffName, String strBranchName, String strBranchCd) throws InterruptedException {
		
		Pg_Branch pg_Branch = new Pg_Branch(this.driver);
		pg_Branch.createNewBranch(strBranchName, strBranchCd);
		Thread.sleep(5000);
		this.additionalMethods.waitUntilVisible(EntitiesDD);
		this.additionalMethods.clickWhenVisible(EntitiesDD);
		this.additionalMethods.clickWhenVisible(linkToStaff);
		this.additionalMethods.clickWhenVisible(btnCreateStaff);
		//String staffPopupTitle = titlePopup.getText();
		
		this.additionalMethods.enterValueWhenVisible(name, strStaffName);
		this.additionalMethods.selectValueByLabel(selBranchDD, strBranchName);
		this.additionalMethods.clickWhenVisible(btnSave);
		this.additionalMethods.waitUntilVisible(tblSearchResult);		
		int rowCount = this.additionalMethods.getRowWithCellText(tblSearchResult, 1, strStaffName);
		
		if(rowCount >0) {
			
			return true;
			
		}else {
			return false;
		}
	
		
	}
	
	@FindBy(xpath="//*[@id=\"searchQuery\"]") WebElement searchBox;
	@FindBy(xpath="//button/span[text()='Search a Staff']") WebElement btnStaffSearch;
	
	
	
	public boolean searchStaff(String strStaffName) {
		
		this.additionalMethods.clickWhenVisible(EntitiesDD);
		this.additionalMethods.clickWhenVisible(linkToStaff);
		this.additionalMethods.clearWhenVisible(searchBox);
		this.additionalMethods.enterValueWhenVisible(searchBox, strStaffName);
		this.additionalMethods.clickWhenVisible(btnStaffSearch);
		this.additionalMethods.waitUntilVisible(tblSearchResult);
		int rowCount = this.additionalMethods.getRowWithCellText(tblSearchResult, 1, strStaffName);
		
		if(rowCount >0) {
			
			return true;
			
		}else {
			return false;
		}
		
	}
	
	@FindBy(xpath="//button/span[text()='View']") WebElement btnView;
	@FindBy(xpath="//table[@class=\"table table-striped\"]") WebElement tblViewStaff;
	@FindBy(xpath="//button/span[text()='Back']") WebElement btnBack;	
	
	public boolean viewStaff(String strSearchText) {
		
		this.additionalMethods.waitUntilVisible(tblViewStaff);
		this.additionalMethods.waitUntilVisible(btnView);
		this.additionalMethods.clickWhenVisible(btnView);
		
		int row = additionalMethods.getRowWithCellTextByValue(tblViewStaff, 1, strSearchText);
		
		this.additionalMethods.waitUntilVisible(btnBack);
		this.additionalMethods.clickWhenVisible(btnBack);
		
		if(row>0) {
			return true;
		}else {
			return false;
		}	
	}
	
	@FindBy(xpath="(//button/span[text()='Edit'])[1]") WebElement btnEdit;	
	
	
	public boolean editStaff(String strStaffName, String strBranchName) throws InterruptedException {
		
		this.additionalMethods.clickWhenVisible(btnEdit);
		String updatedName = strStaffName+"Updated";
		this.additionalMethods.clearWhenVisible(name);
		this.additionalMethods.enterValueWhenVisible(name, updatedName);
		this.additionalMethods.clickWhenVisible(btnSave);
		
		this.additionalMethods.waitUntilVisible(tblViewStaff);
		Thread.sleep(3000);
		int row = additionalMethods.getRowWithCellText(tblViewStaff, 1, updatedName);
		
		if(row>0) {
			return true;
		}else {
			return false;
		}	
	}
	
	@FindBy(xpath="(//button[@class=\"btn btn-danger btn-sm\"])[1]") WebElement btnDelete;
	@FindBy(xpath="//form[@name=\"deleteForm\"]/descendant::button[@class=\"btn btn-danger\"]") WebElement confirmDeletion;
	
	public boolean deleteStaff() throws InterruptedException {
		boolean verifyDeleteRecord=false;		
		this.additionalMethods.waitUntilVisible(tblSearchResult);
		WebElement newRow = this.additionalMethods.getCellObject(tblSearchResult, 0,0);
		
		String newBranchID = newRow.findElement(By.tagName("a")).getText();
		
		int totalRow = this.additionalMethods.getWebTableRowCount(tblSearchResult);
		if(totalRow>0) {
			this.additionalMethods.clickWhenVisible(btnDelete);
			
			this.additionalMethods.waitUntilVisible(confirmDeletion);
			this.additionalMethods.clickWhenVisible(confirmDeletion);
			
			boolean deletedRowExist = searchStaff(newBranchID);
			
			if(!deletedRowExist) {
				
				verifyDeleteRecord= true;
			}		
					
		}
		
		return verifyDeleteRecord;
	}

	

}
