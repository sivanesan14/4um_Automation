package com.forum.bdd.web.screens;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.forum.bdd.ccl.WebActions;
import com.forum.bdd.integrations.report_utils.ReportManager;

public class Forum_Block_Screen {

	String SearchBox = "//*[@placeholder='Search Individuals, Groups etc.']";
//	String SearchResult = "//*[@role='menuitem']/p[@title='Suryaputra']";
	String SearchResult = "//*[@role='menuitem']";
	String UserProfilePage = "//*[@title='Profile']";
//	String UserProfile = "//*[@class='MuiBox-root css-195i9qz']/p[@title='Kiran khavashi']";
	String UserProfile = "//p[@title='Suryaputra']";
	String PostsTab = "//*[@id='items_List'][contains(text(),'Posts')]";
//	String UserPostHamburgerMenu = "(//*[@aria-label='more'])[2]";
	String UserPostHamburgerMenu = "(//*[@id='settings'])[1]";
	String BlockOption = "//*[@title='Block']";
	String BlockConfPopup = "//*[@title='Block User']";
	String BlockConfBtn = "//*[@id='button-contain'][contains(text(),'Block')]";
	String DynamicBlockBtn = "//p[contains(text(),'You have successfully blocked %s')]";
	String ClosePopupBtn = "//*[@aria-label='close']";
	String UnblockBtn = "//*[@id='button-contain'][contains(text(),'Unblock')]";
	String Desctxt = "//*[@class='mentionWrapper__input']";
	String UserNotFound = "//*[contains(text(),'User not found')]";
	String NoPostMsg = "//*[contains(text(),'No new posts here')]";
	String FriendTab = "//*[@title='Friends']";
	String UnblockConfPopup = "//*[@title='Unblock User']";
	String UnblockConfBtn = "(//*[@id='button-contain'][contains(text(),'Unblock')])[2]";
	String DynamicUnblockBtn = "//p[contains(text(),'You have successfully unblocked %s')]";
	String UnfriendBtn = "//*[@id='button-contain'][contains(text(),'Unfriend')]";
	String FriendListOpt = "//*[@id='Friends'][contains(text(),'Friends')]";
	String FriendList = "//*[@title='Friend List']";

	WebActions play = new WebActions();

	public void enterTextonSearchBox(String Username) {
		play.sendKeys(SearchBox, Username, "Text on Search Box");
	}

	public void selectSearchedUser() {
		play.click(SearchResult, "Select User");
	}

	public void verifyOtherUserProfile() {
		String actualText = play.getText(UserProfilePage);
		play.verifyText(actualText, "Profile");
	}

	public void clickPostTab() {
		play.click(PostsTab, "Posts Tab");
	}

	public void clickPostHamburgerMenu() {
		play.waitForVisible(UserPostHamburgerMenu, 10000, "User Post Hamburger Menu");
		play.click(UserPostHamburgerMenu, "User Post Hamburger Menu");
	}

	public void clickBlockOption() {
		play.click(BlockOption, "Block Options");
	}

	public void verifyBlockPopup() {
		String ActualText = play.getText(BlockConfPopup);
		play.verifyText(ActualText, "Block User");
	}

	public void verifyUnblockPopup() {
		String ActualText = play.getText(UnblockConfPopup);
		play.verifyText(ActualText, "Unblock User");
	}

	public void clickBlockConfirmButton() {
		play.click(BlockConfBtn, "Block Confirm Button");
	}

	public void clickUnblockConfirmButton() {
		play.click(UnblockConfBtn, "Unblock Confirm Button");
	}

	public void verifyUserBlockedSuccessPopup(String value) {
		play.waitForVisible(String.format(DynamicBlockBtn, value), 1000, "User Blocker");
	}

	public void clickCloseButton() {
		play.click(ClosePopupBtn, "Close Popup Button");
	}

	public boolean verifyUserBlockedSuccessfully() {
		return play.waitForVisible(UnblockBtn, 1000, "User blocked");
	}

	public void enterUserMention(String User) throws AWTException {
		play.sendKeys(Desctxt, "@" + User, "User Mention Name");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
	}

	public boolean verifyUserNotFoundMessage() {
		return play.waitForVisible(UserNotFound, 10000, "User Not Found");
	}

	public boolean verifyNotPostMessage() {
		return play.waitForVisible(NoPostMsg, 1000, "No new posts here");
	}

	public void clickFriendList() {
		play.click(FriendListOpt, "Friend list tab");
	}

	public boolean verifyFriendTab() {
		boolean FriendLists = play.isVisible(FriendList, "Friend List Options");
		if (FriendLists == true) {
			ReportManager.logInfo("The user is Unblocked");
		} else {
			ReportManager.logInfo("The user is Blocked");
		}
		return FriendLists;
	}

	public void verifyPostPage() {
		if (play.isVisible(NoPostMsg, "Post Page")) {
			System.out.println("The User is not Unblocked successfully");
		} else {
			System.out.println("The User is unblocked successfully");
		}
	}

	public boolean verifyUnblockButton() {
		return play.waitForVisible(UnblockBtn, 1000, "Unblock Button");
	}

	public void clickUnblockBtn() {
		play.click(UnblockBtn, "Unblock button");
	}

	public void verifyUserUnblockedSuccessPopup(String value) {
		play.waitForVisible(String.format(DynamicUnblockBtn, value), 1000, "User Unblocker");
	}

	public boolean verifyUnfriendButton() {
		return play.waitForVisible(UnfriendBtn, 1000, "Unfriend Button is present");
	}
}
