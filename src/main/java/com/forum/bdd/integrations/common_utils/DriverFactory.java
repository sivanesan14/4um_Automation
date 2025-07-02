package com.forum.bdd.integrations.common_utils;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.windows.WindowsDriver;

public class DriverFactory {
	private static DriverFactory instance = null;
	WebDriver driver;
	ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<AppiumDriver>();
//	String strMobileExecutiontype = "android";
	String strMobileExecutiontype = System.getProperty("mobileExecutionType");
	String strApplicationName = System.getProperty("applicationName");
	ThreadLocal<WindowsDriver<WebElement>> windowDriver = new ThreadLocal<WindowsDriver<WebElement>>();

	public static DriverFactory getInstance() {
		if (instance == null) {
			instance = new DriverFactory();
		}
		return instance;
	}

//mobile
	public final void setMobileDriver(String platform, String udid, String systemPort, String deviceName)
			throws Exception {

		if (strMobileExecutiontype.contains("ios")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
			// capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
			// deviceVersion);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			capabilities.setCapability(MobileCapabilityType.UDID, udid);
			capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
			capabilities.setCapability("Connect Hardware Keyboard", true);
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			capabilities.setCapability(MobileCapabilityType.SUPPORTS_LOCATION_CONTEXT, true);
			capabilities.setCapability("app", System.getProperty("user.dir") + ConfigReader.getValue("iOSAppLocation"));
			URL url = new URL("http://0.0.0.0:4723/wd/hub");
			appiumDriver.set(new AppiumDriver(url, capabilities));
			// AppiumDriver<WebElement> driver = new AppiumDriver(url,capabilities);
			getMobileDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		} else if (strMobileExecutiontype.contains("android")) {

			String[] platformInfo = platform.split(" ");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiAutomator2");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformInfo[0]);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformInfo[1]);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			capabilities.setCapability(MobileCapabilityType.UDID, udid);
			capabilities.setCapability("app",
					System.getProperty("user.dir") + ConfigReader.getValue("Android_AppLocation"));
			capabilities.setCapability("appPackage", "com.curebay.medicare");
			capabilities.setCapability("appActivity", "com.curebay.medicare.MainActivity");
			capabilities.setCapability("resetKeyboard", true);
			capabilities.setCapability("unicodeKeyboard", true);
			capabilities.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
			capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
			appiumDriver.set(
					new AppiumDriver<WebElement>(new URL("http://0.0.0.0:" + systemPort + "/wd/hub"), capabilities));

			getMobileDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		} else if (strMobileExecutiontype.equalsIgnoreCase("pCloudy_android")) {
			String[] platformInfo = platform.split(" ");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("pCloudy_Username", "indira.alampally@temp.incred.com");
			capabilities.setCapability("pCloudy_ApiKey", "dmz7qv6jccy592mgz526x6qw");
			capabilities.setCapability("pCloudy_ApplicationName", "OMNI_phase2.apk");
			capabilities.setCapability("appPackage", "com.curebay.medicare");
			capabilities.setCapability("appActivity", "com.curebay.medicare.MainActivity");
			capabilities.setCapability("pCloudy_DurationInMinutes", 10);
			capabilities.setCapability("pCloudy_DeviceManafacturer", platformInfo[0]);
			capabilities.setCapability("pCloudy_DeviceVersion", platformInfo[1]);
			capabilities.setCapability("pCloudy_DeviceFullName", "GOOGLE_PixelXL_Android_10.0.0_53dda");
			capabilities.setCapability("automationName", "uiautomator2");
			capabilities.setCapability("newCommandTimeout", 600);
			capabilities.setCapability("launchTimeout", 90000);

			capabilities.setCapability("noSign", true);
			appiumDriver.set(new AppiumDriver<WebElement>(new URL("https://device.pcloudy.com/appiumcloud/wd/hub"),
					capabilities));
//		} else if (strMobileExecutiontype.equalsIgnoreCase("browserstack")) {
//			DesiredCapabilities capabilities = new DesiredCapabilities();
//			HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
//			bstackOptions.put("userName", "YOUR_USERNAME");
//			bstackOptions.put("accessKey", "YOUR_ACCESS_KEY");
//			capabilities.setCapability("platformName", "android");
//			capabilities.setCapability("appium:platformVersion", "12.0");
//			capabilities.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
//			capabilities.setCapability("bstack:options", bstackOptions);
//
		}
	}

	public final void setDesktopDriver() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", ConfigReader.getValue("ExePath"));
		windowDriver.set(new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723/"), capabilities));
		getWindowDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(10000);
	}

	public WindowsDriver<WebElement> getWindowDriver() {
		return windowDriver.get();
	}

	public WebDriver getWebDriver() {
		return webDriver.get();
	}

	public AppiumDriver getMobileDriver() {
		return appiumDriver.get();
	}
}
