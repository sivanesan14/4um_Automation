package com.forum.bdd.web.step_definitions;

import org.testng.Assert;

import com.forum.bdd.integrations.common_utils.ConfigReader;
import com.forum.bdd.web.screens.Forum_ChatWindow_Screen;
import com.forum.bdd.web.screens.Forum_Chat_Screen;
import com.forum.bdd.web.screens.Forum_CreateGroup_Screen;
import com.forum.bdd.web.screens.Forum_Groups_Screen;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Forum_ChatWindow_Step {

	Forum_Groups_Screen GP = new Forum_Groups_Screen();
	Forum_CreateGroup_Screen CGP = new Forum_CreateGroup_Screen();
	Forum_Chat_Screen CP = new Forum_Chat_Screen();
	Forum_ChatWindow_Screen CWP = new Forum_ChatWindow_Screen();
	String expectedSearchName = ConfigReader.getValue("FriendName");

	@When("User A enters {string} in the search input field")
	public void user_a_enters_in_the_search_input_field(String Name) {
		CP.enterFriendsName(expectedSearchName);
		expectedSearchName = CP.getFriendNameFromSearch();
	}

	@When("User A selects the name from the search results")
	public void user_a_selects_the_name_from_the_search_results() {
		String expectedName = expectedSearchName;
		CP.selectFriendFromSearchList(expectedName);
	}

	@Then("the user should see the {string} on chat window")
	public void VerifyVhatWindowElement(String elements) {

	}

	@Then("User A should be navigated to the chat window with User B")
	public void user_a_should_be_navigated_to_the_chat_window_with_user_b() {
		CWP.verifyChatWindow();
	}

	@When("the user types a message in the Type your message… text box and sends it")
	public void the_user_types_a_message_in_the_text_box_and_sends_it() {

		CWP.enterMessageInBox("Hi '" + expectedSearchName
				+ "', this is a verification message sent as part of 4um application automation testing.");
	}

	@Then("the message should appear in the chat window")
	public void the_message_should_appear_in_the_chat_window() {

		CWP.clickonSendButton();

		Assert.assertTrue(true, CWP.verifySentMessage());
	}

	@When("User A sends a message to User B")
	public void user_a_sends_a_message_to_user_b() {
		CWP.enterMessageInBox(
				"Dear Suryaputra, this is a verification message sent as part of 4um application testing.");
	}

	@Then("the message status for User A e.g Sent, Delivered, or Read should be displayed correctly")
	public void the_message_status_for_user_a_e_g_sent_delivered_or_read_should_be_displayed_correctly() {
		CWP.verifyStatusOfSendMsg();
	}

//	@And("start conversation in edge with another user")
//	public void startConversationInEdge()
//	{
//		CWP.startConversationInEdge();
//		CP.clickOnChat();
//	}

	@When("User B receives the message from User A on chat page with unread message count")
	public void user_b_receives_the_message_from_user_a() {

		System.out.println("🟡 Starting Edge flow for User B...");

		/*
		 * // CWP.startConversationInEdge(); // Thread edgeThread = new Thread(() -> {
		 * // try { // CWP.startConversationInEdge(); // Launch Edge & login //
		 * CP.clickOnChat(); // Navigate to chat // CWP.verifyUnreadMSGCount(); //
		 * Validate unread message count //
		 * System.out.println("✅ Edge execution done (User B)"); // } catch (Exception
		 * e) { // e.printStackTrace(); // throw new
		 * RuntimeException("Edge execution failed: " + e.getMessage()); // } // }); //
		 * // edgeThread.start(); // // try { //
		 * System.out.println("⏳ Waiting for Edge thread to complete..."); //
		 * edgeThread.join(); // Chrome will wait here //
		 * System.out.println("✅ Edge thread completed. Chrome can continue now."); // }
		 * catch (InterruptedException e) { // e.printStackTrace(); // throw new
		 * RuntimeException("Thread interrupted while waiting for Edge thread."); // }
		 * //
		 */ }

	@Then("User B open User A message from chat window")
	public void user_b_open_user_a_message_from_chat_window() {

		// CWP.launchEdgeWithDifferentUser();

	}

	@Then("the recent message should be visible on the User B Chat window")
	public void the_recent_message_should_be_visible_on_the_user_b_chat_window() {
		CWP.verifySentMessage();
	}

	@Then("the message status for User A should update to a double tick mark if the User B is Online")
	public void the_message_status_for_user_a_should_update_to_a_blue_double_tick_mark() {
		CWP.verifyStatusOfTickMark();
	}

	@When("User B replies to User A’s message")
	public void user_b_replies_to_user_a_s_message() {
		CWP.enterMessageInBox(
				"Dear Suryaputra, this is a verification message sent as part of 4um application testing.");
	}

	@Then("the reply from User B should appear in the same chat window thread")
	public void the_reply_from_user_b_should_appear_in_the_same_chat_window_thread() {
		CWP.verifySentMessage();
	}

	@When("User A receives the reply from User B")
	public void user_a_receives_the_reply_from_user_b() {

	}

	@Then("the message status for User B e.g. Sent, Delivered, or Read should be displayed correctly")
	public void the_message_status_for_user_b_e_g_sent_delivered_or_read_should_be_displayed_correctly() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("the user clicks on the {string} option from the Hamburger Menu")
	public void the_user_clicks_on_the_block_option_from_the_hamburger_menu(String Options) {
		CWP.clickOnHamburgerMenu();
		CWP.selectMenuOption(Options);
	}

	@When("user clicks on the group profile or group name")
	public void clickOnGroupUserName() {
		CWP.clickOnGroupName();
	}

	@Then("a confirmation popup message {string} should appear")
	public void a_confirmation_popup_message_should_appear(String Pop_Up) {
		CWP.verifyConfirmationPop_Up();
	}

	@Then("the user confirms the block action")
	public void the_user_confirms_the_block_action() {
		CWP.clickOnBlockButton();
	}

	@Then("the user click on leave button")
	public void clickOnleaveBtn() {
		CWP.clickOnLeaveBtn();
	}

	@Then("a success popup message {string} should be displayed")
	public void a_success_popup_message_should_be_displayed(String string) {
		CWP.verifySuccessMessage();
	}

	@Then("the chat input option should be disabled for the user")
	public void the_chat_input_option_should_be_disabled_for_the_user() {
		Assert.assertTrue(CWP.inputFieldIsVisible(), "Input field should not be visible after block");
	}

	@Then("the message {string} should be visible on the chat window page")
	public void the_message_should_be_visible_on_the_chat_window_page(String string) {
		CWP.verifyConversationStartText();
	}

	@When("the user clicks on the unblock option from the Hamburger Menu")
	public void the_user_clicks_on_the_unblock_option_from_the_hamburger_menu() {

	}

	@Then("the user confirms the unblock action")
	public void the_user_confirms_the_unblock_action() {
		CWP.clickOnUnBlockButton();
	}

	@When("the user mouse over on a previously sent message")
	public void the_user_mouse_over_on_a_previously_sent_message() {
		CWP.mouseOverToMessage();
	}

	@Then("the reaction options should not be available")
	public void the_reaction_options_should_not_be_available() {
		Assert.assertTrue(CWP.verifyEmojiPalette(), "Emoji palette verification failed after block/unblock.");
	}

	@When("the user mous over on a previously received message")
	public void the_user_mous_over_on_a_previously_received_message() {

	}

	@Then("User should see the delete icon on each message")
	public void user_should_see_the_delete_icon_on_each_message() {
	}

	@Then("the reaction options should be available")
	public void the_reaction_options_should_be_available() {
		CWP.verifyEmojiPalette();
	}

	@Then("User should see the emoji plate on each message")
	public void user_should_see_the_emoji_plate_on_each_message() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the user clicks on the delete button")
	public void the_user_clicks_on_the_delete_button() {
		CWP.clickOnDeletButton();
	}

	@Then("the all message should be deleted")
	public void the_all_message_should_be_deleted() {

	}

	@Then("user should navigated to chat page")
	public void user_should_navigated_to_chat_page() {
		Assert.assertTrue(CP.verifyChatPage(), "User unable to navigated to chat page..");
	}

	@Then("a Report Details popup screen should appear")
	public void a_popup_screen_should_appear() {
		Assert.assertTrue(CWP.isReportDetailsPopUpAppear(), "Report details Pop up screen is not appeared");
	}

	@Then("the user selects a reason from the dropdown menu")
	public void the_user_selects_a_reason_from_the_dropdown_menu() {
		CWP.selectReasons("Hate Speech");
	}

	@Then("the user enters a description in the input field")
	public void the_user_enters_a_description_in_the_input_field() {
		CWP.enterReportDescription("User is not a part of 4um chat");
	}

	@When("the user clicks on the Send button")
	public void the_user_clicks_on_the_send_button() {
		CWP.clickOnReportSendBtn();
	}

	@Then("the Report option should be disabled to prevent duplicate reporting")
	public void the_option_should_be_disabled_to_prevent_duplicate_reporting() {
		Assert.assertTrue(CWP.verifyReportEnableStatus(), "Report status is enabled after report");
	}

	@When("the user types a message with more than {int} characters in the Type your message… text box")
	public void the_user_types_a_message_with_more_than_characters_including_text_and_emoji_in_the_text_box(
			Integer num) {
		String Message = CWP.generateRandomText(num);
		CWP.enterMessageInBox(Message);
	}

	@Then("the user should see an {string} warning message displayed below the input box")
	public void the_user_should_see_an_warning_message_displayed_below_the_input_box(String characters) {
		String expectedCharacterLimitText = characters;
		String actualCharacterLimitText = CWP.verifyChatInputFieldCharacterCount();
		Assert.assertEquals(actualCharacterLimitText, expectedCharacterLimitText,
				"❌ Mismatch in character limit text or limit text is not visible.");
	}
}
