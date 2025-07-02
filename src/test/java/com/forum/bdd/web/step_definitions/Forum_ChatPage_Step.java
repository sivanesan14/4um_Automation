package com.forum.bdd.web.step_definitions;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.forum.bdd.web.screens.Forum_ChatWindow_Screen;
import com.forum.bdd.web.screens.Forum_Chat_Screen;
import com.forum.bdd.web.screens.Forum_CreateGroup_Screen;
import com.forum.bdd.web.screens.Forum_Groups_Screen;
import com.forum.bdd.web.screens.Forum_Home_Screen;
import com.forum.bdd.web.screens.Forum_Login_Screen;
import com.forum.bdd.web.screens.Forum_UserProfileDetails_Screen;
import com.forum.bdd.web.screens.Forum_CreateMessage_Screen;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Forum_ChatPage_Step {

	Forum_Login_Screen LP = new Forum_Login_Screen();
	Forum_Home_Screen HP = new Forum_Home_Screen();
	Forum_Groups_Screen GP = new Forum_Groups_Screen();
	Forum_CreateGroup_Screen CGP = new Forum_CreateGroup_Screen();
	Forum_Chat_Screen CP = new Forum_Chat_Screen();
	Forum_ChatWindow_Screen CWP = new Forum_ChatWindow_Screen();
	Forum_UserProfileDetails_Screen UPP = new Forum_UserProfileDetails_Screen();
	Forum_CreateMessage_Screen CMP = new Forum_CreateMessage_Screen();
	String expectedName;
	String expectedName2;

	@Given("the user navigates to the Chat Page")
	public void the_user_navigates_to_the_chat_page() {
		HP.clickOnChat();
	}

	@Then("the user should see the chat feature enabled on the right-side menu")
	public void the_user_should_see_the_chat_feature_enabled_on_the_right_side_menu() {
		Assert.assertTrue(HP.isChatFeatureEnabled(), "Chat feature is Not Enabled on Left side menu");
	}

	@When("the user clicks on the chat feature")
	public void the_user_clicks_on_the_chat_feature() {
		HP.clickOnChat();
	}

	@Then("the user should be navigated to the chat page")
	public void the_user_should_be_navigated_to_the_chat_page() {
		Assert.assertTrue(CP.verifyChatPage(), "User Unable to navigate to Chat Page");
	}

	@Given("the user is on the chat page")
	public void the_user_is_on_the_chat_page() {
		Assert.assertTrue(CP.verifyChatPage(), "User Unable to navigate to Chat Page");
	}

	@Then("the user should see the {string} on the chat page")
	public void the_user_should_see_the_on_the_chat_page(String elements) {
		CP.verifyChatUIElements(elements);
	}

	@When("the user enters a friend's name in the search input field")
	public void the_user_enters_a_friend_s_name_in_the_search_input_field() {
		CP.enterFriendsName("Suryaputra");
	}

	@Then("the that friend name should be displayed in the search results")
	public void the_friend_s_name_should_be_displayed_in_the_search_results() {
		String expectedName = "Suryaputra";
		// String actalName = CP.getSearchResult();
		Assert.assertTrue(CP.getSearchResult(),
				"Searched group name '" + expectedName + "' is not found in the search list.");
	}

	@Then("the that name should be displayed in the search results")
	public void searchFriendName() {
		String expectedName2 = expectedName;
		// String actalName = CP.getSearchResult();
		Assert.assertTrue(CP.getSearchResult(),
				"Searched name '" + expectedName2 + "' is not found in the search list.");
	}

	@When("the user selects the friend's name from the search results")
	public void the_user_selects_the_friend_s_name_from_the_search_results() {
		CWP.verifyChatWindow();
	}

	@Then("the user should be navigated to the chat window with that friend")
	public void the_user_should_be_navigated_to_the_chat_window_with_that_friend() {
		String actalName = expectedName;
		Assert.assertEquals(expectedName, actalName,
				"Searched friend name' " + actalName + " 'is not displayed on chat window");
	}

	@Then("the user should be navigated to the chat window with selected Friend")
	public void the_user_should_be_navigated_to_the_chat_window_with_selected_Friend() {
		String actalName = expectedName;
		Assert.assertEquals(expectedName, actalName,
				"Searched friend name' " + actalName + " 'is not displayed on chat window");
	}
	
	@Then("the message status e.g Sent, Delivered, or Read should be displayed correctly")
	public void the_message_status_e_g_sent_delivered_or_read_should_be_displayed_correctly() {
		CWP.verifyStatusOfSendMsg();
	}

	@When("the user enters a name in the search input field")
	public void the_user_enters_a_name_in_the_search_input_field() {
		CP.enterName("Kiran prakash khawasi()");
	}

	@Then("the user's name should be displayed in the search results")
	public void the_user_s_name_should_be_displayed_in_the_search_results() {

	}

	@When("the user selects the name from the search results")
	public void the_user_selects_the_name_from_the_search_results() {
		expectedName2 = CP.getFriendNameFromSearch();
		String expectedName = expectedName2;
		CP.selectFriendFromSearchList(expectedName);
	}

	@Given("the user selects any friend from the Friends_tab")
	public void user_select_the_any_friend_form_the_frinds_tab() {

		expectedName = CP.getFriendName();
		CP.selectAnyVisibleFriendFromFriendTab("Suryaputra");
	}

	@Given("the user clicks on the Create Message icon")
	public void the_user_clicks_on_the_create_message_icon() {
		CP.clickCreateMsgIcon();
	}

	@Then("the user should see the {string} on the Create Message pop-up")
	public void the_user_should_see_the_elements_on_the_create_message_pop_up(String elements) {
		CMP.verifyCreateMessageUIElements(elements);
	}

	@When("the user enters the friend's name in the To input field section")
	public void the_user_enters_the_friend_s_name_in_the_input_field_section() {
		CMP.enterFriendsNamesInToFields(
				Arrays.asList("Rahul KL", "Radhey", "Kiran khavashi", "Siva User Test", "Margaret Mills", "Virat_iBr"));
	}

	@When("the user enters the more then 7 members in the To input field section")
	public void verifyErrorMessage() {
		CMP.enterFriendsNamesInToFields(Arrays.asList("Rahul KL", "Radhey", "Kiran khavashi", "Siva User Test",
				"Margaret Mills", "Virat_iBr", "Karna"));
	}

	@Then("upon selecting the friend's name, it should be displayed in the To input field tab")
	public void upon_selecting_the_friend_s_name_it_should_be_displayed_in_the_input_field_tab() {
//		CMP.selectFriendFromToTab();
	}

	@When("the user enters the text message in the {string} box")
	public void the_user_enters_the_text_message_in_the_box(String string) {
		CMP.enterMSGForGroupChat();
	}

	@When("the user clicks on the Create button")
	public void the_user_clicks_on_the_create_button() {
		CMP.clickOnSendBtn();
	}

	@Then("the user should be navigated to the chat window with the group members")
	public void the_user_should_be_navigated_to_the_chat_window_with_the_group_members() {
		List<String> ActualDate = CMP.verifyGroupMembers();
	}

	@Then("the user should see a warning message stating {string}")
	public void verifyErrorMessage(String msg) {
		String actualText = CMP.verifyAddMoreMemberErrorMsg();
		String ExpectedText = "You already added 7 members to the conversation. Adding more members is not allowed.";
		Assert.assertEquals(actualText, ExpectedText,
				"The expected text " + ExpectedText + " is Not matched with:" + actualText);
	}

}
