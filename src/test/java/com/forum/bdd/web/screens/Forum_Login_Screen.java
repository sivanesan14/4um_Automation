package com.forum.bdd.web.screens;

import com.forum.bdd.ccl.WebActions;
import com.forum.bdd.integrations.common_utils.ConfigReader;

public class Forum_Login_Screen {
	String LoginEmailID = "//*[@id='mailId_input']";
	String LogiPassword = "//*[@id='password_input']";
//	String LoginNextBtn = "//*[@class='flexCenterJustify MuiBox-root css-1jke4yk']/button[@id='button-contain']";
	String LoginNextBtn = "//button[@id='button-contain'][contains(text(),'next')]";
	String HomeTxt = "//*[@class='MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-wp5o9t']";
	String ProfileImage = "//*[@id='user_profile']";
	String LogoutOpt = "//*[@id='long-button']";
//	String LogoutBtn = "//*[contains(text(),'Log-Out')]";
//	String LogoutBtn = "(//*[contains(text(),'Log-Out')])[1]";
	String LogoutBtn = "(//*[@role='menuitem'][contains(text(),'Log-Out')])[2]";
	String LogoutPopup = "//*[@aria-label='Are you sure you want to logout the 4um?']";
//	String LogoutConfirmBtn = "//*[@id='button-contain'][contains(text(),'Logout')]";
	String LogoutConfirmBtn = "(//*[@id='button-contain'][contains(text(),'Logout')])[2]";
	String LoginPage = "//*[@title='Join the conversation']";

	WebActions play = new WebActions();
//	Page page = play.getPage();

	public void enterURL() {
		play.openURL(ConfigReader.getValue("DevURL"));
	}

	public void enterEmailID() {
		play.sendKeys(LoginEmailID, ConfigReader.getValue("username"), "EmailID");
	}
	
	public void enterAnotherUserEmailID() {
		play.sendKeys(LoginEmailID, ConfigReader.getValue("AnotherUsername"), "EmailID");
	}

	public void enterPassword() {
		play.sendKeys(LogiPassword, ConfigReader.getValue("password"), "Password");
	}
	
	public void enterAnotherUserPassword() {
		play.sendKeys(LogiPassword, ConfigReader.getValue("password2"), "Password");
	}

	public void clickNextButton() {
		play.click(LoginNextBtn, "Login Next Button");
	}

	public void verifyHomePage() throws InterruptedException {
//		String actualText = play.getText(HomeTxt);
//		play.verifyText(actualText, "Home");
		Thread.sleep(500);
		play.isVisible(ProfileImage, "Profile Icon displaying");
	}

	public void clickLogoutOptions() {
//		try {
//			Thread.sleep(50000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		play.click(LogoutOpt, "Logout Option");
	}

	public void clickLogout() {
		play.click(LogoutBtn, "Logout Button");
	}

	public void clickLogoutPopupButton() {
		String actualText = play.getText(LogoutPopup);
		play.verifyText(actualText, "Are you sure you want to logout the 4um?");
		play.click(LogoutConfirmBtn, "Logout confirmation Popup");
	}

	public void verifyLogout() {
		String actualText = play.getText(LoginPage);
		play.verifyText(actualText, "Join the conversation");
	}

	public void successFullyLogin() {
		play.openURL("https://dev.4um.com/signin");
		play.sendKeys(LoginEmailID, "test4um123@gmail.com", "EmailID");
		play.sendKeys(LogiPassword, "Kiran$123", "Password");
		play.click(LoginNextBtn, "Login Next Button");
	}
}
