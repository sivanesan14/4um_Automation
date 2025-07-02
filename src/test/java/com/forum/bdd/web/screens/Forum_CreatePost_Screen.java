package com.forum.bdd.web.screens;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.forum.bdd.ccl.WebActions;
import com.forum.bdd.integrations.common_utils.RandomGenerator;
import com.forum.bdd.integrations.report_utils.ReportManager;
import com.microsoft.playwright.TimeoutError;

public class Forum_CreatePost_Screen {
	String CreatePostOpt = "//*[contains(text(),'Post')]";
	String Titletxt = "(//*[@id='title-input'])[1]";
	String Desctxt = "//*[@class='mentionWrapper__input']";
	String title_CreatePost = "//p[text()='Create Post']";
	String UploadfileOpt = "//*[@id='fileUploadButton']";
	String CreatePostBtn = "//*[@id='button-contain']";
	String GroupNametxt = "(//*[@id='title-input'])[2]";
	String VerifyHomePage = "//*[@title='Home']";
	String NoDesErrorMess = "//*[contains(text(),'Please fill this field')]";
//	String MoreFileErrorMess = "//*[contains(text(),'You have exceeded the maximum number of files allowed. Please select up to 12 files.')]";
	String MoreFileErrorMess = "//*[contains(text(),'The Upload Media cannot exceed 12 files.')]";
	String MoreDesErrorMess = "//*[@id='createPost']//div[4]";
//	String HamburgerMenu = "(//*[@class='MuiCardHeader-root css-1tkqnl1']//div//span//p[@title='Rahul KL']/../../../../div//button[@type='button'])[2]";
	String DynamicHamburgerMenu = "(//*[starts-with(@class,'MuiCardHeader-root')]//div//span//p[@title='%s']/../../../../div//button[@type='button'])[1]";

	String DeleteOption = "//*[@title='Delete']";
//	String DeletePopup = "//*[@title='Are you sure you want to delete the post?']";
	String DeletePopup = "//h2/p[@id='typographyWith-i18n']";
	String ConfirmDeleBtn = "//*[@type='button'][contains(text(),'Delete')]";
//	String ConfirmPostdeletePopup = "//*[@title='The post has been successfully deleted.']";
	String ConfirmPostdeletePopup = "//*[@class='MuiDialogContent-root css-1xfpytt']//div//p";
	String success_Message = "//p[contains(@title,'Successfully created post')]";
	String GroupNameSelect = "//*[@role='menuitem']";
	String TitleErrorMess = "//*[contains(text(),'The Title cannot exceed 200 characters.')]";
	String NavigateProfilePage = "(//*[@id='userDetails']//div)[1]";

	WebActions play = new WebActions();
	LocalDateTime currentDateTime = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	String formattedDateTime = currentDateTime.format(formatter);

	public void clickCreatePostOptions() {
		play.click(CreatePostOpt, "Create Post Options");
	}

	public void verifyTitleInPostPage() {
		play.isVisible(title_CreatePost, "Title : Create Post");
	}

	public boolean verifTheCurrentUrl(String path) {
		String currentUrl = play.getCurrentUrl();
		boolean contains = currentUrl.contains(path);
		return contains;
	}

	public void enterPostTitle() {
		play.sendKeys(Titletxt, "New Post", "Enter Post Title");
	}

	public void enterPostTitle(String Title) {
		play.sendKeys(Titletxt, Title, "Enter Post Title");
	}

	public String enterPostDescription(String type) {
		String value = null;
		if (type.contains("max")) {
			value = RandomGenerator.randomString(2500);
		} else if (type.contains("min")) {
			value = RandomGenerator.randomString(1);
		} else {
			value = "Post Description";
		}
		play.sendKeys(Desctxt, value, "Post Description");
		return value;
	}

	public void enterMorePostTitle() {
		String MorePostTitle = RandomGenerator.randomString(201);
		play.sendKeys(Titletxt, MorePostTitle, "Enter more than 200 Char Title");
	}

	public void enterDescription() {
		play.sendKeys(Desctxt, "Created for Automation", "Enter Description");
	}

	public void enterDescriptionWithUser() throws AWTException {
		String userMentoin = "@Virat_iBr";
		String selectUser = "(//*[contains(text(),'Virat_iBr')])[3]";
		play.userMention(Desctxt, selectUser, userMentoin, "Enter User mentoin name");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		play.enterText(Desctxt, "\nCreated for Automation", "Enter Description");
	}

	public void enterMoreDescription() {
		String MorePostDesc = RandomGenerator.randomString(2501);
		play.sendKeys(Desctxt, MorePostDesc, "Enter more than 2500 Char Description");
	}

	public void enterGroupName() throws AWTException {
		play.sendKeys(GroupNametxt, "QA TESTING", "Enter Group Name");
		play.click(GroupNameSelect, "GroupName Select");
		Robot robot = new Robot();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER);
	}

	public void enterInvalidDescription(String Description) {
		String value;
		if (Description.contains("empty")) {
			value = "";
		} else {
			value = "     ";
		}
		play.sendKeys(Desctxt, value, "Enter Description");
	}

	public boolean postNotCreated() {
		boolean dispaly = false;
		try {
			dispaly = play.waitForVisible(success_Message, 2000, "Success Message - Post not created");
			ReportManager.logInfo("Failed: The success message appeared even though there was an error in a field.");
		} catch (TimeoutError e) {
			ReportManager.logInfo(
					"Success: Due to a field error, the post was not created, and no success message was displayed.");
		}
		return dispaly;
	}

	public void uploadMediafile() {
		play.waitForUpload(UploadfileOpt, System.getProperty("user.dir") + "\\src\\test\\resources\\Images\\peacock.jpg",
				"Uploaded File");
	}

	public void clickCreatePostButton() {
		play.click(CreatePostBtn, "Create Post Button");
	}

	public void verifyHomePageAfterPostCreation() {
		String actualText = play.getText(VerifyHomePage);
		play.verifyText(actualText, "Home");
	}

	public boolean successMessage() {
		return play.waitForVisible(success_Message, 10000, "Wait for Post creation success message");
	}

	public void verifyNoDescErrorMessage(String ErrorMessage) {
		String actualText = play.getText(NoDesErrorMess);
		play.verifyText(actualText, ErrorMessage);
	}

	public void verifyMediafileErrorMessage(String ErrorMessage) {
		String actualText = play.getText(MoreFileErrorMess);
		actualText = actualText.trim();
		play.verifyText(actualText, ErrorMessage);
	}

	public void selectMultipleMediafiles() {
		String basPaths = System.getProperty("user.dir") + "\\src\\test\\resources\\Images\\";
		String[] imageFile = { "hummingbird.webp", "IPL.webp", "IPL-Trophy.jpg", "landscape.jpg", "MSD.jpg",
				"peacock.jpg", "trophy.jpg", "wildcamel.jpg", "Agri.jpg", "Bird.jpg", "dark.jpg", "Flower.jpg",
				"Hrithik.jpg" };
		for (String imageFiled : imageFile) {
			String FullPath = basPaths + imageFiled;
			play.waitForUpload(UploadfileOpt, FullPath, "Image uploaded: " + imageFiled);
		}
	}

	public void verifyMoreDescriptionErrorMessage() {
		String actualText = play.getText(MoreDesErrorMess);
		System.out.println(actualText);
		play.verifyText(actualText, "The Description cannot exceed 2500 characters.");
	}

	public void verifyMoreTitleErrorMessage() {
		String actualText = play.getText(TitleErrorMess);
		play.verifyText(actualText, "The Title cannot exceed 200 characters.");
	}

	public void clickPostHamburgerMenu(String username) {
//		play.click(HamburgerMenu, "Hamburger menu");
		play.click((String.format(DynamicHamburgerMenu, username)), "ConfirmDeleBtn");
	}

	public void clickUserProfileDetails() {
		play.click(NavigateProfilePage, "Navigate to Profile Page");
	}

	public void clickPostDeleteOptions() {
		play.click(DeleteOption, "Post Delete options");
	}

	public void verifyPostDeleteConfirmationPopup() {
		String actualText = play.getText(DeletePopup);
		String expectedText = "Are you sure you want to delete the post?";
		if (actualText.equals(expectedText)) {
			play.verifyText(actualText, "Are you sure you want to delete the post?");
		} else {
			play.verifyText(actualText, "Are you sure you want to delete the poll?");
		}
	}

	public void clickPostDeleteConfirmButton() {
		play.click(ConfirmDeleBtn, "Post delete confirm button");
	}

	public void verifyPostDeleteSucessfully() {
		String actualText = play.getText(ConfirmPostdeletePopup);
		String expectedText = "The post has been successfully deleted.";
		if (actualText.equals(expectedText)) {
			play.verifyText(actualText, "The post has been successfully deleted.");
			ReportManager.logInfo(actualText);
		} else {
			play.verifyText(actualText, "The poll has been successfully deleted.");
			ReportManager.logInfo(actualText);
		}
	}
}
