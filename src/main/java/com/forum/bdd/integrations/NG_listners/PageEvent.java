package com.forum.bdd.integrations.NG_listners;

import java.io.IOException;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.forum.bdd.integrations.common_utils.BrowserFactory;
import com.forum.bdd.integrations.common_utils.VideoRecord;
import com.forum.bdd.integrations.report_utils.ReportManager;

public class PageEvent implements ITestListener {
	
	public static String strBrowser;

	
	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("+++++++++++++++++++++onTestStart++++++++++++++++++++");
		
		strBrowser = System.getProperty("Browser");
		System.out.println("Execution started @ " + strBrowser + " browser & for type : Web UI");
		try {

			
			VideoRecord.startRecord(arg0.getMethod().getMethodName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		

		try {
			BrowserFactory.getInstance().getPlaywright().close();
			VideoRecord.stopRecord();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {

		// retry.retry(iTestResult);
		System.out.println("+++++++++++++++++++++onTestFailure++++++++++++++++++++");
		ReportManager.logFail(iTestResult.getThrowable().toString());

 		try {
			ReportManager.logScreenshotInfo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
	}
		ReportManager.endCurrentTest();
	

		try {

			VideoRecord.stopRecord();
			BrowserFactory.getInstance().getPlaywright().close();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

		// ExtentLogger.skip("<b><i>" + result.getThrowable().toString() + "</i></b>");
		String logText = "<b>" + arg0.getMethod().getMethodName() + " is skipped.</b>";
		Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);

	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext arg0) {

		// strBrowser = arg0.getCurrentXmlTest().getParameter("browser");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}
}
