package com.forum.bdd.web.screens;

import com.forum.bdd.ccl.WebActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Forum_FrindsList_Screen {

	String friendListHeader = "//p[text()='Friend List']";
	String chatIcon = "//div[@class='flexCenter MuiBox-root css-u6ord']//button[@type='button']";
	String selFrind = "//div[@class='MuiBox-root css-ds2abh']//p[@title]";
	String navigateBack = "//div[@class='MuiBox-root css-163nafr']//*[@fill='none']";

	WebActions play = new WebActions();
	Page page = play.getPage();

	/**
	 * Verifies that the user has successfully navigated to the Friend List page by
	 * checking the visibility of the friend list header.
	 *
	 * @return true if the friend list header is visible, false otherwise
	 */
	public boolean verifyFriendListPage() {
		return play.isVisible(friendListHeader, "User successfully navigated to the Friend List page");
	}

	/**
	 * Checks whether the chat icon is visible for at least one friend in the friend
	 * list.
	 *
	 * @return true if any chat icon is visible, false otherwise
	 */
	public boolean isChatIconVisibleForAnyFriend() {
		page.waitForTimeout(3000);

		Locator friendList = page.locator(chatIcon);
		int totalFriendsList = friendList.count();
		System.out.println("Total friends with chat icon on the page: " + totalFriendsList);

		for (int i = 0; i < totalFriendsList; i++) {
			Locator friendChatIcon = friendList.nth(i);

			if (friendChatIcon.isVisible()) {
				System.out.println("Chat icon is visible for friend at index: " + i);
				return true;
			}
		}

		System.out.println("No chat icon is visible for any friend.");
		return false;
	}

	/**
	 * Selects a user from the friend list page by matching the friend's name, then
	 * clicks the corresponding chat icon if visible.
	 * 
	 * @param friendName the name of the friend to select
	 * @return the friend's name if found and chat icon clicked, otherwise null
	 */
	public String selectUserFromFriendListPage(String friendName) {
		page.waitForTimeout(3000);

		Locator friendList = page.locator(selFrind); // List of friend elements with title attribute
		Locator chatIcons = page.locator(chatIcon); // Corresponding chat icons

		int totalFriends = friendList.count();
		int totalChatIcons = chatIcons.count();

		System.out.println("Total Friends: " + totalFriends);
		System.out.println("Total Chat Icons: " + totalChatIcons);

		int totalToCheck = Math.min(totalFriends, totalChatIcons); // Safety check

		for (int i = 0; i < totalToCheck; i++) {
			Locator friend = friendList.nth(i);
			String friendTitle = friend.getAttribute("title");

			if (friendTitle != null && friendTitle.equalsIgnoreCase(friendName)) {
				System.out.println("✅ Found friend: " + friendTitle);

				Locator chatIcon = chatIcons.nth(i);
				if (chatIcon.isVisible()) {
					chatIcon.click();
					System.out.println("✅ Clicked chat icon for: " + friendTitle);
					return friendTitle;
				} else {
					System.out.println("❌ Chat icon not visible for: " + friendTitle);
					return null;
				}
			}
		}

		System.out.println("❌ Friend name '" + friendName + "' not found.");
		return null;
	}

}
