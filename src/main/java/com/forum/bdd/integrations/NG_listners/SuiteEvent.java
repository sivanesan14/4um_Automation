package com.forum.bdd.integrations.NG_listners;

import java.io.FileWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IExecutionListener;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlSuite;

import com.forum.bdd.integrations.common_utils.BrowserFactory;
import com.forum.bdd.integrations.common_utils.DriverFactory;
import com.forum.bdd.integrations.report_utils.ReportManager;
import com.opencsv.CSVWriter;

public class SuiteEvent extends TestListenerAdapter implements ISuiteListener, IExecutionListener, IReporter {
	CSVWriter writer;
	public List<String[]> data = new ArrayList<String[]>();

	public static String str_Execution_TYPE = System.getProperty("execType");
	public static String str_browser; // for the report generation
	public static String str_platform;// for the report generation

	@Override
	public void onFinish(ISuite arg0) {

	}

	@Override
	public void onStart(ISuite arg0) {

	}

	@Override
	public void onExecutionStart() {

		System.out.println("==========In on execution start==============");

		System.out.println("Execution type is: " + String.valueOf(str_Execution_TYPE));
		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			ReportManager.startReport();
		} else if (str_Execution_TYPE.equalsIgnoreCase("Mobile_UI")) {
			ReportManager.startReport();
		} else if (str_Execution_TYPE.equalsIgnoreCase("API")) {
			System.out.println("In api execution start");
			ReportManager.startApiReport();
		}

		else if (str_Execution_TYPE.equalsIgnoreCase("Desktop_UI")) {
			System.out.println("============desktopstarreport");

			ReportManager.startReport();
		} else {
			System.out.println("[-] Please set exection type [Mobile_UI or API] value in cucumberhooks");
		}
	}

	@Override
	public void onExecutionFinish() {

		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			str_browser = System.getProperty("Browser");
			str_platform = System.getProperty("os.name", "os.version");
			ReportManager.extent.setSystemInfo("Browser",
					str_browser + "  v" + BrowserFactory.getInstance().getBrowser().version());
			ReportManager.extent.setSystemInfo("Platform", str_platform);
			ReportManager.endReport();
		} else if (str_Execution_TYPE.equalsIgnoreCase("Mobile_UI")) {
			System.out.println("============MobileEndReport");
			ReportManager.endReport();
		}

		else if (str_Execution_TYPE.equalsIgnoreCase("Desktop_UI")) {

			System.out.println("============desktopendreport");
			ReportManager.endReport();
		} else if (str_Execution_TYPE.equalsIgnoreCase("API")) {
			ReportManager.endAPIReport();
		} else {
			System.out.println("[-] Please set exection type[Mobile_UI or API] value in cucumberhooks");
		}
	}

	@Override
	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String outputDirectory) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String csv = "./test.csv";

		try {
			writer = new CSVWriter(new FileWriter(csv));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (ISuite iSuite : arg1) {
			Map<String, ISuiteResult> results = iSuite.getResults();
			Set<String> keys = results.keySet();
			for (String key : keys) {
				ITestContext context = results.get(key).getTestContext();
				IResultMap resultMap = context.getFailedTests();

				// -------------------------FAILED TEST CASE-----------------------------
				Collection<ITestNGMethod> failedMethods = resultMap.getAllMethods();
				for (ITestNGMethod iTestNGMethod : failedMethods) {
					Date DateTime = new Date(iTestNGMethod.getDate());
					String dateDate = dateFormat.format(DateTime);
					String[] array_DataTime = dateDate.split(" ");
					data.add(new String[] { array_DataTime[0], array_DataTime[1], iTestNGMethod.getMethodName(),
							iTestNGMethod.getDescription(), "FAIL" });
				}

				System.out.println("===================FAILED======================");
				System.out.println("Failed test case count:" + resultMap.size());
				// -------------------------PASSED TEST CASE-----------------------------

				IResultMap resultMapPass = context.getPassedTests();
				Collection<ITestNGMethod> passMethods = resultMapPass.getAllMethods();
				for (ITestNGMethod iTestNGMethod : passMethods) {
					Date DateTime = new Date(iTestNGMethod.getDate());
					String dateDate = dateFormat.format(DateTime);
					String[] array_DataTime = dateDate.split(" ");
					data.add(new String[] { array_DataTime[0], array_DataTime[1], iTestNGMethod.getMethodName(),
							iTestNGMethod.getDescription(), "PASS" });

				}
				System.out.println("===================PASSED======================");
				System.out.println("Passed test case count:" + resultMapPass.size());

				// -------------------------Skipped TEST CASE-----------------------------
				IResultMap resultMapSkip = context.getSkippedTests();
				Collection<ITestNGMethod> skipMethods = resultMapPass.getAllMethods();
				for (ITestNGMethod iTestNGMethod : skipMethods) {
					Date DateTime = new Date(iTestNGMethod.getDate());
					String dateDate = dateFormat.format(DateTime);
					String[] array_DataTime = dateDate.split(" ");
					data.add(new String[] { array_DataTime[0], array_DataTime[1], iTestNGMethod.getMethodName(),
							iTestNGMethod.getDescription(), "SKIP" });
				}
				System.out.println("===================SKIPPED======================");
				System.out.println("Skipped test case count:" + resultMapSkip.size());
			}

		}

		String[] header = { "Date", "Time", "Test Case ID", "Description", "Result" };
		writer.writeNext(header);
		writer.writeAll(data);
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getBrowser() {
		String browserName = null;

		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			Capabilities cap = ((RemoteWebDriver) DriverFactory.getInstance().getWebDriver()).getCapabilities();
			browserName = cap.getBrowserName().toLowerCase();

		}
		return browserName;

	}

	public static String getVersion() {
		String v = null;
		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			Capabilities cap = ((RemoteWebDriver) DriverFactory.getInstance().getWebDriver()).getCapabilities();
			v = cap.getBrowserName().toString();

		}
		return v;

	}
}
