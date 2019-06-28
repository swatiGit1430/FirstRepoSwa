package com.FirstProj.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pg_Branch {
	
	WebDriver driver;
	AdditionalMethods additionalMethods;
	
public Pg_Branch(WebDriver driver) {
		
		this.driver=driver ;
		PageFactory.initElements(driver, this);
		additionalMethods = new AdditionalMethods(driver);
	}
	   

	@FindBy(xpath="//*[@id=\"navbar-collapse\"]/ul/li/descendant::span[text()='Entities']") 
	WebElement EntitiesDD;
	@FindBy(xpath="//*[@class=\"dropdown pointer ng-scope open\"]//ul/descendant::span[text()='Branch']") 
	WebElement linkToBranch;
	@FindBy(xpath="//button//span[text()='Create a new Branch']") WebElement btnCreateBranch;
	@FindBy(xpath="//*[@id=\"myBranchLabel\"]") WebElement titlePopup;
	@FindBy(xpath="//input[@name=\"name\"]") WebElement name;
	@FindBy(xpath="//input[@name=\"code\"]") WebElement code;
	@FindBy(xpath="//button[@type=\"submit\"]//span[text()='Save']") WebElement btnSave;	
	@FindBy(xpath="//button[@type=\"button\"]//span[text()='Cancel']") WebElement btnCancel;
	@FindBy(xpath="(//*[@class=\"form-group\"]/following-sibling::div/div)[1]") WebElement errMsgList;
	//@FindBy(xpath="(//*[@class=\"form-group\"]/following-sibling::div/div/p[@class=\"help-block ng-scope\"])[1]") WebElement currentError;
	By currentError =By.xpath("(//*[@class=\"form-group\"]/following-sibling::div/div/p[@class=\"help-block ng-scope\"])[1]");
	@FindBy(xpath="(//*[@class=\"form-group\"]/following-sibling::div/div)[2]") WebElement errMsgListCode;
	//@FindBy(xpath="(//*[@class=\"form-group\"]/following-sibling::div/div/p[@class=\"help-block ng-scope\"])[2]") WebElement currentErrorCode;
	By currentErrorCode = By.xpath("(//*[@class=\"form-group\"]/following-sibling::div/div/p[@class=\"help-block ng-scope\"])[2]");
	
	
	
	public boolean createNewBranch(String str_Name, String str_Code) throws InterruptedException {
		boolean verifyFormTitle, verifyErrorMsgName=false, verifyErrorMsgCode=false;
		this.additionalMethods.clickWhenVisible(EntitiesDD);
		this.additionalMethods.clickWhenVisible(linkToBranch);
		this.additionalMethods.waitUntilVisible(btnCreateBranch);
		this.additionalMethods.clickWhenVisible(btnCreateBranch);
		this.additionalMethods.waitUntilVisible(titlePopup);
		String popupTitle = titlePopup.getText();
		if(popupTitle.contains("Create or edit a Branch")) {
			verifyFormTitle=true;
		}else {
			verifyFormTitle=false;
		}
		this.additionalMethods.clickWhenVisible(name);
		this.additionalMethods.enterValueWhenVisible(name, str_Name);
		
		List<WebElement> list_errMsg = errMsgList.findElements(By.tagName("p"));
		
		//if(currentError.isDisplayed()) 
		if(this.additionalMethods.isElementPresent(currentError))
		{
		
		String errMasg1 = this.additionalMethods.getTextValue(currentError);
				//currentError.getText();
		
		for(WebElement listError : list_errMsg) {
			
			if(listError.getText().contains(errMasg1)) {
				verifyErrorMsgName=true;
				break;
			}
		}
		
		}else {
			
			System.out.println("Valid value in Name field");
		}
		this.additionalMethods.clickWhenVisible(code);
		this.additionalMethods.enterValueWhenVisible(code, str_Code);
		code.sendKeys(Keys.TAB);
		List<WebElement> list_errMsgCd = errMsgListCode.findElements(By.tagName("p"));
		
		//if(currentErrorCode.isDisplayed()) 
			if(this.additionalMethods.isElementPresent(currentErrorCode)){
			
			String errMasg2 = this.additionalMethods.getTextValue(currentErrorCode);
					
					//currentErrorCode.getText();
			
			for(WebElement listErrorCd : list_errMsgCd) {
				
				if(listErrorCd.getText().contains(errMasg2)) {
					verifyErrorMsgCode=true;
					break;
				}
			}
			
		}else {
			
			System.out.println("Valid value in Code field");
			this.additionalMethods.clickWhenVisible(btnSave);
			
		}
		/*
		 * btnCancel.click(); Thread.sleep(5000); btnCreateBranch.click();
		 */
		if(verifyFormTitle & verifyErrorMsgName & verifyErrorMsgCode) {
			
			return true;
		}else {
			
			return false;
		}
		
	}
	
	@FindBy(xpath="//input[@id=\"searchQuery\"]") WebElement searchQuery;
	@FindBy(xpath="//button/span[text()='Search a Branch']") WebElement btnSearch;
	@FindBy(xpath="//div[@class=\"table-responsive\"]/table") WebElement tblSearchResult;
	
	
	public boolean searchBranch(String strSearchKey) throws InterruptedException {
		this.additionalMethods.clearWhenVisible(searchQuery);
		this.additionalMethods.enterValueWhenVisible(searchQuery, strSearchKey);
		this.additionalMethods.clickWhenVisible(btnSearch);
		this.additionalMethods.waitUntilVisible(tblSearchResult);
		
		int rowReturns = this.additionalMethods.getWebTableRowCount(tblSearchResult);
		System.out.println("rowReturns on search"+rowReturns);
		if(rowReturns>1) {
			
			return true;
		}else {
			
			return false;
		}
		
	}
	
	@FindBy(xpath="//button/span[text()='View']") WebElement btnView;
	@FindBy(xpath="//table[@class=\"table table-striped\"]") WebElement tblViewBranch;
	@FindBy(xpath="//button/span[text()='Back']") WebElement btnBack;	
	
	public boolean viewBranch(String strSearchText) {
		
		this.additionalMethods.clickWhenVisible(btnView);
		this.additionalMethods.waitUntilVisible(tblViewBranch);
		int row = additionalMethods.getRowWithCellTextByValue(tblViewBranch, 1, strSearchText);
		
		if(row>0) {
			return true;
		}else {
			return false;
		}	
	}
	
	@FindBy(xpath="(//button/span[text()='Edit'])[1]") WebElement btnEdit;	
	
	
	public boolean editBranch(String strName, String strCode) throws InterruptedException {
		this.additionalMethods.clickWhenVisible(btnEdit);
		
		String updatedName = strName+"Updated";
		this.additionalMethods.clearWhenVisible(name);
		this.additionalMethods.enterValueWhenVisible(name, updatedName);
		this.additionalMethods.clearWhenVisible(code);
		this.additionalMethods.enterValueWhenVisible(code, strCode+"U");
		this.additionalMethods.clickWhenVisible(btnSave);
		this.additionalMethods.waitUntilVisible(tblViewBranch);
		
		int row = additionalMethods.getRowWithCellText(tblViewBranch, 1, updatedName);
		
		if(row>0) {
			return true;
		}else {
			return false;
		}	
	}
	
	@FindBy(xpath="(//button[@class=\"btn btn-danger btn-sm\"])[1]") WebElement btnDelete;
	@FindBy(xpath="//form[@name=\"deleteForm\"]/descendant::button[@class=\"btn btn-danger\"]") WebElement confirmDeletion;
	
	
	
	public boolean deleteBranch() throws InterruptedException {
		boolean verifyDeleteRecord=false;	
		this.additionalMethods.waitUntilVisible(tblSearchResult);
		WebElement newRow = this.additionalMethods.getCellObject(tblSearchResult, 0,0);
		
		String newBranchID = newRow.findElement(By.tagName("a")).getText();
		
		int totalRow = this.additionalMethods.getWebTableRowCount(tblSearchResult);
		if(totalRow>0) {
			
			this.additionalMethods.clickWhenVisible(btnDelete);
			this.additionalMethods.waitUntilVisible(confirmDeletion);
			this.additionalMethods.clickWhenVisible(confirmDeletion);
			
			boolean deletedRowExist = searchBranch(newBranchID);
			
			if(!deletedRowExist) {
				
				verifyDeleteRecord= true;
			}		
					
		}
		
		return verifyDeleteRecord;
	}

}
