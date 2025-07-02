package com.forum.bdd.web.screens;

import com.forum.bdd.ccl.WebActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Forum_GroupsList_Screen {

	String groupListHeader = "//p[text()='Groups List']";
	String chatIcon = "//div[@class='flexCenter MuiBox-root css-u6ord']//button[@type='button']";
	String selGroup = "//div[@class='MuiBox-root css-ds2abh']//p[@title]";
	String navigateBack = "//div[@class='MuiBox-root css-163nafr']//*[@fill='none']";

	WebActions play = new WebActions();
	Page page = play.getPage();

	public boolean verifyGroupListPage() {
		return play.isVisible(groupListHeader, "User successfully navigated to the Friend List page");
	}

	public boolean isChatIconVisibleForAnyGroup() {
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

	public String selectGroupsFromGroupListPage(String GroupName) {
		page.waitForTimeout(3000);

		Locator GroupList = page.locator(selGroup); // List of friend elements with title attribute
		Locator chatIcons = page.locator(chatIcon); // Corresponding chat icons

		int totalGroups = GroupList.count();
		int totalChatIcons = chatIcons.count();

		System.out.println("Total Groups: " + totalGroups);
		System.out.println("Total Chat Icons: " + totalChatIcons);

		int totalToCheck = Math.min(totalGroups, totalChatIcons); // Safety check

		for (int i = 0; i < totalToCheck; i++) {
			Locator Group = GroupList.nth(i);
			String GroupTitle = Group.getAttribute("title");

			if (GroupTitle != null && GroupTitle.equalsIgnoreCase(GroupName)) {
				System.out.println("✅ Found friend: " + GroupTitle);

				Locator chatIcon = chatIcons.nth(i);
				if (chatIcon.isVisible()) {
					chatIcon.click();
					System.out.println("✅ Clicked chat icon for: " + GroupTitle);
					return GroupTitle;
				} else {
					System.out.println("❌ Chat icon not visible for: " + GroupTitle);
					return null;
				}
			}
		}

		System.out.println("❌ Group name '" + GroupName + "' not found.");
		return null;
	}

}
