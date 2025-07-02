package com.forum.bdd.integrations.NG_listners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.forum.bdd.integrations.common_utils.DriverFactory;
import com.forum.bdd.integrations.common_utils.VideoRecord;
import com.forum.bdd.integrations.report_utils.ReportManager;

import io.appium.java_client.android.AndroidDriver;

public class MobileEvent implements ITestListener {
	ThreadLocal<AndroidDriver> androidDriver = new ThreadLocal<AndroidDriver>();
	DriverFactory driverFactory = DriverFactory.getInstance();
	private static final String KEY = "platform";
	private static final String KEY1 = "udid";
	private static final String KEY2 = "systemPort";
	private static final String KEY3 = "deviceName";
	public static String Platform;
	public static String Udid;
	public static String SystemPort;
	public static String DeviceName;
	public static String applicationName = System.getProperty("applicationName");
//	public static String applicationName;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("+++++++++++++++++++onTestStart++++++++++++++++++");

		/*
		 * try { ReportManager.logInfoMobile("Platform: " + Platform);
		 * ReportManager.logInfoMobile("UDID: " + Udid);
		 * ReportManager.logInfoMobile("Appium Port: " + SystemPort);
		 * ReportManager.logInfoMobile("Device Name: " + DeviceName);
		 * driverFactory.setMobileDriver(Platform, Udid, SystemPort, DeviceName); }
		 * catch (Exception e) { e.printStackTrace(); }
		 */
		try {
			VideoRecord.startRecord(arg0.getMethod().getMethodName());
			if (MobileEvent.applicationName.contains("kfhonline")) {
				DriverFactory.getInstance().getMobileDriver().activateApp("com.kfh.kfhonline");
//				DriverFactory.getInstance().getMobileDriver().launchApp();

			} else if (MobileEvent.applicationName.contains("kfhecorp")) {
				DriverFactory.getInstance().getMobileDriver().activateApp("com.kfh.ecorp");
//				DriverFactory.getInstance().getMobileDriver().launchApp();

			} else {
				DriverFactory.getInstance().getMobileDriver().activateApp("com.kfh.tam");
//				DriverFactory.getInstance().getMobileDriver().launchApp();
			}
			System.out.println("---Open App---");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		// System.out.println("Test Success: " +
		// iTestResult.getMethod().getMethodName());
		// ReportManager.logPass(iTestResult.getThrowable().getMessage());
		ReportManager.endCurrentTest();
		// driverFactory.getMobileDriver().close();

		if (MobileEvent.applicationName.contains("kfhonline")) {
//			DriverFactory.getInstance().getMobileDriver().terminateApp("com.kfh.kfhonline");
			DriverFactory.getInstance().getMobileDriver().closeApp();

		} else if (MobileEvent.applicationName.contains("kfhecorp")) {
//			DriverFactory.getInstance().getMobileDriver().terminateApp("com.kfh.ecorp");
			DriverFactory.getInstance().getMobileDriver().closeApp();

		} else {
//			DriverFactory.getInstance().getMobileDriver().terminateApp("com.kfh.tam");
			DriverFactory.getInstance().getMobileDriver().closeApp();
		}

		try {
			VideoRecord.stopRecord();
			System.out.println("---Close App---");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
//		System.out.println("Test Fail: " + iTestResult.getMethod().getMethodName());
		try {
			ReportManager.logMobileScreenshot();
		} catch (IOException | RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		ReportManager.logFail(iTestResult.getThrowable().getMessage());
		if (MobileEvent.applicationName.contains("kfhonline")) {
//			DriverFactory.getInstance().getMobileDriver().terminateApp("com.kfh.kfhonline");
			DriverFactory.getInstance().getMobileDriver().closeApp();

		} else if (MobileEvent.applicationName.contains("kfhonline")) {
//			DriverFactory.getInstance().getMobileDriver().terminateApp("com.kfh.ecorp");
			DriverFactory.getInstance().getMobileDriver().closeApp();

		} else {
//			DriverFactory.getInstance().getMobileDriver().terminateApp("com.kfh.tam");
			DriverFactory.getInstance().getMobileDriver().closeApp();
		}
		System.out.println("---Close App---");

		try {
			VideoRecord.stopRecord();
//			ReportManager.endCurrentTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		driverFactory.getMobileDriver().quit();
	}

	@Override
	public void onStart(ITestContext arg0) {
		Platform = arg0.getCurrentXmlTest().getParameter(KEY);
		Udid = arg0.getCurrentXmlTest().getParameter(KEY1);
		SystemPort = arg0.getCurrentXmlTest().getParameter(KEY2);
		DeviceName = arg0.getCurrentXmlTest().getParameter(KEY3);

		try {
			driverFactory.setMobileDriver(Platform, Udid, SystemPort, DeviceName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		DriverFactory.getInstance().getMobileDriver().findElement(MobileUtil.returnByBasedOnPageNameAndObjectName("Mobilescreen", "phone_Number")).click();
//		DriverFactory.getInstance().getMobileDriver().findElement(MobileUtil.returnByBasedOnPageNameAndObjectName("Mobilescreen", "phone_Number")).sendKeys(ConfigReader.getValue("MobileNumber"));
//		DriverFactory.getInstance().getMobileDriver().findElement(MobileUtil.returnByBasedOnPageNameAndObjectName("Mobilescreen", "btn_otp")).click();
//		DriverFactory.getInstance().getMobileDriver().findElement(MobileUtil.returnByBasedOnPageNameAndObjectName("Mobilescreen", "txt_otp")).sendKeys(ConfigReader.getValue("Otp"));
//		DriverFactory.getInstance().getMobileDriver().findElement(MobileUtil.returnByBasedOnPageNameAndObjectName("Mobilescreen","btn_submit")).click();

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
