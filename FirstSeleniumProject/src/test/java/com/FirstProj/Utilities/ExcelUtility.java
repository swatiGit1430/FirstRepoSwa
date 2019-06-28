package com.FirstProj.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static int getRowCount(String strExcelFile, String strExcelSheet) throws IOException {
		
		fis = new FileInputStream(strExcelFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(strExcelSheet);
		
		int row_count = ws.getLastRowNum();
		wb.close();
		fis.close();
		
		return row_count;		
		
	}
	
	public static int getCellCount(String strExcelFile, String strExcelSheet,int rowNum) throws IOException {
		
		fis = new FileInputStream(strExcelFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(strExcelSheet);
		row = ws.getRow(rowNum);
		int cell_count =row.getLastCellNum();
		wb.close();
		fis.close();
		return cell_count;
		
	}
	
	
	
	public static String getCellData(String strExcelFile, String strExcelSheet, int rowNum, int colNum) throws IOException {
		
		fis = new FileInputStream(strExcelFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(strExcelSheet);
		row = ws.getRow(rowNum);
		cell = row.getCell(colNum);
		
		String data;
		try {
			
			DataFormatter formatter = new DataFormatter();
			
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		}catch(Exception e) {
			
			data = " ";
		}
		wb.close();
		fis.close();
		return data;
		
	}
	
	public static void setCellData(String strExcelFile, String strExcelSheet, int rowNum, int colNum, String data) throws IOException {
		
		fis = new FileInputStream(strExcelFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(strExcelSheet);
		row = ws.getRow(rowNum);
		cell =row.createCell(colNum);
		cell.setCellValue(data);
		fos = new FileOutputStream(strExcelFile);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
	}

}
