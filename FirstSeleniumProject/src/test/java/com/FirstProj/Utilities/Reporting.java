package com.FirstProj.Utilities;

//this is a listner class used to generate reports
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;	
	public ExtentTest test;
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //It will give the time stamp of report generation
		String reportName = "Test-Report"+timeStamp+".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+reportName); //It gives the location for report
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "swati");
		
		htmlReporter.config().setDocumentTitle("Gurukul Staff Management Project"); //It will set my project title
		htmlReporter.config().setReportName("Automation Test Report"); //It will set the name of report I am running
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr) {
		
		test = extent.createTest(tr.getName()); //it makes new entry in report
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));		
		
	}
	
	public void onTestFailure(ITestResult tr) {
		
		test = extent.createTest(tr.getName()); //it makes new entry in report
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));		
		
		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		System.out.println(screenshotPath);
		
		File file = new File(screenshotPath);
		
		if(file.exists()) {
			
			try {
				test.fail("Below is the failure screenshot: "+test.addScreenCaptureFromPath(screenshotPath));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
	public void onTestSkipped(ITestResult tr) {
		
		test = extent.createTest(tr.getName()); //it makes new entry in report
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.BLUE));
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
	}
	

}
