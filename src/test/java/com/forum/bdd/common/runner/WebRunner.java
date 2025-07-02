package com.forum.bdd.common.runner;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.forum.bdd.integrations.NG_listners.PageEvent;
import com.forum.bdd.integrations.NG_listners.SuiteEvent;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@Listeners({ SuiteEvent.class, PageEvent.class })

@CucumberOptions(features = { "src/test/java/com/forum/bdd/web/features" }, glue = { "com/forum/bdd/common/hooks",
		"com/forum/bdd/web/step_definitions" }, tags = "@Verify_CreateGroup_Page", plugin = { "pretty",
				"html:target/site/cucumber-pretty/cucumberPlay.html", "json:target/cucumber/Webcucumber.json",
				"rerun:target/web_rerun.txt",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" }, monochrome = true, publish = true, dryRun = false)

public class WebRunner {

	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		System.out.println("@Before class");
	}

	@Test(groups = "cucumber", description = "Run Cucumber Scenario", dataProvider = "scenarios")
	public void scenario(PickleWrapper pickleEventWrapper, FeatureWrapper cucumberFeatureWrapper) throws Throwable {
		testNGCucumberRunner.runScenario(pickleEventWrapper.getPickle());
	}

	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		System.out.println("@Data provider");
		if (testNGCucumberRunner == null) {
			testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		}

		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		System.out.println("@Afterclass");
		testNGCucumberRunner.finish();
	}
}
