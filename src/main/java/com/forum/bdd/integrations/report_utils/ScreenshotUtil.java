package com.forum.bdd.integrations.report_utils;

import java.io.IOException;

import java.nio.file.Paths;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.forum.bdd.integrations.common_utils.BrowserFactory;
import com.microsoft.playwright.Page;

public class ScreenshotUtil {

	public static String takeScreenshot(WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String source = ts.getScreenshotAs(OutputType.BASE64);
		return source;
	}

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		// getPage().screenshot(new
		// Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

		byte[] buffer = BrowserFactory.getInstance().getPage()
				.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);

		return base64Path;
	}
}
