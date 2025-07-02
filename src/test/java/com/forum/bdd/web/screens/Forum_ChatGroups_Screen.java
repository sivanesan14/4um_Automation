package com.forum.bdd.web.screens;

import com.forum.bdd.ccl.WebActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Forum_ChatGroups_Screen {
	
	//Locator for the search input field
	String groupsTab = "//*[text()='Groups']";
	String searchBox = "//*[@type='text']";
	String getsearchuserlist = "//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation1 css-eyeu1w']//p";
	String searchIcon = "//div[@class='MuiFormControl-root MuiTextField-root css-i44wyl']//*[name()='svg']";
	String getGroupsList = "//*[@class='MuiCardHeader-root css-t4blon']//p";
	String getGroupName= "(//*[@class='MuiCardHeader-root css-t4blon']//p)[1]";
	String SearchValue = "//*[@class='MuiFormControl-root MuiTextField-root css-i44wyl']//input[@value]";
	String GroupName = "//div[@class='MuiBox-root css-k008qs']//p[@title][1]";

	
	WebActions play = new WebActions();
	Page page = play.getPage();
	
	/**
	 * Selects a group from the Groups tab under the chat feature.
	 * This method assumes the group list is already visible.
	 */
	public void selectGroupFromGroupTab() {
	    
//		 Locator friendList = page.locator(getGroupsList);
//			int totalFriends = friendList.count();
//
//		    
//			for (int i = 0; i < totalFriends; i++) {
//				Locator nameElement = friendList.nth(i);
//				String name = nameElement.getAttribute("title");
//				System.out.println("Found friend in list: " + name);
//
//				if (name != null && name.equalsIgnoreCase(selctGroup)) {
//					nameElement.click();
//					System.out.println("✅ Friend matched and clicked: " + name);
//					break;
//				}
//			}
	   play.click(getGroupsList, "User selected a group from the Groups tab");
	}

	
	/**
	 * Enters a Group's name into the search input field.
	 *
	 * @param name The name of the group to be entered into the search field.
	 */
	public void enterGroupsName(String name) {
		play.enterText(searchBox, name , "the search input");
		page.waitForTimeout(5000);
		//play.enterText(searchBox, name, "Entered a group '" + name + "' in the search input field.");
	}
	
	public String getGroupNameFromSerch()
	{
		 String friendFromFriendsTab=play.getAttributeValue(SearchValue, "value").trim();
		 System.out.println("The entered group in the search box is : '" + friendFromFriendsTab);
		return friendFromFriendsTab;
	}
	
	public String getGroupNameFromGroupsTab()
	{
		 String groupFromFriendsTab=play.getAttributeValue(getGroupName, "title").trim();
		 System.out.println("The entered group in the search box is : '" + groupFromFriendsTab);
		return groupFromFriendsTab;
	}
	
	
	
	
	public void selectGroupFromSearchList(String searchedGroup) {
	    page.waitForTimeout(2000);

	    String resultXpath = "//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation1 css-eyeu1w']//p[@title]";
	    Locator friendList = page.locator(resultXpath);
	    int totalFriends = friendList.count();
	    System.out.println("Total groups found: " + totalFriends);

	    for (int i = 0; i < totalFriends; i++) {
	        Locator nameElement = friendList.nth(i);
	        String name = nameElement.getAttribute("title");

	        if (name != null) {
	            String normalizedListName = name.trim().replaceAll("\\s+", " ");
	            String normalizedSearchName = searchedGroup.trim().replaceAll("\\s+", " ");

	            System.out.println("List name: '" + normalizedListName + "', Search: '" + normalizedSearchName + "'");

	            if (normalizedListName.equalsIgnoreCase(normalizedSearchName)) {
	                nameElement.scrollIntoViewIfNeeded();
	                page.waitForTimeout(1000);
	                nameElement.click(new Locator.ClickOptions().setForce(true));
	                System.out.println("✅ Clicked on group: " + name);
	                return;
	            }
	        }
	    }

	    System.out.println("❌ Group name '" + searchedGroup + "' not found or not clickable.");
	}

}
