package com.FirstProj.TestCase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.FirstProj.Utilities.ExcelUtility;
import com.FirstProj.Utilities.ReadConfig;
import com.aventstack.extentreports.ExtentReports;

public class BaseClass {
	
	ReadConfig readConfig = new ReadConfig();
	
	public String externalURL = readConfig.getExternalURL();
	public String userName = readConfig.getUserName();
	public String password = readConfig.getPassword();
	public static WebDriver driver;	
	public static Logger logger;	
	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String strBrowser) {
		
		logger = Logger.getLogger("FirstSeleniumProject");
		PropertyConfigurator.configure("log4j.properties");
		
		if(strBrowser.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", readConfig.getChromeDriver());
			driver = new ChromeDriver();
			
		}else if(strBrowser.equals("ie")) {
			
			System.setProperty("webdriver.ie.driver", readConfig.getIEDriver());
			driver = new InternetExplorerDriver();
			
			
		}else if(strBrowser.equals("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxDriver());
			driver = new FirefoxDriver();		
			
		}		
		driver.manage().window().maximize() ;
		driver.get(externalURL);
	}
	
	/*
	 * @AfterMethod public void tearDownMethod() { driver.quit(); }
	 * 
	 * @AfterTest public void tearDownTest() { driver.quit();
	 * }
	 */
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	
	@DataProvider(name = "ExcelDataProvider")
	public static String[][] getExceldata(Method m) throws IOException {
		
	String testDataExcelpath = System.getProperty("user.dir")+"/src/test/java/com/FirstProj/TestData/Test_Data.xlsx";
	//System.out.println("this is the name of method : "+m.getName());
	
	int rowNum = ExcelUtility.getRowCount(testDataExcelpath, m.getName());
	int colNum = ExcelUtility.getCellCount(testDataExcelpath, m.getName(), 1);
		
	String loadData[][] = new String[rowNum][colNum];
	
	for(int i=1; i<=rowNum; i++) {
		
		for(int j=0; j<colNum; j++) {
			
			loadData[i-1][j] = 	ExcelUtility.getCellData(testDataExcelpath, m.getName(), i, j);
		}
	}	
	
	return loadData;
	
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		//Files.copy(source,target);
		FileUtils.copyFile(source,target);
		System.out.println("Screenshot taken");
		
	}

}
