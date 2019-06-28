package com.FirstProj.Utilities;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviderUtility {

	
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
}
