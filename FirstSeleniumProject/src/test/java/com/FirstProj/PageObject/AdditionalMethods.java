package com.FirstProj.PageObject;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdditionalMethods {
	WebDriver driver;
	WebDriverWait wait;
	
	public AdditionalMethods(WebDriver driver) {
		
		this.driver=driver ;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 60);
		
	}
	
	/*****************Web Table Methods**********/
	
	/**
	 * Below method take the table object and returns total number of rows
	 * @param tableObject
	 * @return
	 */
	
	public int getWebTableRowCount(WebElement tableObject) {
		
		List<WebElement> listOfRows = tableObject.findElements(By.tagName("tr"));
		
		int rowCount = listOfRows.size();
		
		return rowCount;
	}
	
	/**
	 * Below method take the table object and returns total number of rows
	 * @param tableObject
	 * @return
	 */
	
	public int getWebTableRowCount(By tableObject) {
		
		WebElement tableObj = driver.findElement(tableObject);
		List<WebElement> listOfRows = tableObj.findElements(By.tagName("tr"));
		
		int rowCount = listOfRows.size();
		
		return rowCount;
	}
	
	/**
	 * Below method search the input string in table object and returns
	 * number of row, where string found
	 * @param tableObject
	 * @return
	 */
		
	public int getRowWithCellTextByValue(WebElement tableObject, int colNum, String searchText) {
		int myRow=0;
		WebElement tableBody = tableObject.findElement(By.tagName("tbody"));
	    List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
	    for(int i=1; i<rows.size();i++) {
	   
	        List<WebElement> td = rows.get(i-1).findElements(By.tagName("td"));
	        ;
	        if (td.size() > 0 && td.get(colNum).findElement(By.tagName("input")).getAttribute("value").equals(searchText)) {
	        	
	        myRow =i;
	          break;
	          
	        }
	    }
	    return myRow;
		
	}
	
	/**
	 * Below method search the cell object based on row & column and returns
	 * cell object
	 * @param tableObject
	 * @return
	 */
	
	public WebElement getCellObject(WebElement tableObject,int rowNum, int colNum) {
		
		WebElement tableBody = tableObject.findElement(By.tagName("tbody"));
		
		 List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
		 
		// int rowNum = rows.size();
		 
		 List<WebElement> cellObj = rows.get(rowNum).findElements(By.tagName("td"));
		 
		 return cellObj.get(colNum);
		
	}
	
	/**
	 * Below method search the input string in table object and returns
	 * number of row, where string found
	 * @param tableObject
	 * @return
	 */
	
	public int getRowWithCellText(WebElement tableObject, int colNum, String searchText) {
		int myRow=0;
		WebElement tableBody = tableObject.findElement(By.tagName("tbody"));
	    List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
	    for(int i=1; i<rows.size();i++) {
	   
	        List<WebElement> td = rows.get(i-1).findElements(By.tagName("td"));
	        ;
	        if (td.size() > 0 && td.get(colNum).getText().equals(searchText)) {
	        	
	        myRow =i;
	          break;
	          
	        }
	    }
	    return myRow;
		
	}
	
	/**
	 * Below method checks if element present in DOM or not
	 * returns true or false
	 * @param tableObject
	 * @return
	 */
	
	public boolean isElementPresent(By inputObject) {
		
		if (driver.findElements(inputObject).size()>0) {
			
			return true;
		}else {
			
			return false;
		}
		
	}
	
	/**
	 * Below method wait until the element is visible
	 * @param locator
	 * @return
	 */
	
	
	public void waitUntilVisible(WebElement locator) {
		
		wait.until(ExpectedConditions.visibilityOf(locator));
		
	}
	
	/**
	 * Below method clear the input field when the element is visible
	 * @param locator
	 * @return
	 */
	
	public void clearWhenVisible(WebElement locator) {
		
		try {			
			wait.until(ExpectedConditions.visibilityOf(locator));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			locator.clear();
			}catch(Exception e) {
				
				System.out.println("Element Not clickable - " + locator.toString().substring(70));
	            e.printStackTrace();
			}
	}
	
	/**
	 * Below method click the input field when the element is visible
	 * @param locator
	 * @return
	 */
	public void clickWhenVisible(WebElement locator) {
		
		try {		
			wait.until(ExpectedConditions.visibilityOf(locator));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			locator.click();
			}catch(Exception e) {
				
				System.out.println("Element Not clickable - " + locator.toString().substring(70));
	            e.printStackTrace();
			}
	}
	
	/**
	 * Below method enter value in the input field when the element is visible
	 * @param locator, valueToEnter
	 * @return
	 */
	
	public void enterValueWhenVisible(WebElement locator, String valueToEnter) {
		
		try {
						
			wait.until(ExpectedConditions.visibilityOf(locator));
			locator.sendKeys(valueToEnter);
			}catch(Exception e) {
				
				System.out.println("Element Not clickable - " + locator.toString().substring(70));
	            e.printStackTrace();
			}
	}
	
	/**
	 * Below method checks if the element is visible or not
	 * @param locator
	 * @return
	 */
	
	public boolean isElementVisible(WebElement locator) {
		
		try {
				
		wait.until(ExpectedConditions.visibilityOf(locator));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return true;
		}catch(Exception e) {
			
			System.out.println("Element Not found - " + locator.toString().substring(70));
            e.printStackTrace();
            return false;
		}
		
	}
	
	/**
	 * Below method checks if alert is present 
	 * @param 
	 * @return
	 */
	
	public boolean isAlert() {
		
		try {
			driver.switchTo().alert();
			return true;
		}catch(NoAlertPresentException ex){
			return false;
			}
	}
	
	/**
	 * Below method accepts the alert 
	 * @param 
	 * @return
	 */
	
	public boolean acceptAlert() {
		try {
			driver.switchTo().alert().accept();
			return true;
		}catch(NoAlertPresentException ex){
			return false;
			}
		
	}
	
	/**
	 * Below method returns the innerHTML text for element 
	 * @param 
	 * @return
	 */
	
	public String getTextValue(By inputObject) {
		
		return driver.findElement(inputObject).getText();
	}
	
	/**
	 * Below method waits 
	 * @param 
	 * @return
	 */
	
	public void doWait() {
		
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
	}
	
	/**
	 * Below method selects the dropdown by its value 
	 * @param 
	 * @return
	 */
	
	public void selectValueByLabel(WebElement selectLocator, String strLabel) {
		
		Select option = new Select(selectLocator);
		option.selectByVisibleText(strLabel);
		
	}

}
