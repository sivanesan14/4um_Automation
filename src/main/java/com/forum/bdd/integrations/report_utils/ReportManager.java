package com.forum.bdd.integrations.report_utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.forum.bdd.integrations.common_utils.Constants;
import com.forum.bdd.integrations.common_utils.DriverFactory;

public class ReportManager {

	public static ExtentReports extent;
	public static Map<Long, ExtentTest> testMap = new HashMap<>();
	public static Map<String, ExtentTest> extentMap = new HashMap<>();
	public static String str_Execution_TYPE = System.getProperty("execType");

	public static void startReport() {

		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
			String dateStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());

			ExtentSparkReporter spark;

			if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
				spark = new ExtentSparkReporter(Constants.extent_reportPath);
				extent.attachReporter(spark);
				// spark.config().setEncoding("utf-8");
				spark.config().setTheme(Theme.DARK);
				spark.config().setDocumentTitle("4um Web Report");
				spark.config().setReportName("4um");

//				extent.setSystemInfo("Organization", "4um");
//				extent.setSystemInfo("Employee",
//						"<b> Test User1</b>" + " " + Constants.ICON_SOCIAL_LINKEDIN + " " + Constants.ICON_SOCIAL_GITHUB);
//				extent.setSystemInfo("Domain", "Engineering (IT - Software)");
//				extent.setSystemInfo("Skill", "Test Automation Engineer");

			} else if (str_Execution_TYPE.equalsIgnoreCase("Mobile_UI")) {
				spark = new ExtentSparkReporter(Constants.extent_mobilereportPath);
				extent.attachReporter(spark);
				// spark.config().setEncoding("utf-8");
				spark.config().setTheme(Theme.DARK);
				spark.config().setDocumentTitle("4um Mobile Report");
				spark.config().setReportName("4um");

//				extent.setSystemInfo("Organization", "4um");
//				extent.setSystemInfo("Employee",
//						"<b> Test User1</b>" + " " + Constants.ICON_SOCIAL_LINKEDIN + " " + Constants.ICON_SOCIAL_GITHUB);
//				extent.setSystemInfo("Domain", "Engineering (IT - Software)");
//				extent.setSystemInfo("Skill", "Test Automation Engineer");

			}
		}

	}

	public static void startTest(String testName, String categories) {
		if (extentMap.containsKey(testName)) {
			extent.removeTest(extentMap.get(testName));
			testName = "Rerun - " + testName;
		}
		ExtentTest test = extent.createTest(testName);
		test.assignCategory(categories);
		testMap.put(Thread.currentThread().getId(), test);
		extentMap.put(testName, test);
	}

	public static void logScreenshot() throws IOException { //
		getCurrentTest().addScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshot());
		Media mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshot())
				.build();
		getCurrentTest().fail("", mediaModel);

	}

	public static void logScreenshotInfo() throws IOException { //
		getCurrentTest().addScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshot());
		Media mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshot())
				.build();
		getCurrentTest().pass("", mediaModel);

	}

	public static void logMobileScreenshot() throws IOException {

		Media mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(
				ScreenshotUtil.takeScreenshot(DriverFactory.getInstance().getMobileDriver())).build();
		getCurrentTest().fail("", mediaModel);

	}

	public static void logPass(String message) {
		getCurrentTest().log(Status.PASS, message);

	}

	public static void logFail(String message) {
		getCurrentTest().log(Status.FAIL, message);
	}

	public static void logInfo(String message) {
		getCurrentTest().log(Status.INFO, message);

	}

	public static void logInfoAPIWEB(String message) {
		getCurrentTest().log(Status.INFO, message);

	}

	public static void endCurrentTest() {
		getCurrentTest().getExtent().flush();

		testMap.remove(Thread.currentThread().getId());
	}

	public static ExtentTest getCurrentTest() {
		return testMap.get(Thread.currentThread().getId());

	}

	public static void endReport() {

		extent.flush();
	}

	// =============================================API
	// REPORT=======================================================

	public static ExtentSparkReporter htmlReporterAPI;
	public static ExtentReports extentAPI;
	public static Map<Long, ExtentTest> testMapAPI = new HashMap<>();
	public static ExtentTest testSuiteAPI;
	public static Map<String, ExtentTest> extentMapAPI = new HashMap<>();

	public static void startApiReport() {

		if (htmlReporterAPI == null) {
			String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
			String dateStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
			htmlReporterAPI = new ExtentSparkReporter(
					System.getProperty("user.dir") + "/Execution_Reports/API_Reports/Extent_API.html");
			extentAPI = new ExtentReports();
			extentAPI.attachReporter(htmlReporterAPI);
			extentAPI.setSystemInfo("Host Name", "4um");
			extentAPI.setSystemInfo("Environment", "Stage");
			extentAPI.setSystemInfo("User Name", "Siva");
			System.out.println();
			htmlReporterAPI.config().setDocumentTitle("4um API Report");
			// Name of the report
			htmlReporterAPI.config().setReportName("4um");

			// Dark Theme
			htmlReporterAPI.config().setTheme(Theme.DARK);

		}
	}

	public static void startTestAPI(String apiTestSuiteName, String categories) {
//		if (extentMapAPI.containsKey(apiTestSuiteName)) {
//			extentAPI.removeTest(extentMapAPI.get(apiTestSuiteName));
//			apiTestSuiteName = "Rerun - " + apiTestSuiteName;
//		}
		testSuiteAPI = extentAPI.createTest(apiTestSuiteName);
		testSuiteAPI.assignCategory(categories);
		testMapAPI.put(Thread.currentThread().getId(), testSuiteAPI);

	}

	public static void createAPI_Node(String api_scenarioName) {

		testSuiteAPI.createNode(api_scenarioName);

	}

	public static void logPassAPI(String message) {
		getCurrentAPITest().log(Status.PASS, message);

	}

	public static void logFailAPI(String message) {
		getCurrentAPITest().log(Status.FAIL, message);

	}

	public static void logInfoAPI(String message) {
		getCurrentAPITest().log(Status.INFO, message);

	}

	public static void endCurrentAPITest() {
		getCurrentAPITest().getExtent().flush();

		testMapAPI.remove(Thread.currentThread().getId());
	}

	public static ExtentTest getCurrentAPITest() {
		return testMapAPI.get(Thread.currentThread().getId());

	}

	public static void endAPIReport() {

		extentAPI.flush();
	}

}
