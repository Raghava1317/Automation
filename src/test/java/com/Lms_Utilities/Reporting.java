package com.Lms_Utilities;

//Listener class used to generates Extent Reports


import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Reporting extends TestListenerAdapter
{
 
	//Declaring the Object for ExtentHtmlReporter
	public ExtentHtmlReporter htmlReporter;
	//Declaring the Object for ExtentReports
	public ExtentReports extent;
	//Declaring the Objects for Logger
	public ExtentTest logger;
	
	//Creating Method for Extent Report
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-"+timeStamp+".html";
		
		//Initializing the ExtentHtmlReporter and Setting path for storing the Extent Report
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		//Reading the extent-config.xml File
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		//Initializing the ExtentReports
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "raghava");
		//Setting the Document Title 
		htmlReporter.config().setDocumentTitle("InetBanking Test Project");
		//Setting the Report Name
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		//Setting the Theme
		htmlReporter.config().setTheme(Theme.DARK);
		
	}
	
	/*When TestNG Passed this Listeners Method gets called Automatically and
	 *  Displays the report in Green color */
	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));
		
	}
	
	/*When TestNG Failed this Listeners Method gets called Automatically and
	 *  Displays the report in Red color */
	
	
	public void onTestFailure(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		
	}
	
	/*When TestNG Skipped this Listeners Method gets called Automatically and
	 *  Displays the report in Orange color */
	
	
	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		
	}
	
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
