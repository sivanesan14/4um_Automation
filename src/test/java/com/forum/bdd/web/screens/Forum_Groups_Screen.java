package com.forum.bdd.web.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forum.bdd.ccl.WebActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Forum_Groups_Screen {

	String GroupsHeading = "//p[text()='Groups']";
	String myGroups = "//*[@class='MuiTabs-root css-mksl4s']//*[text()= 'My Groups']";
	String joinedGroups = "//*[@class='MuiTabs-root css-mksl4s']//*[text()= 'Joined Groups']";
	String createbtn = "//p[text()='Create Group']";
	String groupIcon = "//p[contains(., 'Create Group')]/*[name()='svg']";
	String membersCount = "//p[@id='Members' and @title]";
	String getMembers = "//*[@class='MuiBox-root css-10jr14j']//*[text()='1 Members']";
	String getDateFormat = "//*[@class='MuiBox-root css-10jr14j']//*[text()='5/9/2025']";
	String selectGroups = "//*[@class='MuiBox-root css-10jr14j']";
	String lastLoc = "";
	String avatar = "";
	String slectMylastgroup = "(//*[@class='MuiBox-root css-10jr14j'])[11]";
	String profileimage = "//*[@class='MuiBox-root css-10jr14j']//*[@id='userImg']";
	String isInitialsVisible = "//*[@class='MuiCardHeader-avatar css-1p83tvv']//*[@class='MuiAvatar-root MuiAvatar-circular MuiAvatar-colorDefault css-11rruqo']";
	String mygroupsclr = "//*[text()= 'My Groups']//*[@class='MuiTouchRipple-root css-w0pj6f']";
	String joinedGroupClr = "//*[text()= 'Joined Groups']//*[@class='MuiTouchRipple-root css-w0pj6f']";

	WebActions play = new WebActions();
	Page page = play.getPage();

	public void selectGroups() {
		play.click(selectGroups, "Selct the any one group form the group page");
	}

	public void clickOnCreateGroupIcon() {
		play.click(groupIcon, null);
	}

	public boolean verifyGroupsPage() {

		return play.isVisible(GroupsHeading, "Verifying the user succussfully navigated to groups page");

	}

	public void verifyGroupsPageUIElements(String elements) {
		System.out.println("Checking the visibility for elements: " + elements);
		switch (elements.trim()) {
		case "My_Groups":
			play.isVisible(myGroups, "Verifying My_Groups Tab is visible on groups page");

		case "Joined_Groups":
			play.isVisible(joinedGroups, "Verifying Joined_Groups Tab is visible on groups page");

		case "Create Group":

			play.isVisible(createbtn, "Verifying Create Group text is visible on groups page");

		case "Create Group Icon":
			play.isVisible(groupIcon, "Verifying Create Group Icon is visible on groups page");
			break;

		default:
			System.out.println("Unknown element: " + elements);
			throw new IllegalArgumentException("Unknown element: " + elements);
		}
	}

	public void clickOnMy_Groups() {
		play.click(myGroups, "Clicked on My_groups Tab");
	}

	public void clickOnJoined_Groups() {
		play.click(joinedGroups, "Clicked on joined_groups Tab");
	}

	public boolean verifyJoinedGroups() {
		boolean isVisible = play.isVisible(groupIcon, "Verifying create group icon on Joined_Groups");

		if (isVisible) {
			System.out.println("Checked: Create group icon is visible on Joined_Groups.");
			// Assert.assertTrue(isVisible, "Create group icon should be visible on
			// Joined_Groups.");
		} else {
			System.out.println("Checked: Create group icon is NOT visible on Joined_Groups.");
			// Assert.fail("Create group icon is not visible on Joined_Groups.");
		}

		return isVisible;
	}

	public String verifyMYGroupsColorCode() {
		Locator mygroupsclr = page.locator("//*[text()= 'My Groups']//*[@class='MuiTouchRipple-root css-w0pj6f']");

		String myGPClrCode = mygroupsclr.evaluate("ele ===> getComputedStyle(ele).mygroupsclr").toString();
		System.out.println("Background color is: " + mygroupsclr);

		if (myGPClrCode.contains("rgb(2, 131, 131)"))
			System.out.println("✅ My groups color coode for blue is" + myGPClrCode + "as expected");
		else {
			System.out.println("❌ Unexpected My_groups color coode for blue is: " + myGPClrCode);

		}
		return myGPClrCode;
	}

	public String VerifyJoinedGroupsColorCode() {
		Locator joinedGroupClr = page
				.locator("//*[text()= 'Joined Groups']//*[@class='MuiTouchRipple-root css-w0pj6f']");

		String colorCode = joinedGroupClr.evaluate("ele ===> getComputedStyle(ele).joinedGroupClr").toString();

		if (colorCode.contains("rgb(2, 131, 131)"))
			System.out.println("✅ Joined_groups color coode for blue is" + colorCode + "as expected");
		else {
			System.out.println("❌ Unexpected Joined_groups color coode for blue is: " + colorCode);

		}
		return colorCode;

	}

	public boolean verifyMyGroups() {
		boolean isVisible = play.isVisible(groupIcon, "Verifying create group icon on Joined_Groups");

		if (isVisible) {
			System.out.println("Checked: Create group icon is visible on My_Groups.");
			// Assert.assertTrue(isVisible, "Create group icon should be visible on
			// Joined_Groups.");
			return true;
		} else {

			System.out.println("Checked: Create group icon is NOT visible on My_Groups.");
			return false;
			// Assert.fail("Create group icon is not visible on Joined_Groups.");
		}

	}

	public List<String> getMembersDateFormate() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Locator groupCards = page.locator("//*[@class='MuiBox-root css-10jr14j']");

		// Get the count of group cards (in case there are fewer than 3)
		int totalGroups = groupCards.count();
		System.out.println("Total no of groups on page is: " + totalGroups);

		int groupsToCheck = Math.min(3, totalGroups);
		List<String> creationDates = new ArrayList<>();

		for (int i = 0; i < groupsToCheck; i++) {

			Locator group = groupCards.nth(i);

			String dateXpath = "//p[contains(@id, 'typographyWith-i18n') and contains(text(), '/')]";
			String groupNameXpath = "//span[contains(@class,'MuiTypography-root') and contains(@class,'MuiCardHeader-title')]";

			String groupName = group.locator(groupNameXpath).innerText().trim();
			String creationDate = group.locator(dateXpath).innerText().trim();

			// Print all together
			System.out.println("Group " + (i + 1) + ":");
			System.out.println("  Name        : " + groupName);
			System.out.println("  Created On  : " + creationDate);

			creationDates.add(creationDate);
		}
		return creationDates;

	}

	public List<Map<String, String>> getMemberscount() {

		List<Map<String, String>> groupDetailsList = new ArrayList<>();

		// Get all groups on the groups page
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Locator groupCards = page.locator("//*[@class='MuiBox-root css-10jr14j']");

		// Get the count of group cards (in case there are fewer than 3)
		int totalGroups = groupCards.count();
		System.out.println("Total no of groups on page is: " + totalGroups);

		int groupsToCheck = Math.min(3, totalGroups);

		for (int i = 0; i < groupsToCheck; i++) {

			Locator group = groupCards.nth(i);

			// Get member count and date from within the group card
			String membersXpath = "//p[contains(text(),'Members')]";
			String groupNameXpath = "//span[contains(@class,'MuiTypography-root') and contains(@class,'MuiCardHeader-title')]";

			// String Member = play.getText(membersXpath);
			String groupName = group.locator(groupNameXpath).innerText().trim();
			String membersText = group.locator(membersXpath).innerText().trim();

			System.out.println("Group " + (i + 1) + ":");
//			System.out.println("  Name        : " + groupName);
//			System.out.println("  Members     : " + membersText);

			group.click();
			// System.out.println("✔️ Group selected from 'My Groups' tab.");

			String Memberscount = play.getText(membersCount);
			Map<String, String> groupData = new HashMap<>();

			groupData.put("groupName", groupName);
			groupData.put("membersText", membersText);
			groupData.put("membersCount", Memberscount);

			groupDetailsList.add(groupData);

			play.getPage().navigate("https://dev.4um.com/groups");

		}
		return groupDetailsList;

	}

	public void verifyScrollingfunction() {
		page.waitForTimeout(2000); // wait for page to load

		// Group count before scrolling
		Locator groupList = page.locator("xpath=//*[@class='MuiBox-root css-10jr14j']");
		int countBefore = groupList.count();
		System.out.println("Group count before scroll: " + countBefore);

		// Scroll down
		for (int i = 0; i < 10; i++) { // Increase '5' if needed
			page.mouse().wheel(0, 500); // scroll 500 pixels each time
			page.waitForTimeout(1500); // wait for items to lazy-load
		}

		// Optional: Scroll up a bit at the end
		page.mouse().wheel(0, -300);
		page.waitForTimeout(1000);

		// Scroll up
		page.mouse().wheel(0, -40);
		page.waitForTimeout(1000);

		// Group count after scroll
		int countAfter = groupList.count();
		System.out.println("Group count after scroll: " + countAfter);

		if (countBefore != countAfter) {
			System.out.println("✅ Scroll triggered more group items.");
		} else {
			System.out.println("⚠️ Scroll happened but did not load more groups.");
		}
	}

	public void verifyScrollToLastGroup() {
		play.PageDown(2);
		play.scrollToElement(slectMylastgroup, "scrolled to last group of particular page");
	}

	public void verifyPagination() {
		Locator totalGroups = page.locator("//*[@class='MuiBox-root css-10jr14j']");
		System.out.println(totalGroups.count());
		verifyScrollToLastGroup();
		System.out.println(totalGroups.count());
	}

	public List<Map<String, Boolean>> verifyProfileImageAndInitials() {
		page.waitForTimeout(3000);

		List<Map<String, Boolean>> result = new ArrayList<>();

		Locator groupCards = page.locator("//*[@class='MuiBox-root css-10jr14j']");
		int totalGroups = groupCards.count();
		System.out.println("Total no of groups on page is: " + totalGroups);

		int groupsToCheck = Math.min(10, totalGroups);

		for (int i = 0; i < groupsToCheck; i++) {
			Locator group = groupCards.nth(i);

			// Extract group name
			String groupNameXpath = "//span[contains(@class,'MuiTypography-root') and contains(@class,'MuiCardHeader-title')]";
			String groupName = group.locator(groupNameXpath).innerText().trim();

			// Check for profile image inside the current group card
			Locator profileImage = group.locator("//img[contains(@src, 'https')]"); // image tag with src
			Locator initialsElement = group
					.locator("//div[contains(@class, 'MuiAvatar-root') and not(descendant::img)]");

			boolean isProfileImageVisible = profileImage.isVisible();
			boolean isInitialsVisible = initialsElement.isVisible();

			Map<String, Boolean> groupResult = new HashMap<>();
			groupResult.put("isProfileImageVisible", isProfileImageVisible);
			groupResult.put("isInitialsVisible", isInitialsVisible);
			result.add(groupResult);

			if (isProfileImageVisible) {
				System.out.println("✅ Profile image is visible for group: " + groupName);
			} else if (isInitialsVisible) {
				String initialsText = initialsElement.innerText().trim();
				System.out.println("✅ Initials are shown for group: " + groupName + " → " + initialsText);
			} else {
				throw new AssertionError("❌ Neither profile image nor initials visible for group: " + groupName);
			}
		}
		return result;
	}

}
