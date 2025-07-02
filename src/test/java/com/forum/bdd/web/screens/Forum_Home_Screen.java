package com.forum.bdd.web.screens;

import com.forum.bdd.ccl.WebActions;
import com.microsoft.playwright.Page;

public class Forum_Home_Screen {

	String groupMenu = "(//li[@class='MuiListItem-root MuiListItem-gutters MuiListItem-padding css-iyzhj7'])[1]";
	String chatIcon = "(//span[text()='Chat'])[1]";
	String ForumLogo = "//*[@class='MuiBox-root css-450wu2']//*[@fill='none']";
	String userProfile = "//*[@class='MuiBox-root css-1w5sfh2']//p";

	WebActions play = new WebActions();
	Page page = play.getPage();

	public void clickONGroupMenu() {
		play.getText(groupMenu);
		play.click(groupMenu, "Checking group menu is displayed");
	}

	public boolean verify4umLogoOnHomeapge() {
		return play.isVisible(ForumLogo, "Verified 4um logo is displayed");
	}

	public void clickOnUserProfile() {
		play.click(userProfile, "Clicked on logged in user profile page");
		String Userprofile = play.getAttributeValue(userProfile, "title");
		System.out.println("The logged in profile name is: " + Userprofile);
	}

	public void clickOnChat() {
		play.click(chatIcon, "User successfully clicked on the chat feature.");
	}

	public boolean isChatFeatureEnabled() {
		return play.isVisible(chatIcon, "Verifying that the chat feature is enabled on the right-side menu.");
	}
}