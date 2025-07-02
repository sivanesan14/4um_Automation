package com.forum.bdd.web.screens;

import com.forum.bdd.ccl.WebActions;
import com.forum.bdd.integrations.common_utils.RandomGenerator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Forum_ChatWindow_Screen {

	static String LoginEmailID = "//*[@id='mailId_input']";
	static String LogiPassword = "//*[@id='password_input']";
	static String LoginNextBtn = "(//button[normalize-space()='next'])[1]";

	String chatIcon = "(//span[text()='Chat'])[1]";

	String getuserName = "//div[@class='MuiBox-root css-k008qs']//p[@title][1]";
	String typeYourMsg = "//textarea[@id='messageTextField']";
	String inputField = "//*[@class='MuiFormControl-root MuiTextField-root css-gisz7v']";
	String chracterLimit = "//p[text()='Input Limit Reached']";

	String sendIcon = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-5zkx04']";
	String sendBtn = "//button[text()='Send']";

	String blockVerifyText = "//p[text()='4um member unable to receive messages.']";

	// Locator for Hamburger menu and options
	String hamburgerMenu = "(//button[contains (@class, 'css-1yxmbwk')])[3]";
	String MenuOption = "//*[@class='MuiList-root MuiList-padding MuiMenu-list css-r8u8y9']//p[@title]";
	String menuOptionIcon = "//*[@class='MuiListItemIcon-root css-1f8bwsm']";

	// Locator for delete Confirmation Pop_Message
	String confirmation_Pop = "(//*[contains(@class, 'MuiPaper-elevation0 MuiDialog-paper MuiDialog-paperScrollPaper')]//p[@title])[2]";
	String block_Pop_Header = "//p[text()='Block User']";
	String confirmation_PopText = "//*[@class='flexCenterJustify flexCenterColumn MuiBox-root css-0']//p[text()=' $']";
	String delet_Pop_Block = "//Button[text()='Block']";
	String cancel_Pop = "//Button[text()='Cancel']";
	String delet_Pop_Unblock = "//Button[text()='Unblock']";

	String unBlock_Pop_Header = "//p[text()='Unblock User']";

	// Locator for delete success message pop_up message
	String succussMsgIcon = "//*[@class='MuiDialogContent-root css-1xfpytt']//*[@fill='none']";
	String succussMsg = "//p[contains(text(), 'You have successfully blocked Suryaputra.')]";
	String unblocksuccessMsg = "//p[contains(text(), 'You have successfully unblocked')]";
	String closePop_UP = "//button[@aria-label='close']";

	String Emoji_Palate = "//*[@class='MuiBox-root css-jhx1m8']//*[@class='MuiBox-root css-kp3su1']";
	String ThumbIcon = "//*[@class='MuiBox-root css-jhx1m8']//*[@class='MuiBox-root css-1wn7xef']//*[@fill='url(#pattern0_1269_10793)']";
	String DeleteIcon = "//*[@class='MuiBox-root css-jhx1m8']//*[@class='MuiBox-root css-1wn7xef']//*[@mask='url(#mask0_1269_10801)']";
	String EmojiRecat = "//*[@class='MuiBox-root css-jhx1m8']//*[@class='MuiBox-root css-1wn7xef']";

//Locator for delete Conversation Chat 
	String deletIcon = "//*[@class='MuiBox-root css-1n2mv2k']//*[@mask='url(#mask0_1269_10801)']";
	String deletePopMSG = "//p[text()='Are you sure you want to delete the conversation?']";
	String deletePop2ndText = "//p[text()='This action cannot be undone.']";
	String DeleteBtn = "//button[text()='Delete']";
	String CancelBtn = "//button[text()='Cancel']";

	// Locator for Report a user
	String reportDetail = "//p[text()='Report Details']";
	String SeletReason = "//*[text()='Select Reason']";
	String reportDescription = "//*[@placeholder='Write description']";
	String reportSuccessPopup = "//p[text()='Your report has been submitted. Our Admin will review and take appropriate action.']";
	String ReportSendBtn = "//button[text()='Send']";
	String ReportStatus = "//li[@aria-disabled='true']";
	String reasonsfromDropDown = "//ul[@class='MuiList-root MuiList-padding MuiMenu-list css-r8u8y9']//li[text()]";

	// Locator for leave group pop screen
	String leaveGroupHeading = "//p[text()='Request to Leave Group']";
	String successMessage = "//*[@class='MuiDialogContent-root css-1xfpytt']";
	String leaveBtn = "//button[text()='Leave']";
	String conversionText = "//p[text()='Join the 4um Group to resume the conversation.']";

	WebActions play = new WebActions();
	Page page = play.getPage();
	Forum_Login_Screen LP = new Forum_Login_Screen();
	static Forum_Chat_Screen CP = new Forum_Chat_Screen();
	// Forum_ChatWindowPage_Page CWP= new Forum_ChatWindowPage_Page();

	/**
	 * Verifies that the user has successfully navigated to a friend's chat window
	 * and returns the friend's username displayed.
	 *
	 * @return The username text shown in the chat window.
	 */

	public String verifyChatWindow() {
		page.waitForTimeout(2000);

		String userName = play.getText(getuserName).trim();
		return userName;
	}

	/**
	 * Enters the specified message text into the message input box.
	 *
	 * @param message The message text to send to a friend.
	 * @return
	 */
	public String enterMessageInBox(String message) {
		String text = RandomGenerator.randomString(1900);
//		System.out.println(message);
		play.enterText(typeYourMsg, text, "Entered message to send to friend");
		page.waitForTimeout(2000);
		return message;
	}

	public void clickonSendButton() {
		boolean UserMsgToUser = play.isVisible(sendIcon, "Checking weather user sending a message to frind");
		boolean userMSStoGroup = play.isVisible(sendBtn, "Checking weather user sending a message to Group");

		if (UserMsgToUser) {
			play.click(sendIcon, "Succussfully sent a message to friend");
		} else if (userMSStoGroup) {
			play.click(sendBtn, "Succussfully sent a message to group");
		}
	}

	/**
	 * Retrieves the 'title' attribute of the most recent sent message in the chat.
	 *
	 * @return The 'title' attribute of the latest sent message, or null if no
	 *         messages found.
	 */

	public String verifySentMessage() {

		String userGroup = "//div[@class='MuiAvatarGroup-root css-kszoy9']";
		String froumGroups = "//div[@class='MuiBox-root css-k008qs']//p[@title][2]";

		String messageXpath = null;

		if (play.isVisible(froumGroups, "Verifying forum group visible")) {
			page.waitForTimeout(3000);
			messageXpath = "//*[@class='MuiBox-root css-jhx1m8']//*[@class='MuiBox-root css-xm69l9']//*[@class='MuiBox-root css-ijpki2']//p";

		} else if (play.isVisible(userGroup, "Verifying user group visible")) {
			page.waitForTimeout(3000);
			messageXpath = "//*[@class='MuiBox-rootcss-jhx1m8']//*[@class='MuiBox-root css-ijpki2']//p";

		} else if (play.isVisible(getuserName, "Verifying user name displayed")) {
			page.waitForTimeout(3000);
			messageXpath = "//*[@class='MuiBox-root css-jhx1m8']//*[@class='MuiBox-root css-1sg8vbr']//P";

		} else {
			System.out.println("No message container found for the current context.");
			return null;
		}

		Locator messages = page.locator(messageXpath);
		int totalMessages = messages.count();

		System.out.println("Total messages displayed in chat: " + totalMessages);

		if (totalMessages == 0) {
			System.out.println("No sent messages found.");
			return null;
		}
		// Get the last (most recent) message's 'title' attribute
		Locator lastMessage = messages.nth(totalMessages - 1);
		page.waitForTimeout(3000);
		String recentChatTitle = lastMessage.textContent();

		if (lastMessage.isVisible()) {

			System.out.println("User sent message is: " + recentChatTitle);

			// return null;
		} else {
			System.out.println("User Sent message ' " + recentChatTitle + " ' is not visible on chat window.");
		}

		return recentChatTitle;
	}

	/**
	 * Verifies and returns the status text of a sent message.
	 *
	 * @return The message status text.
	 */
	public String verifyStatusOfSendMsg() {
		String statusXPath = "//*[contains(@class, 'MuiBox-root css-149n1zq') and contains(text(), '')]";

//		String statusTimeXPath = "//*[contains(@class, 'css-149n1zq') and contains(text(), '')]//p";

		String msgStatus = play.getText(statusXPath);

		if (msgStatus != null && !msgStatus.trim().isEmpty()) {
			System.out.println("Message status is: " + msgStatus);
		} else {
			System.out.println("Message status text is empty or not found.");
		}

		return msgStatus;
	}

	/**
	 * Verifies the message tick status displayed in the chat.
	 * 
	 * <ul>
	 * <li>Single tick ✅ indicates the message was sent.</li>
	 * <li>Double gray ticks ✅✅ indicate the message was delivered.</li>
	 * <li>Double blue ticks ✅✅ indicate the message was read.</li>
	 * </ul>
	 * 
	 * The method checks the visibility and color of the tick icons to determine and
	 * print the correct status.
	 */

	public void verifyStatusOfTickMark() {
		// String sentTick = "div.MuiBox-root.css-149n1zq svg[data-testid*='DoneIcon']";
		String sentTick = "//*[@class='MuiBox-root css-0']//*[@class='MuiBox-root css-70qvj9'][1]";
		// String deliveredTick = "div.MuiBox-root.css-149n1zq
		// svg[data-testid*='DoneAllIcon']";
		String deliveredTick = " //*[@class='MuiBox-root css-0']//*[@class='MuiBox-root css-70qvj9'][1]";
		// String readTick = "div.MuiBox-root.css-149n1zq
		// svg[data-testid*='DoneAllIcon']";
		String readTick = " //*[@class='MuiBox-root css-0']//*[@class='MuiBox-root css-70qvj9'][1]";

		boolean isSentVisible = play.isVisible(sentTick, "Verifying message sent status");
		boolean isDeliveredVisible = play.isChecked(deliveredTick, "Checking message delivered status");

		// Use locator to fetch 'fill' or 'color' style
		Locator readTickLocator = page.locator(readTick);
		String tickColor = (String) readTickLocator.first().evaluate("el => getComputedStyle(el).color");

		if (isSentVisible && !isDeliveredVisible) {
			System.out.println("✅ Message successfully sent (single tick)");
		} else if (isDeliveredVisible && !tickColor.contains("rgb(0, 0, 255)")) {
			System.out.println("✅✅ Message delivered (double tick)");
		} else if (tickColor.contains("rgb(0, 0, 255)") || tickColor.contains("blue")) {
			System.out.println("✅✅ (blue) Message read by recipient");
		} else {
			System.out.println("⚠️ Status unknown or not visible");
		}
	}

	/**
	 * Verifies if unread message count is visible in the friends tab.
	 */
	public void verifyUnreadMSGCount() {
		String unreadCountLocator = "//ul[@class='MuiList-root MuiList-padding css-1ontqvh']//*[@class='MuiBox-root css-1btt3by']//p";

		boolean isVisible = play.isVisible(unreadCountLocator, "Verifying unread message count is displayed");

		if (isVisible) {
			System.out.println("✅ Unread messages are displayed in the friends tab.");
		} else {
			System.out.println("❌ No unread messages are displayed in the friends tab.");
		}

	}

	public void verifyOnlineAndOffline() {

	}

	/*
	 * public static Page launchEdgeWithDifferentUser() {
	 * 
	 * // New Playwright instance Playwright playwright = Playwright.create();
	 * 
	 * // Launch Edge browser Browser edgeBrowser = playwright.chromium()
	 * .launch(new
	 * BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
	 * 
	 * // Create new context and page BrowserContext edgeContext = edgeBrowser
	 * .newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
	 * 
	 * Page edgePage = edgeContext.newPage();
	 * 
	 * edgePage.navigate("https://dev.4um.com/signin");
	 * 
	 * edgePage.fill(LoginEmailID, "khawasi1431@gmail.com");
	 * edgePage.fill(LogiPassword, "Kiran$178"); edgePage.click(LoginNextBtn);
	 * CP.clickOnChat();
	 * 
	 * return edgePage;
	 * 
	 * }
	 * 
	 * public class MultiBrowserHelper implements Runnable {
	 * 
	 * private final String email; private final String password;
	 * 
	 * public MultiBrowserHelper(String email, String password) { this.email =
	 * email; this.password = password; }
	 * 
	 * @Override public void run() { Playwright playwright = Playwright.create();
	 * 
	 * Browser edgeBrowser = playwright.chromium() .launch(new
	 * BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
	 * BrowserContext edgeContext = edgeBrowser .newContext(new
	 * Browser.NewContextOptions().setViewportSize(1920, 1080)); Page edgePage =
	 * edgeContext.newPage(); edgePage.navigate("https://dev.4um.com/signin");
	 * edgePage.fill(LoginEmailID, "khawasi1431@gmail.com");
	 * edgePage.fill(LogiPassword, "Kiran$178"); edgePage.click(LoginNextBtn);
	 * edgePage.click(chatIcon);
	 * 
	 * edgePage.waitForTimeout(5000); // simulate time for actions // Navigate to
	 * chat verifyUnreadMSGCount(); // Optionally, perform chat or other actions
	 * System.out.println("✅ Edge user interaction completed.");
	 * 
	 * edgeBrowser.close(); playwright.close(); } }
	 * 
	 * public void startConversationInEdge() {
	 * 
	 * Thread edgeThread = new Thread(new
	 * MultiBrowserHelper("khawasi1431@gmail.com", "Kiran$123"));
	 * edgeThread.start();
	 * 
	 * try { // Block Chrome test execution until Edge is done edgeThread.join(); }
	 * catch (InterruptedException e) { e.printStackTrace(); }
	 * 
	 * System.out.println("✅ Resuming Chrome test after Edge finished"); }
	 */

	public void clickOnHamburgerMenu() {
		page.waitForTimeout(3000);
		play.click(hamburgerMenu, "Clicked on humberger menu");
	}

	/**
	 * Selects a menu option from a list of available options based on the provided
	 * title.
	 *
	 * @param optionToSelect The title attribute of the menu option to be selected.
	 */
	public void selectMenuOption(String optionToSelect) {
		Locator options = page.locator(MenuOption);
		int optionCount = options.count();
		System.out.println("Total menu options available: " + optionCount);

		for (int i = 0; i < optionCount; i++) {
			Locator menuOption = options.nth(i);
			String menuText = menuOption.getAttribute("title");

			if (menuText != null && menuText.equalsIgnoreCase(optionToSelect)) {
				menuOption.click();
				System.out.println("'" + optionToSelect + "' option is selected.");
				return;
			}
		}

		System.out.println("Option '" + optionToSelect + "' not found in the menu.");
	}

	/**
	 * 
	 * Verifies whether the Block or Unblock confirmation pop-up is visible
	 * 
	 * and retrieves the confirmation message text.
	 * 
	 * @return The confirmation message text if a pop-up is visible, otherwise
	 *         returns null.
	 */
	public String verifyConfirmationPop_Up() {
		Boolean isDeleteIconVisible = play.isVisible(deletIcon, "Verifying the Delete icon");
		boolean isBlockVisible = play.isVisible(block_Pop_Header, "Verifying the Block user header");
		boolean isUnblockVisible = play.isVisible(unBlock_Pop_Header, "Verifying the Unblock user header");
		boolean isLeaveIconVisible = play.isVisible(leaveGroupHeading, "Verifying the leave pop-up header");

		if (isBlockVisible) {
			String blockHeaderText = play.getText(block_Pop_Header);
			String confirmationMessage = play.getText(confirmation_Pop);
			System.out.println("Block Pop-up Header: " + blockHeaderText);
			return confirmationMessage;

		} else if (isUnblockVisible) {
			String unblockHeaderText = play.getText(unBlock_Pop_Header);
			String confirmationMessage = play.getText(confirmation_Pop);
			System.out.println("Unblock Pop-up Header: " + unblockHeaderText);
			return confirmationMessage;
		} else if (isDeleteIconVisible) {
			String confirmationMessage = play.getText(deletePopMSG);
			String confirmation2nMessage = play.getText(deletePop2ndText);
			System.out.println("Delete Chat Confirmation Pop_up is: " + confirmationMessage);
			System.out.println("Delete Chat 2nd Confirmation Pop_up is: " + confirmation2nMessage);
			return confirmationMessage;

		} else if (isLeaveIconVisible) {
			String confirmationMessage = play.getText(successMessage);
			System.out.println("The leave group confirmation message is: " + confirmationMessage);
			return confirmationMessage;
		}

		System.out.println("No confirmation pop-up is visible.");
		return null;
	}

	/**
	 * Clicks on the "Block" button in the confirmation pop-up.
	 */
	public void clickOnBlockButton() {
		play.click(delet_Pop_Block, "Clicked on block text");
	}

	/**
	 * Clicks on the "Delete" button for the delete chat history confirmation
	 * pop-up.
	 */
	public void clickOnDeletButton() {
		play.click(DeleteBtn, "Clicked on block text");
	}

	public void clickOnLeaveBtn() {
		play.click(leaveBtn, "Successfully left the group");
	}

	public void clickOnGroupName() {
		play.click(getuserName, "Clicked on group profile name");
	}

	/**
	 * Verifies and retrieves the success message displayed after block or unblock
	 * actions. Closes the success pop-up after reading the message.
	 *
	 * @return The success message text if visible, otherwise returns null.
	 */
	public String verifySuccessMessage() {
		page.waitForTimeout(3000);
		boolean isBlockSuccessVisible = play.isVisible(succussMsg,
				"Checking right mark icon on success pop-up message");
		boolean isUnblockSuccessVisible = play.isVisible(unblocksuccessMsg,
				"Checking success pop-up for unblock action");
		boolean isRightIconVisible = play.isVisible(succussMsgIcon, "Checking Right Icon for the user report action");

		if (isBlockSuccessVisible) {
			String blockSuccessText = play.getText(succussMsg);
			System.out.println("Block Success Message: " + blockSuccessText);
			play.click(closePop_UP, "Success pop-up closed");
			return blockSuccessText;

		} else if (isUnblockSuccessVisible) {
			String unblockSuccessText = play.getText(unblocksuccessMsg);
			System.out.println("Unblock Success Message: " + unblockSuccessText);
			play.click(closePop_UP, "Success pop-up closed");
			return unblockSuccessText;

		} else if (isRightIconVisible) {

			String reportSuccessMessage = play.getText(reportSuccessPopup);
			System.out.println("The success message for the : " + reportSuccessMessage);
			play.click(closePop_UP, "Success pop-up closed");
			return reportSuccessMessage;

		} else {
			System.out.println("No success message pop-up is visible.");
			return null;
		}
	}

	public String verifyResumeConversionText() {
		String text = play.getText(conversionText);
		return text;
	}

	/**
	 * Clicks on the "Unblock" button in the confirmation pop-up.
	 */
	public void clickOnUnBlockButton() {
		play.click(delet_Pop_Unblock, "Clicked on unblock text");
	}

	/**
	 * Retrieves the confirmation text shown after a conversation starts (e.g.,
	 * after blocking).
	 *
	 * @return The conversation start confirmation message.
	 */
	public String verifyConversationStartText() {
		String conversationStartMessage = play.getText(blockVerifyText);
		return conversationStartMessage;
	}

	/**
	 * Verifies whether the input field for blocking is visible after the block
	 * action. Logs the result and asserts the expected behavior.
	 *
	 * @return true if the input field is visible; false otherwise.
	 */
	public boolean inputFieldIsVisible() {
		boolean isVisible = play.isVisible(inputField, "Verifying input field for block");

		if (isVisible) {
			System.out.println("Input field is visible after block.");
		} else {
			System.out.println("Input field is not visible after block.");
		}
		return isVisible;
	}

	/**
	 * Performs a mouse hover action on the most recent sent message in the chat.
	 */
	public void mouseOverToMessage() {
		String sentMsgXPath = "//*[@class='MuiBox-root css-jhx1m8']" + "//*[@class='MuiBox-root css-xm69l9']"
				+ "//*[@class='MuiBox-root css-1sg8vbr']//p";

		if (play.isVisible(sentMsgXPath, "Checking if message is visible for mouse hover")) {
			play.mouseHover(sentMsgXPath, "Successfully mouse hovered over the most recent message");
		} else {
			System.out.println("Message not visible for mouse hover.");
		}
	}

	/**
	 * Verifies whether the emoji palette is available after unblocking. Attempts to
	 * select the thumbs-up emoji if visible, otherwise checks for delete icon
	 * visibility.
	 *
	 * @return true if either the emoji or delete icon is visible, false otherwise.
	 */
	public boolean verifyEmojiPalette() {
		page.waitForTimeout(5000);

		boolean isReactionVisible = play.isVisible(ThumbIcon,
				"Verifying if at least one reaction emoji is visible after unblock");

		if (isReactionVisible) {
			play.click(ThumbIcon, "Selected the thumbs up emoji.");
			System.out.println("Selected the thumbs up emoji.");
			return true;
		} else {
			boolean isDeleteIconVisible = play.isVisible(DeleteIcon, "Verifying the delete icon after block");

			if (isDeleteIconVisible) {
				System.out.println("Delete icon is visible. User is unable to react to the message.");
				return true;
			} else {
				System.out.println("Neither reaction emoji nor delete icon is visible.");
				return false;
			}
		}
	}

	/**
	 * Clicks on the Send button in the report dialog.
	 */
	public void clickOnReportSendBtn() {
		play.click(ReportSendBtn, "Clicked on Report Send button.");
	}

	/**
	 * Verifies whether the report option is disabled by navigating through the
	 * hamburger menu.
	 *
	 * @return true if the report option is disabled, false otherwise.
	 */
	public boolean verifyReportEnableStatus() {
		clickOnHamburgerMenu(); // Corrected method name for readability
		return play.isDisabled(ReportStatus, "Verified the report status is disabled.");
	}

	/**
	 * Enters a description into the report input field.
	 *
	 * @param description The text to enter as the report reason/description.
	 */
	public void enterReportDescription(String description) {
		play.sendKeys(reportDescription, description, "Report description is entered.");
	}

	/**
	 * Selects a reason from the report dropdown based on the provided text.
	 *
	 * @param reasonToSelect The reason to be selected from the dropdown.
	 */
	public void selectReasons(String reasonToSelect) {
		play.click(SeletReason, "Clicked on the reason dropdown.");

		Locator reportReasons = page.locator(reasonsfromDropDown);
		int totalReasons = reportReasons.count();
		System.out.println("Total available reasons: " + totalReasons);

		for (int i = 0; i < totalReasons; i++) {
			Locator reason = reportReasons.nth(i);
			String reasonText = reason.textContent().trim();

			if (reasonText.equalsIgnoreCase(reasonToSelect)) {
				page.waitForTimeout(1000); // Optional wait for stability
				reason.click();
				System.out.println("✅ Selected reason: " + reasonToSelect);
				return;
			}
		}

		System.out.println("❌ Reason '" + reasonToSelect + "' not found in the dropdown.");
	}

	/**
	 * Verifies whether the Report Details pop-up screen is visible.
	 *
	 * @return true if the report details pop-up appears, false otherwise.
	 */
	public boolean isReportDetailsPopUpAppear() {
		return play.isVisible(reportDetail, "Verified that the Report Details pop-up screen has appeared.");
	}

	public String generateRandomText(int count) {
//		return play.generateRandomText(count);
		return RandomGenerator.randomString(count);
	}

	/**
	 * Verifies the character limit message displayed for the chat input field. Logs
	 * the message text length and returns the character limit text if visible.
	 *
	 * @return The character limit text if visible, otherwise null.
	 */
	public String verifyChatInputFieldCharacterCount() {

		page.waitForTimeout(2000);

		// Get the text entered in the message input field
		String messageText = play.getText(typeYourMsg);

		// Check if the character limit text is visible
		boolean isVisible = play.isVisible(chracterLimit, "Checking character limit text for chat input field");

		if (isVisible) {
			String characterLimitText = play.getText(chracterLimit);
			System.out.println("============================================");
			System.out.println("✅ Total characters entered: " + messageText.length());
			System.out.println("============================================\n");
			return characterLimitText;
		} else {
			System.out.println("❌ Character limit text is not visible.");
			System.out.println("Current message length: " + messageText.length());
			return null;
		}
	}

}
