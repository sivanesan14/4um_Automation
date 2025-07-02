package com.forum.bdd.ccl;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.forum.bdd.integrations.common_utils.DriverFactory;
import com.forum.bdd.integrations.report_utils.ReportManager;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class MobileActions {

	Actions action = new Actions(DriverFactory.getInstance().getMobileDriver());
	String lastWord;

	@SuppressWarnings("deprecation")
	public WebElement waitForVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getMobileDriver(), 30);
		wait.pollingEvery(1, TimeUnit.SECONDS);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElement(By locator, long time) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getMobileDriver(), time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void Click(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		elm.click();
		ReportManager.logInfo("Successfully clicked on - " + " <b style=\"color:green;\"> : " + info + "</b>");
		System.out.println("Successfully clicked on - " + info);
		// LogClass.loginfo("Successfully clicked on -"+ info);
	}

	public void Clickoperation(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		if (elm != null) {
			elm.click();
			ReportManager.logInfo("Successfully clicked on - " + info);
			System.out.println("Successfully clicked on - " + info);
		} else
			ReportManager.logInfo("Not Successfully clicked on - " + info);
		System.out.println("Not successfuly clicked on- " + info);

		// LogClass.loginfo("Successfully clicked on -"+ info);
	}

	public boolean isDisplayed(By locator) throws InterruptedException {
		boolean eleSelected = DriverFactory.getInstance().getMobileDriver().findElement(locator).isDisplayed();
		return eleSelected;
	}

	public void verifyText(String actualText, String expectedText) {
		ReportManager.logInfo("Actual Text - " + " <b style=\"color:green;\"> : " + actualText + "</b>");
		ReportManager.logInfo("Expected Text - " + " <b style=\"color:green;\"> : " + expectedText + "</b>");
		System.out.println("Actual Text - " + actualText);
		System.out.println("Expected Text - " + expectedText);
		assertEquals(actualText, expectedText);
	}

	public static void VerifyText(String actualText, String expectedText) {
		if (actualText.contains(expectedText)) {
			ReportManager.logPass("Actual Text - " + "<b style=\"color:green;\">" + actualText + "</b>"
					+ " matched with expected " + "<b style=\"color:green;\">" + expectedText + "</b>");
		} else {
			ReportManager.logFail("Actual Text - " + "<b style=\"color:green;\">" + actualText + "</b>"
					+ " not matched with expected " + "<b style=\"color:green;\">" + expectedText + "</b>");
			assertEquals(expectedText, actualText);
		}
	}

	public void Send(String value) {

		Actions action = new Actions(DriverFactory.getInstance().getMobileDriver());
		action.sendKeys(value).perform();
	}

	public void sendKeys(By locator, String text) {
		WebElement elm = waitForVisible(locator);
		elm.sendKeys(text);
		ReportManager.logInfo("Successfully Entered text - " + text + "</b>");
		System.out.println("Successfully Entered text - " + text);
	}

	public boolean isElementPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getMobileDriver(), 5);
		boolean isDone = DriverFactory.getInstance().getMobileDriver().findElements(locator).size() > 0;
		return isDone;

	}

	public void sendKeys(By locator, String text, String info) {
		WebElement elm = waitForVisible(locator);
		elm.click();
		elm.sendKeys(text);
		ReportManager.logInfo(info + " <b style=\"color:green;\"> : " + text + "</b>");
		ReportManager.logInfo("Successfully Entered text - " + "<b style=\"color:green;\">" + text + "</b>");
		System.out.println("Successfully Entered text - " + text);
		// LogClass.loginfo(info+" :"+text);
	}

	public void clearAndSendKeys(By locator, String text) {
		WebElement elm = waitForVisible(locator);
		elm.clear();
		elm.sendKeys(text);
		ReportManager.logInfo("Successfully Entered text -<b style=\"color:green;\"> " + text + "</b>");

		// LogClass.loginfo("Successfully Entered text - " + text);
	}

	public void clearAndSendKeys(By locator, String text, String info) {
		WebElement elm = waitForVisible(locator);
		elm.clear();
		elm.sendKeys(text);
		ReportManager.logInfo(info + "<b style=\"color:green;\"> :" + text + "</b>");

		// LogClass.loginfo(info+" : " + text);
	}

	public String getText(By locator) {
		WebElement elm = waitForVisible(locator);
		String elmText = elm.getText();
		ReportManager.logInfo("Successfully get text - <b style=\"color:green;\">" + elmText + "</b>");
		System.out.println("Successfully get text - " + elmText);
		// LogClass.loginfo("Successfully get text - " + elmText);
		return elmText;
	}

	public String getText(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		String elmText = elm.getText();
		ReportManager.logInfo("" + info + "<b style=\"color:green;\"> :" + elmText + "</b>");
		// LogClass.loginfo(""+info+" : " + elmText);
		return elmText;
	}

	public void swipeUp(int howManySwipes) {
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		// calculate coordinates for vertical swipe
		int startY = (int) (size.height * 0.70);
		int endY = (int) (size.height * 0.30);
		int startX = (size.width / 2);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release()
						.perform();
				System.out.println("swipeUp");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	public void swipeDown(int howManySwipes) {
		// calculate coordinates for vertical swipe
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		int startY = (int) (size.height * 0.70);
		int endY = (int) (size.height * 0.30);
		int startX = (size.width / 2);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, endY)).moveTo(PointOption.point(startX, startY)).release()
						.perform();
				System.out.println("swipeDown");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	public void swipeDowns(int howManySwipes) {
		// calculate coordinates for vertical swipe
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		int startY = (int) (size.height * 0.80);
		int endY = (int) (size.height * 0.40);
		int startX = (size.width / 2);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, endY)).moveTo(PointOption.point(startX, startY)).release()
						.perform();
				System.out.println("swipeDown");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	public void swipeRighttoLeft(int howManySwipes) {
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		// calculate coordinates for horizontal swipe
		int startY = (int) (size.height / (size.height / 5));
		int startX = (int) (size.width * 0.90);
		int endX = (int) (size.width * 0.10);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY)).release()
						.perform();
				System.out.println("swipeRighttoLeft");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	public void swipeLefttoRight(int howManySwipes) {
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		// calculate coordinates for horizontal swipe
		int startY = (int) (size.height / (size.height / 1.2));
		int startX = (int) (size.width * 0.05);
		int endX = (int) (size.width * 0.90);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY)).release()
						.perform();
				System.out.println("swipeLefttoRight");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	public void swipeUp_FindElementClick(int howManySwipes, By locator) throws InterruptedException {
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		// calculate coordinates for vertical swipe
		int startY = (int) (size.height * 0.70);
		int endY = (int) (size.height * 0.30);
		int startX = (size.width / 2);
		Thread.sleep(1000);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				boolean isElmPresent = DriverFactory.getInstance().getMobileDriver().findElements(locator).size() > 0;
				if (isElmPresent) {
					DriverFactory.getInstance().getMobileDriver().findElement(locator).click();
					break;
				}
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release()
						.perform();
				System.out.println("swipeUp");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	public void swipeUp_FindElementClick(int howManySwipes, By locator, String info) throws InterruptedException {
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		// calculate coordinates for vertical swipe
		int startY = (int) (size.height * 0.70);
		int endY = (int) (size.height * 0.30);
		int startX = (size.width / 2);
		Thread.sleep(1000);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				boolean isElmPresent = DriverFactory.getInstance().getMobileDriver().findElements(locator).size() > 0;
				if (isElmPresent) {
					DriverFactory.getInstance().getMobileDriver().findElement(locator).click();
					ReportManager
							.logInfo("Successfully clicked on - " + " <b style=\"color:green;\"> : " + info + "</b>");
					System.out.println("Successfully clicked on - " + info);
					break;
				}
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release()
						.perform();
				System.out.println("swipeUp");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	public void swipeUp_FindElement(int howManySwipes, By locator) throws InterruptedException {
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		// calculate coordinates for vertical swipe
		int startY = (int) (size.height * 0.70);
		int endY = (int) (size.height * 0.30);
		int startX = (size.width / 2);
		Thread.sleep(1000);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				boolean isElmPresent = DriverFactory.getInstance().getMobileDriver().findElements(locator).size() > 0;
				if (isElmPresent) {
					// DriverFactory.getInstance().getMobileDriver().findElement(locator).click();
					break;
				}
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release()
						.perform();
				System.out.println("swipeUp");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	public String swipeUp_FindElementGetText(int howManySwipes, By locator) throws InterruptedException {
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		// calculate coordinates for vertical swipe
		int startY = (int) (size.height * 0.70);
		int endY = (int) (size.height * 0.30);
		int startX = (size.width / 2);
		String text = null;
		Thread.sleep(1000);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				boolean isElmPresent = DriverFactory.getInstance().getMobileDriver().findElements(locator).size() > 0;
				System.out.println(isElmPresent);
				if (isElmPresent) {
					text = DriverFactory.getInstance().getMobileDriver().findElement(locator).getText();
					ReportManager.logInfo("Successfully get text - <b style=\"color:green;\">" + text + "</b>");
					System.out.println("Successfully get text - " + text);
					break;
				}
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release()
						.perform();
				System.out.println("swipeUp");
			}
		} catch (Exception e) {
			// print error or something
		}
		return text;
	}

	public void pressKeyboardValues(Keys value) throws InterruptedException {
		action.sendKeys(value).build().perform();
		ReportManager.logInfo("Successfully performed keyboard action - <b style=\"color:green;\">" + value + "</b>");
		// LogClass.loginfo("Successfully performed keyboard action - " + value);
	}

	public void rightToLeftSwipeUsingElement(By fromLocator, By toLocator) throws InterruptedException {
		List<WebElement> from_Location = DriverFactory.getInstance().getMobileDriver().findElements(fromLocator);
		List<WebElement> to_Location = DriverFactory.getInstance().getMobileDriver().findElements(toLocator);
		int startX = from_Location.get(0).getLocation().getX() + (from_Location.get(0).getSize().getWidth() / 2);
		int startY = from_Location.get(0).getLocation().getY() + (from_Location.get(0).getSize().getHeight() / 2);
		int endX = to_Location.get(0).getLocation().getX() + (to_Location.get(0).getSize().getWidth() / 2);
		int endY = to_Location.get(0).getLocation().getY() + (to_Location.get(0).getSize().getHeight() / 2);
		new TouchAction(DriverFactory.getInstance().getMobileDriver()).press(point(startX, startY))
				.waitAction(waitOptions(ofMillis(1000))).moveTo(point(endX, endY)).release().perform();

	}

	public void rightToLeftSwipeUsingWebElement(WebElement fromLocator, WebElement toLocator)
			throws InterruptedException {
		System.out.println(" swip element");
		System.out.println("fromLocator" + fromLocator);
		System.out.println("to locator" + toLocator);

		WebElement from_Location = fromLocator;
		WebElement to_Location = toLocator;
		int startX = from_Location.getLocation().getX() + (from_Location.getSize().getWidth() / 2);
		int startY = from_Location.getLocation().getY() + (from_Location.getSize().getHeight() / 2);
		int endX = to_Location.getLocation().getX() + (to_Location.getSize().getWidth() / 2);
		int endY = to_Location.getLocation().getY() + (to_Location.getSize().getHeight() / 2);
		new TouchAction(DriverFactory.getInstance().getMobileDriver()).press(point(startX, startY))
				.waitAction(waitOptions(ofMillis(1000))).moveTo(point(endX, endY)).release().perform();

	}

	public int getTextByInt(By locator) {
		WebElement elm = waitForVisible(locator);
		String elmText = elm.getText();
		int elmIntTxt = Integer.parseInt(elmText);
		ReportManager.logInfo("Successfully get Integer text - <b style=\"color:green;\">" + elmIntTxt + "</b>");
		System.out.println("Successfully get Integer text- " + elmIntTxt);
		return elmIntTxt;
	}

	public boolean isDisplyed(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		boolean isDisplyed = elm.isDisplayed();

		if (isDisplyed) {
			ReportManager.logInfo("Sucessfully element is displayed - " + info);
			System.out.println("Sucessfully element is displayed - " + info);
		} else {
			ReportManager.logFail("Element not displayed - " + info);
			System.out.println("Element not displayed - " + info);
		}
		return isDisplyed;
	}

	public void clickUsingCoordinates(int xcordinate, int ycordinate) throws InterruptedException {
		new TouchAction(DriverFactory.getInstance().getMobileDriver()).tap(PointOption.point(xcordinate, ycordinate))
				.release().perform();
	}

	public List<WebElement> getElementSizeUsingFindElements(By locator) throws InterruptedException {
		List<WebElement> lst_Elm = DriverFactory.getInstance().getMobileDriver().findElements(locator);
		ReportManager.logInfo("Successfully captured elemnt size is - " + lst_Elm.size());
		for (int iterator = 0; iterator < lst_Elm.size(); iterator++) {
			System.out.println("List of elements: " + lst_Elm.get(iterator).getText());
		}
		return lst_Elm;
	}

	public void longpressactions(By locator) throws Exception {
//		new TouchAction(DriverFactory.getInstance().getMobileDriver()).longPress(new LongPressOptions()
//		.withElement(ElementOption.element(mWebElement)).withDuration(Duration.ofMillis(100000))).release()
//		.perform();

//		WebElement mWebElement = DriverFactory.getInstance().getMobileDriver().findElement(locator);
//		new TouchAction(LocalDriverManager.getAppiumDriver()).longPress(new PointOption().point(x,y)).waitAction(WaitOptions.waitoptions(Duration.ofSeconds(3))).release().perform();
//		TimeUnit.SECONDS.sleep(1);

		WebElement mWebElement = DriverFactory.getInstance().getMobileDriver().findElement(locator);

		new TouchAction(DriverFactory.getInstance().getMobileDriver())
				.longPress(new ElementOption().withElement(mWebElement))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000))).release().perform();
		Thread.sleep(3000);
//		new TouchAction(DriverFactory.getInstance().getMobileDriver()).longPress(new ElementOption().withElement(mWebElement))
//		.release().perform();
//		Thread.sleep(3000);

	}

	public void Touch(By locator, String info) {

		Actions action = new Actions(DriverFactory.getInstance().getMobileDriver());
		action.clickAndHold().release().perform();
	}

	public boolean isEnabled(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		boolean isEnable = elm.isEnabled();
		if (isEnable) {
			ReportManager.logInfo("Element Enabled: " + "<b style=\"color:green;\">" + info + "</b>");
			System.out.println("Element Enabled: " + info);
		} else {
			ReportManager.logInfo("Element Disabled: " + "<b style=\"color:green;\">" + info + "</b>");
			System.out.println("Element Disabled: " + info);
		}
		return isEnable;
	}

	public static void doubleClick(By locator, String text) {

		Actions actions = new Actions(DriverFactory.getInstance().getMobileDriver());
		WebElement elementLocator = DriverFactory.getInstance().getMobileDriver().findElement(locator);
		actions.doubleClick(elementLocator).perform();

	}

	public void clear(By locator, String text) {
		WebElement elm = waitForVisible(locator);
		ReportManager.logInfo("Successfully Cleared text -<b style=\"color:green;\"> " + text + "</b>");
		elm.clear();
	}

	public String getElementTextUsingFindElements(int iteratorStarted, int iteratorEnded, By locator)
			throws InterruptedException {
		String lst_ElmText = "";
		List<WebElement> lst_Elm = DriverFactory.getInstance().getMobileDriver().findElements(locator);
//		ReportManager.logInfoMobile("Successfully captured elemnt size is - " + lst_Elm.size());
		for (int iterator = iteratorStarted; iterator < lst_Elm.size() - iteratorEnded; iterator++) {
			String lst_ElmgetText = lst_Elm.get(iterator).getText();
//			lst_ElmText = lst_ElmText+"\n"+lst_ElmgetText;

			System.out.println("Successfully get text - " + lst_Elm.get(iterator).getText());
			ReportManager.logInfo("Successfully get text -<b style=\"color:green;\"> " + lst_ElmgetText + "</b>");
		}

		return lst_ElmText;
	}

	public void getToastMessage() {
		try {
//			mobileActions.waitForVisible(By.xpath("//android.widget.Toast"));
			String toast = DriverFactory.getInstance().getMobileDriver().findElement(By.xpath("//android.widget.Toast"))
					.getText();
			System.out.println("The Toast Message is : " + toast);
			ReportManager.logInfo("Successfully get Toast Message text -<b style=\"color:green;\"> " + toast + "</b>");
		} catch (Exception e) {
			System.out.println();
		}

	}

	public void SwipeByCoordinates(int startX, int startY, int endX, int endY) throws InterruptedException {
		new TouchAction(DriverFactory.getInstance().getMobileDriver()).press(point(startX, startY))
				.waitAction(waitOptions(ofMillis(1000))).moveTo(point(endX, endY)).release().perform();
	}

	public void navigatesbackUsingFindElement(By locator) throws InterruptedException {
		Thread.sleep(1000);
		try {
			boolean isElmPresent = DriverFactory.getInstance().getMobileDriver().findElements(locator).size() > 0;
			if (isElmPresent) {
				DriverFactory.getInstance().getMobileDriver().navigate().back();
			}

		} catch (Exception e) {
			// print error or something
		}
	}

	public void CloseAPP(String terminateApp) {
		DriverFactory.getInstance().getMobileDriver().terminateApp(terminateApp);
//		 System.out.println("---Close App---");
	}

	public void OpenAPP(String activateApp) {
		DriverFactory.getInstance().getMobileDriver().activateApp(activateApp);
//	     System.out.println("---Open App---");
	}

	public void swipeDown_FindElement(int howManySwipes, By locator) throws InterruptedException {
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		// calculate coordinates for vertical swipe
		int startY = (int) (size.height * 0.70);
		int endY = (int) (size.height * 0.30);
		int startX = (size.width / 2);
		Thread.sleep(1000);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				boolean isElmPresent = DriverFactory.getInstance().getMobileDriver().findElements(locator).size() > 0;
				if (isElmPresent) {
					// DriverFactory.getInstance().getMobileDriver().findElement(locator).click();
					break;
				}
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, endY)).moveTo(PointOption.point(startX, startY)).release()
						.perform();
				System.out.println("swipedown");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	public void clicksAgainManyClicks(int howMany, By locatorNextScreen, By locatorPresentScreen)
			throws InterruptedException {
		Thread.sleep(1000);
		try {
			for (int i = 1; i <= howMany; i++) {
				Thread.sleep(1000);
				boolean isElmPresent = DriverFactory.getInstance().getMobileDriver().findElements(locatorNextScreen)
						.size() > 0;
				if (isElmPresent) {

					break;
				} else {
					DriverFactory.getInstance().getMobileDriver().findElement(locatorPresentScreen).click();
				}

			}
		} catch (Exception e) {
		}
	}

	public void swtichToFrame(By locator) {
//		DriverFactory.getInstance().getMobileDriver().switchTo().frame(this.waitForVisible(locator));
		DriverFactory.getInstance().getMobileDriver().switchTo().frame(0);
		System.out.println("I am in the Frame loop");
	}

//	public void frameScrollToElement(String selector, String locator, String info) {
//		page.frameLocator(selector).locator(locator).scrollIntoViewIfNeeded();
//		ReportManager.logInfo("Successfully scrolled to - " + info);
//		System.out.println("Successfully scrolled to - " + info);
//	
	public void getLastWordOnMobile(By locator, String Info) {
		WebElement sentence = waitForVisible(locator);
		String strSentence_Value = sentence.getText().trim();
		System.out.println(strSentence_Value);

		int lastSpaceIndex = strSentence_Value.lastIndexOf(' ');
		lastWord = (lastSpaceIndex == -1) ? strSentence_Value : strSentence_Value.substring(lastSpaceIndex + 1);
		lastWord = lastWord.replace("*", "");
		lastWord = lastWord.replace("?", "");
		System.out.println("Last word of the question is : " + lastWord);

	}

	public void typeLastwordOnMobile(By locator, String info) {
		WebElement Lastword = waitForVisible(locator);
		action.sendKeys(Lastword, lastWord).perform();
		System.out.println("Successfully entered value -  " + " in " + info + " box");
		ReportManager.logInfo("Successfully entered value -  " + " in " + info + " box");
	}

	public void action_clickOnPosition(int pointA_X, int pointA_Y) {
		PointerInput finger = new PointerInput(org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger");
		org.openqa.selenium.interactions.Sequence clickPosition = new org.openqa.selenium.interactions.Sequence(finger,
				1);
		clickPosition.addAction(finger.createPointerMove(Duration.ZERO, Origin.viewport(), pointA_X, pointA_Y))
				.addAction(finger.createPointerDown(MouseButton.LEFT.asArg()))
				.addAction(finger.createPointerUp(MouseButton.LEFT.asArg()));
		DriverFactory.getInstance().getMobileDriver().perform(Arrays.asList(clickPosition));
	}

//	public void testApplicationsManagement() throws InterruptedException {
//		String appId = DriverFactory.getInstance().getMobileDriver().getCurrentPackage();
//		assertThat(DriverFactory.getInstance().getMobileDriver().queryAppState(appId), equalTo(ApplicationState.RUNNING_IN_FOREGROUND));
//		Thread.sleep(500);
//		DriverFactory.getInstance().getMobileDriver().runAppInBackground(Duration.ofSeconds(-1));
//		assertThat(DriverFactory.getInstance().getMobileDriver().queryAppState(appId), lessThan(ApplicationState.RUNNING_IN_FOREGROUND));
//		Thread.sleep(500);
//		DriverFactory.getInstance().getMobileDriver().activateApp(appId);
//		assertThat(DriverFactory.getInstance().getMobileDriver().queryAppState(appId), equalTo(ApplicationState.RUNNING_IN_FOREGROUND));
//	}
}
