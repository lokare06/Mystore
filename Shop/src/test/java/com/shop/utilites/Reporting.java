 
 
	package com.shop.utilites;

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
	import com.aventstack.extentreports.reporter.ExtentReporter;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

	public class Reporting extends TestListenerAdapter {
		public ExtentSparkReporter htmlReporter;
		public ExtentReporter extent;
		public ExtentTest logger;
		
		public void onstart(ITestContext testContext) throws IOException
		{
			String timeStamp=new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
			String repName="Test-Report-"+timeStamp+".html";
			htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName);
			htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
			
			ExtentReports extent=new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Host name","localhost");
			extent.setSystemInfo("Environment","QA");
			extent.setSystemInfo("user","Deepali");
			
			htmlReporter.config().setDocumentTitle("Mystore Test Project");
			htmlReporter.config().setReportName("Functional Test Report");
			// htmlReporter.config().setTestViewChartLocation(ChartLocation);
			htmlReporter.config().setTheme(Theme.DARK);
					}
			public void onTestSuccess(ITestResult tr)
			{
				logger=((ExtentReports) extent).createTest(tr.getName());
				logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));
		}
			public void onTestFailure(ITestResult tr)
			{
				logger=((ExtentReports) extent).createTest(tr.getName());
				logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
			String screenshotPath=System.getProperty("user.dir")+"\\Screenshot\\"+tr.getName()+".png";
			File f=new File(screenshotPath);
			if(f.exists())
			{
				logger.fail("Screenshot is below:"+logger.addScreenCaptureFromPath(screenshotPath));
			}
			}
			public void onTestSkipped(ITestResult tr) {
				logger=((ExtentReports) extent).createTest(tr.getName());
				logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
			}
			
			public void onFinish(ITestContext testContext)
			{
				((ExtentReports) extent).flush();
			}
			
			
	}



