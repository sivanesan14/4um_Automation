package com.forum.bdd.web.screens;

import java.util.ArrayList;
import java.util.List;

import com.forum.bdd.ccl.WebActions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Forum_CreateMessage_Screen {

	String createMesgIcon = "//*[@class='MuiBox-root css-1a5ho4p']//*[@type='button']";
	String SelectFrinds = "//li[@class='MuiListItem-root MuiListItem-gutters css-lmd8nq']";
	String getfrindsNmae = "//*[contains(@class, 'MuiListItemButton-root') and contains(@text(), '/')]";

	String creatMsgHeading = "//h6[text()='Create Message']";
	String pop_UpcancelBtn = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-148fdm8']";

	String To_text = "//p[text()='To:']";
	String toSearchBox = "//div[@class='MuiBox-root css-c0ffkd']//input[@type='text']";

	String textMsgBox = "//textarea[@placeholder='Write your message...']";
	String chatLimitText = "//p[@class='MuiTypography-root MuiTypography-body1 css-jrni1w']";
	String sendBtn = "//button[text()='Send']";
	String cancelBtn2 = "//button[text()='Cancel']";

	WebActions play = new WebActions();
	Page page = play.getPage();

	/**
	 * Verifies the visibility of specific UI elements in the "Create Message"
	 * pop-up screen.
	 *
	 * @param element The name of the UI element to verify.
	 * @throws IllegalArgumentException if an unknown element is passed.
	 */
	public void verifyCreateMessageUIElements(String element) {
		System.out.println("Checking the visibility of element on Create Message pop-up screen: " + element);

		switch (element.trim()) {
		case "Create message heading":
			play.isVisible(creatMsgHeading,
					"Verified that the Create message heading is visible on the Create Message pop-up.");
			break;

		case "Pop_Up Cancel button":
			page.waitForTimeout(2000);
			play.isVisible(pop_UpcancelBtn, "Verified that the Cancel button is visible on the Create Message pop-up.");
			break;

		case "Search input box":
			play.isVisible(toSearchBox, "Verified that the search input box is visible on the Create Message pop-up.");
			break;

		case "To: text":
			play.isVisible(To_text, "Verified that the 'To:' label is visible on the Create Message pop-up.");
			break;

		case "Chracter Limit":
			page.waitForTimeout(2000);
			play.isVisible(chatLimitText,
					"Verified that the character limit text is visible on the Create Message pop-up.");
			break;

		case "Send button":
			play.isVisible(sendBtn, "Verified that the Send button is visible on the Create Message pop-up.");
			break;

		case "Cancel button":
			play.isVisible(cancelBtn2, "Verified that the Cancel button is visible on the Create Message pop-up.");
			break;

		default:
			System.out.println("Unknown element: " + element);
			throw new IllegalArgumentException("Unknown element: " + element);
		}
	}

	/**
	 * Enters multiple friend names into the 'To:' search input field one by one and
	 * selects each friend from the suggestions.
	 *
	 * @param userNames List of friend names to enter and select
	 */
	public void enterFriendsNamesInToFields(List<String> userNames) {
		
		play.isVisible(toSearchBox, "Verifying 'To:' search input box is visible");
		String friendXpath = "//ul[@class='MuiAutocomplete-listbox css-fbss0f']//p[@title]";
		int index = 1;
		for (String name : userNames) {
			play.enterText(toSearchBox, name, index + " user entered");
			page.click(friendXpath);
			//
			index++;
		}
	}

	/**
	 * Selects a friend from the "To:" autocomplete dropdown list in the Create
	 * Message pop-up.
	 */
	public void selectFriendFromToTab() {
		String friendXpath = "//ul[@class='MuiAutocomplete-listbox css-fbss0f']//p[@title]";

		// Optionally verify the friend name is visible before clicking
	 play.isVisible(friendXpath, "Friend name is displayed in the search result");
	 play.click(friendXpath, "Selected");

	}

	/**
	 * Verifies and prints the names and count of group members by hovering over the
	 * group profile name.
	 */
	public List<String> verifyGroupMembers() {
		Locator groupMembers = page.locator(
				"//*[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation8 MuiPopover-paper MuiMenu-paper MuiMenu-paper css-pwxzbm']//p[@title]");

		List<String> creationDates = new ArrayList<>();
		String groupProfileNameHover = "(//*[@class='MuiBox-root css-1n7cmb5']//p)[2]";
		play.mouseHover(groupProfileNameHover, "Mouse over to the group profile name");

		page.waitForTimeout(3000);

		int membersCount = groupMembers.count();
		System.out.println("The group members count is ==> " + membersCount);
		

		for (int i = 0; i < membersCount; i++) {
			Locator member = groupMembers.nth(i);
			String memberName = member.getAttribute("title");
			System.out.println("Group member are: " + memberName);
			creationDates.add(memberName);
			
		}
		return creationDates;
		
	}

	/**
	 * Enters a predefined message into the group chat message input box.
	 */
	public void enterMSGForGroupChat() {
		play.enterText(textMsgBox, "Hello Team good morning this message is generated through automation code",
				" ==> this is text message for the group chat");
	}

	/**
	 * Clicks on the Send button to send the message in the group chat.
	 */
	public void clickOnSendBtn() {
		play.click(sendBtn, "User successfully started a group conversation");
	}

	/**
	 * Retrieves the error message text displayed when attempting to add more
	 * members than allowed.
	 *
	 * @return The error message text as a String.
	 */
	public String verifyAddMoreMemberErrorMsg() {
		String errorMsgXPath = "//div[@class='MuiAlert-message css-1xsto0d']";
		String errorMsgText = play.getText(errorMsgXPath);
		return errorMsgText;
	}

}
