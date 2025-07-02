package com.forum.bdd.common.hooks;

import java.nio.file.Paths;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.forum.bdd.integrations.common_utils.BrowserFactory;
import com.forum.bdd.integrations.common_utils.DriverFactory;
import com.forum.bdd.integrations.report_utils.ReportManager;
import com.forum.bdd.integrations.report_utils.ScreenshotUtil;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.Tracing;

import io.cucumber.java.After;
import io.cucumber.java.Before;
//import static org.junit.Assume.*;
import io.cucumber.java.Scenario;

public class CucumberHooks {

	public static ArrayList<String> passedTests = new ArrayList<String>();
	public static ArrayList<String> failedTests = new ArrayList<String>();
	public static ArrayList<String> totalTestCases = new ArrayList<String>();

	private static String str_Execution_TYPE = System.getProperty("execType");
	DriverFactory driverFactory = DriverFactory.getInstance();
	public String str_BrowserType = System.getProperty("Browser");
	BrowserFactory browserfactory = BrowserFactory.getInstance();
	public static String featureFileName;

	@Before(value = "@Noaudio", order = 0)
	public void skipscenario(Scenario scenario) {
		System.out.println("Skipped scenario is:" + scenario.getName());
		// Assume.assumetrue(false);
	}

	@Before
	public void before(Scenario scenario) throws Exception {
		System.out.println("+++++++++++++++++++before hooks++++++++++++++++++");

		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			ReportManager.startTest(scenario.getName(), "SMOKE");
			System.out.println("Execution started @ " + str_BrowserType + " browser & for type : Web UI");
			try {

				browserfactory.setBrowser(str_BrowserType);
				browserfactory.getBrowserContext().tracing()
						.start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (str_Execution_TYPE.equalsIgnoreCase("Mobile_UI")) {
			ReportManager.startTest(scenario.getName(), "SMOKE");
//		     System.out.println("---Open App---");

		}

		else if (str_Execution_TYPE.equalsIgnoreCase("Desktop_UI")) {
			ReportManager.startTest(scenario.getName(), "SMOKE");
//			DriverFactory.getInstance().getMobileDriver().activateApp("com.curebay.medicare");
//		     System.out.println("---Open App---");

		} else if (str_Execution_TYPE.equalsIgnoreCase("API")) {
			ReportManager.startTestAPI(scenario.getName(), "SMOKE");
		} else {
			throw new Exception(
					"[-] Please set exection type[Web_UI or Mobile_UI or API] value in cucumberhooks class line number 21");
		}

		/**
		 * ReportManager.createAPI_Node(scenario.getName()); String str_Features =
		 * scenario.getId().split(";")[0]; String[] str_arryFeature =
		 * str_Features.split("features/"); System.out.println(str_arryFeature);
		 * String[] str_Feature = str_arryFeature[1].split(".feature"); featureFileName
		 * = str_Feature[0]; System.out.println(str_Feature[0]);
		 **/
	}

	@After
	public void after(Scenario scenario) throws Exception {
		System.out.println("+++++++++++++++++++after hooks+++++++++++++++++++");
		String dateStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			browserfactory.getBrowserContext().tracing()
					.stop(new Tracing.StopOptions().setPath(Paths.get(System.getProperty("user.dir")
							+ "/TracingReports/" + dateStamp + "/" + scenario.getName() + ".zip")));
			totalTestCases.add(scenario.getName());
			if (totalTestCases.contains(scenario.getName())) {
				System.out.println("Removing TC from totalTestCases: " + scenario.getName());
				totalTestCases.remove(scenario.getName());
			}
			if (failedTests.contains(scenario.getName())) {
				System.out.println("Removing TC from failedTests: " + scenario.getName());
				failedTests.remove(scenario.getName());
			}
			if (passedTests.contains(scenario.getName())) {
				System.out.println("Removing TC from passedTests: " + scenario.getName());
				passedTests.remove(scenario.getName());
			}
			if (scenario.isFailed()) {
				failedTests.add(scenario.getName());
				try {
					final byte[] screenshot = (ScreenshotUtil.takeScreenshot().getBytes());
					scenario.attach(screenshot, "image/png", featureFileName);
					scenario.log("ScreenShot Attached");
				} catch (TimeoutError e) {
					// System.out.println("Screenshots not captured");
				}
			} else {
				passedTests.add(scenario.getName());
			}

		} else if (str_Execution_TYPE.equalsIgnoreCase("Mobile_UI")) {
			totalTestCases.add(scenario.getName());
			if (totalTestCases.contains(scenario.getName())) {
				System.out.println("Removing TC from totalTestCases: " + scenario.getName());
				totalTestCases.remove(scenario.getName());
			}
			if (failedTests.contains(scenario.getName())) {
				System.out.println("Removing TC from failedTests: " + scenario.getName());
				failedTests.remove(scenario.getName());
			}
			if (passedTests.contains(scenario.getName())) {
				System.out.println("Removing TC from passedTests: " + scenario.getName());
				passedTests.remove(scenario.getName());
			}
			if (scenario.isFailed()) {

				failedTests.add(scenario.getName());
				final byte[] screenshot = ((TakesScreenshot) DriverFactory.getInstance().getMobileDriver())
						.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", featureFileName);
				scenario.log("ScreenShot Attached");

			} else {
				passedTests.add(scenario.getName());

			}

		} else if (str_Execution_TYPE.equalsIgnoreCase("Desktop_UI")) {
			totalTestCases.add(scenario.getName());
			if (totalTestCases.contains(scenario.getName())) {
				System.out.println("Removing TC from totalTestCases: " + scenario.getName());
				totalTestCases.remove(scenario.getName());
			}
			if (failedTests.contains(scenario.getName())) {
				System.out.println("Removing TC from failedTests: " + scenario.getName());
				failedTests.remove(scenario.getName());
			}
			if (passedTests.contains(scenario.getName())) {
				System.out.println("Removing TC from passedTests: " + scenario.getName());
				passedTests.remove(scenario.getName());
			}
			if (scenario.isFailed()) {
				failedTests.add(scenario.getName());
				final byte[] screenshot = ((TakesScreenshot) DriverFactory.getInstance().getMobileDriver())
						.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", featureFileName);
				scenario.log("ScreenShot Attached");

			} else {
				passedTests.add(scenario.getName());

			}

		} else if (str_Execution_TYPE.equalsIgnoreCase("API")) {
			System.out.println("+++++++++++++++++++API+++++++++++++++++++");
			totalTestCases.add(scenario.getName());
			if (scenario.isFailed()) {
				failedTests.add(scenario.getName());
			} else {
				passedTests.add(scenario.getName());
			}

		} else {
			throw new Exception(
					"[-] Please set exection type[Web_UI OR Mobile_UI OR API] value in cucumberhooks class line number 21");
		}

	}

}
