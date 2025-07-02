package com.forum.bdd.web.screens;

import com.forum.bdd.ccl.WebActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Forum_Chat_Screen {

	// Locatore for search fiels on Chat window

	String chatHeading = "//P[text()='Chat']";
	String friendsTab = "//*[text()='Friends']";	
	String groupsTab = "//button[text()='Groups']";	
	String searchBox = "//*[@type='text']";
	String SearchValue = "//*[@class='MuiFormControl-root MuiTextField-root css-i44wyl']//input[@value]";
	String getsearchuserlist = "//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation1 css-eyeu1w']//p";
	String searchIcon = "//div[@class='MuiFormControl-root MuiTextField-root css-i44wyl']//*[name()='svg']";

	String createMesgIcon = "//*[@class='MuiBox-root css-1a5ho4p']//*[@type='button']";

	String groupsList = "//*[@class='MuiCardHeader-root css-t4blon']";
	String frindsTabList = "//ul[@class='MuiList-root MuiList-padding css-1ontqvh']";

	String getselectfrindlist = "//*[@class='MuiListItem-root MuiListItem-gutters css-lmd8nq']//p";
	String getuserNameByprofile = "//*[@class='MuiAvatar-root MuiAvatar-circular css-x74fp']";
	String getUserNameFromFriendTab= "//*[@class='MuiListItem-root MuiListItem-gutters css-lmd8nq']";

	String typeYourMsg = "//textarea[@id='messageTextField']";
	String sendIcon = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-5zkx04']";
	String getMsgStatus = "//*[contains(@class, 'MuiBox-root css-149n1zq') and contains(@text(), '/ ')]";

	String getuserName = "//div[@class='MuiBox-root css-k008qs']//p[text()='Kiran khavashi']";
	// String getuserName = "//ul[@class='MuiList-root MuiList-padding
	// css-1819p45']//p";

	WebActions play = new WebActions();
	Page page = play.getPage();

	/**
	 * Verifies whether the user has successfully navigated to the chat page.
	 *
	 * @return true if the chat page heading is visible, false otherwise.
	 */
	public boolean verifyChatPage() {
		return play.isVisible(chatHeading, "User successfully navigated to the chat page.");
	}

	/**
	 * Verifies the visibility of specific UI elements on the chat page.
	 *
	 * @param element The name of the UI element to verify.
	 * @throws IllegalArgumentException if an unknown element is passed.
	 */
	public void verifyChatUIElements(String element) {
		System.out.println("Checking the visibility of element on chat page: " + element);

		switch (element.trim()) {
		case "Friends_Tab":
			play.isVisible(friendsTab, "Verified that the Friends tab is visible on the chat page.");
			break;

		case "Groups_Tab":
			play.isVisible(groupsTab, "Verified that the Groups tab is visible on the chat page.");
			break;

		case "Create Message Icon":
			play.isVisible(createMesgIcon, "Verified that the Create Message Icon is visible on the chat page.");
			break;

		case "Search Input Field":
			play.isVisible(searchBox, "Verified that the Search Input Field is visible on the chat page.");
			break;

		default:
			System.out.println("Unknown element: " + element);
			throw new IllegalArgumentException("Unknown element: " + element);
		}
	}

	/**
	 * Enters a friend's name into the search input field.
	 *
	 * @param name The name of the friend to be entered into the search field.
	 */
	public void enterFriendsName(String name) {
		play.enterText(searchBox, name, "Entered friend name '" + name + "' in the search input field.");
	}

	/**
	 * Retrieves and verifies the search result for a friend's name. It compares the
	 * typed value in the search box with the names listed in the search result
	 * dropdown.
	 */
	public boolean getSearchResult() {
//		// Step 1: Retrieve the value typed in the search input field
	//	String searchInputXpath = "//input[@placeholder='Search']";
		String resultXpath = "//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation1 css-eyeu1w']//p[@title]";
		page.waitForTimeout(3000); // Wait for search results to appear
		//String searchedFriend = play.getAttributeValue(searchInputXpath, "value");
		//System.out.println("The result is: " + searchedFriend);
		boolean isSearchedGroupVisible=page.isVisible(resultXpath);
		return isSearchedGroupVisible;
	}

	/**
	 * Selects a friend from the search dropdown list that matches the typed name.
	 * It retrieves the value from the search input, matches it against the list,
	 * and clicks the matching result.
	 */
	public void selectFriendFromSearchList(String searchedFriend) {
		page.waitForTimeout(3000); // Wait for search results to appear
	
		// Step 2: Locate all friend names in the search result dropdown
		//String resultXpath = "//*[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation1 css-eyeu1w']//ul[@class='MuiList-root MuiList-padding css-1819p45'][2]//p[@title]";
		String resultXpath = "//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation1 css-eyeu1w']//p[@title]";
		Locator friendList = page.locator(resultXpath);
		int totalFriends = friendList.count();

		// Step 3: Iterate through friend names and select the matching one
		for (int i = 0; i < totalFriends; i++) {
			Locator nameElement = friendList.nth(i);
			String name = nameElement.getAttribute("title");
			System.out.println("Found friend in list: " + name);

			if (name != null && name.equalsIgnoreCase(searchedFriend)) {
				nameElement.click();
				System.out.println("✅ Friend matched and clicked: " + name);
				break;
			}
		}
	}

//		public void enterMessageInBox()
//		{
//			play.enterText(typeYourMsg, "Hi this is rebeca from Testing team", " ==>This is message to send a friend");
//		}

	/**
	 * Enters the specified name into the search input box.
	 *
	 * @param name The name to enter into the search box.
	 */
	public void enterName(String name) {
		play.enterText(searchBox, name, "User entered a name in the search box");
	}
	
	public String getFriendName()
	{
		 String friendFromFriendsTab=play.getText(getUserNameFromFriendTab);
		return friendFromFriendsTab;
	}
	
	public String getFriendNameFromSearch()
	{
		page.waitForTimeout(3000);
		 String friendFromSearch=play.getAttributeValue(SearchValue, "value");
		return friendFromSearch;
	}


	/**
	 * Selects a friend from the Friends tab.
	 */
	public void selectAnyVisibleFriendFromFriendTab(String searchedFriend) {
		
//	    String userGroupsName = "//*[@class='MuiAvatarGroup-root css-kszoy9']";
//	    
//	    // Check if the user group avatar is visible (meaning user not found)
//	    boolean userGroupsVisible = play.isVisible(userGroupsName, "Checking if group avatars are visible");
//	    boolean isgetuserNameByprofile = play.isVisible(getuserNameByprofile, "Checking if user avatars are visible");
	    
	    Locator friendList = page.locator(getselectfrindlist);
		int totalFriends = friendList.count();

	    
		for (int i = 0; i < totalFriends; i++) {
			Locator nameElement = friendList.nth(i);
			String name = nameElement.getAttribute("title");
			System.out.println("Found friend in list: " + name);

			if (name != null && name.equalsIgnoreCase(searchedFriend)) {
				nameElement.click();
				System.out.println("✅ Friend matched and clicked: " + name);
				break;
			}
		}
	}

	/**
	 * Clicks on the Create Message icon to initiate a group chat.
	 */
	public void clickCreateMsgIcon() {
		play.isVisible(createMesgIcon, "Verifying Create Message icon is visible");
		play.click(createMesgIcon, "User successfully clicked on Create Message icon for group chat");
	}
	
	/**
	 * Clicks on the Groups tab in the chat window to switch the view to groups.
	 */
	public void selectGroupsTab() {
	    play.isVisible(groupsTab, "Verifying that the Groups tab is visible before clicking");
	    play.click(groupsTab, "Selected the Groups tab from the chat window");
	}


}
