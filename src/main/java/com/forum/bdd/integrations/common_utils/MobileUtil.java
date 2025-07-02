package com.forum.bdd.integrations.common_utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MobileUtil {

	public static By returnBy(String locatorType, String locatorValue) {
		By by = null;
		switch (locatorType.toUpperCase()) {
		case "XPATH":
			by = By.xpath(locatorValue);
			break;
		case "NAME":
			by = By.name(locatorValue);
			break;
		case "ID":
			by = By.id(locatorValue);
			break;
		case "CSS":
			by = By.cssSelector(locatorValue);
			break;
		}
		return by;
	}

	public static By returnByBasedOnPageNameAndObjectName(String page, String object) {

		String locatorType = JsonUtils.getLocatorType(page, object);
		String locatorValue = JsonUtils.getLocatorValue(page, object);
		return MobileUtil.returnBy(locatorType, locatorValue);
	}

	@SuppressWarnings("static-access")
	public static By returnByBasedOnPageNameAndObjectName(String page, String object, String... args) {
		String locatorType = JsonUtils.getLocatorType(page, object);
		String locatorValue = JsonUtils.getLocatorValue(page, object);
		String modifiedLocatorValue = "";
		if (args.length <= 1) {
			modifiedLocatorValue = locatorValue.replaceAll("%s", args[0]);
		} else {
			modifiedLocatorValue = String.format(locatorValue, args);
		}
		// Log.info("Locator value -------" + modifiedLocatorValue);
		return MobileUtil.returnBy(locatorType, modifiedLocatorValue);

	}

	public static String getLastWindowHandle(WebDriver webDriver) {
		String handle = null;
		for (String handleIterator : webDriver.getWindowHandles()) {
			handle = handleIterator;
		}
		return handle;
	}

}
