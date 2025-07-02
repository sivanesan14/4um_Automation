package com.forum.bdd.web.screens;

import com.forum.bdd.ccl.WebActions;
import com.microsoft.playwright.Page;

public class Forum_UserProfile_Screen {

	String userName = "//div[@class='MuiBox-root css-1m9tz0m']//p[@title][1]";
	String addFriend = "//button[text()='Add Friend']";
	String clicksONFrind = "//p[text()='Friends']";
	String clicksONGroups = "//p[text()='Groups']";

	WebActions play = new WebActions();
	Page page = play.getPage();

	public void clickOnFriends() {
		play.click(clicksONFrind, "Clicked on 'Friends' tab on the user profile page");
	}

	public void clickOnGroups() {
		play.click(clicksONGroups, "Clicked on 'Groups' tab on the user profile page");
	}

	public String isProfilePageVisible() {
		page.waitForTimeout(3000);
		if (play.isVisible(userName, "Verifying user is on the profile page")) {
			String profileName = play.getText(userName);
			System.out.println("✅ The user profile name is: " + profileName);
			return profileName;

		} else {
			System.out.println("❌ Profile page is not visible or user Unable to navigate to User profile page.");
		}
		return null;

	}

	/**
	 * Verifies whether the 'Add Friend' button is visible on the profile page.
	 *
	 * @return true if the 'Add Friend' button is visible; false otherwise.
	 */
	public boolean verifyAddFriendBtn() {
		return play.isVisible(addFriend,
				"Verifying if 'Add Friend' button is visible so user can send a friend request to start the conversation.");
	}

}
