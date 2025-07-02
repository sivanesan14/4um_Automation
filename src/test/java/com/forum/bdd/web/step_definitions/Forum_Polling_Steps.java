package com.forum.bdd.web.step_definitions;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.forum.bdd.ccl.WebActions;
import com.forum.bdd.integrations.report_utils.ReportManager;
import com.forum.bdd.web.screens.Forum_Login_Screen;
import com.forum.bdd.web.screens.Forum_Polling_Screen;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Forum_Polling_Steps {
	Forum_Login_Screen OnlineLoginWP = new Forum_Login_Screen();
	Forum_Polling_Screen poll = new Forum_Polling_Screen();
	WebActions play = new WebActions();
	String question, group_Name, postID;
	String description = "Automation Testing";
	String choice_1 = "yes";
	String choice_2 = "no";
	String choice_3 = "May be";
	String choice_4 = "Not sure";
	String choice_5 = "Will see";
	String category = "Automotive";
	String duration = "2";

	@When("the user logs in with valid credentials on the web application")
	public void the_user_logs_in_with_valid_credentials_on_the_web_application() throws InterruptedException {
		OnlineLoginWP.enterURL();
		OnlineLoginWP.enterEmailID();
		OnlineLoginWP.enterPassword();
		OnlineLoginWP.clickNextButton();
//		OnlineLoginWP.verifyHomePage();
	}

	@When("the user clicks on the Create Poll option in the main menu")
	public void the_user_clicks_on_the_create_poll_option_in_the_main_menu() {
		poll.click_PollMenu();
	}

	@Then("the user should be navigated to the Create Poll page")
	public void the_user_should_be_navigated_to_the_create_poll_page() {
		poll.verifyTitleInPollPage();
		Assert.assertTrue(poll.verifTheCurrentUrl("createPoll"));
	}

	@When("the user enters a valid question {string}")
	public void the_user_enters_a_valid_question(String value) {
		poll.enterQuestion(value);
	}

	@When("the user enters a valid first choice {string}")
	public void the_user_enters_a_valid_first_choice(String value) {
		poll.enter_Choice_1(value);
	}

	@When("the user enters a valid second choice {string}")
	public void the_user_enters_a_valid_second_choice(String value) {
		poll.enter_Choice_2(value);
	}

	@When("the user clicks the Add Option button")
	public void the_user_clicks_the_add_option_button() {
		poll.add_Pollchoice();
	}

	@When("the user enters a valid third choice {string}")
	public void the_user_enters_a_valid_third_choice(String value) {
		poll.enter_Choice_3(value);
	}

	@When("the user enters a valid fourth choice {string}")
	public void the_user_enters_a_valid_fourth_choice(String value) {
		poll.enter_Choice_4(value);
	}

	@When("the user enters a valid fifth choice {string}")
	public void the_user_enters_a_valid_fifth_choice(String value) {
		poll.enter_Choice_5(value);
	}

	@When("the user clicks the Add Description button")
	public void the_user_clicks_the_add_description_button() {
		poll.clickAddDescription();
	}

	@When("the user enters a valid Description {string}")
	public void the_user_enters_a_valid_description(String value) {
		poll.enter_Description(value);
	}

	@When("the user clicks on the groups in the Share drop down")
	public void the_user_clicks_on_the_groups_in_the_share_drop_down() {
		poll.open_ShareDropdown();
	}

	@When("the user selects the group {string} from the search result")
	public void the_user_selects_the_group_from_the_search_result(String groupName) {
		poll.click_GroupsOption();
		String nameInDropDown = poll.selectGroup(groupName);
		Assert.assertTrue(nameInDropDown.equalsIgnoreCase(groupName),"Selected Group Name is not matching in Dropdown");
	}

	@When("the user selects poll duration {string} days from the dropdown")
	public void the_user_selects_poll_duration_days_from_the_dropdown(String value) {
		poll.select_PollDuration(value);
	}

	@When("the user selects poll Category {string} from the dropdown")
	public void the_user_selects_poll_category_from_the_dropdown(String value) {
		poll.selectPollCategory(value);
	}

	@When("the user clicks the Create button")
	public void the_user_clicks_the_create_button() {
		poll.click_CreatePoll();
	}

	@Then("a success message confirming poll creation should be displayed")
	public void a_success_message_confirming_poll_creation_should_be_displayed() throws InterruptedException {
		boolean successMessage = poll.successMessage();
		Assert.assertTrue(successMessage, "Success message not visible");
		poll.verifyHomePageAfterPollCreation();
	}

	@When("the user enters an invalid question for the scenario {string}")
	public void the_user_enters_an_invalid_question_for_the_scenario(String scenario) {
		poll.enter_InvalidQuestion(scenario);
	}

	@Then("the user should see an error message {string} below the Question field")
	public void the_user_should_see_an_error_message_below_the_question_field(String value) {
		Assert.assertTrue(poll.errorValidation_Question(value));
	}

	@Then("the poll should not be created")
	public void the_poll_should_not_be_created() {
		Assert.assertFalse(poll.pollNotCreated(), "Poll Created with Invalid Inputs");
	}

	@When("the user enters an invalid input in choice {int} for the {string}")
	public void the_user_enters_an_invalid_input_in_choice_for_the(Integer index, String scenario) {
		poll.enter_InvalidChoices(scenario, index);
	}

	@Then("the user should see an error message {string} below the Choice field")
	public void the_user_should_see_an_error_message_below_the_choice_field(String message) {
		Assert.assertTrue(poll.errorValidation_Choices(message),
				"Failed: Error message for Invalid choice is not matching");
	}

	@When("the user enter the invalid input in Description for the {string}")
	public void the_user_enter_the_invalid_input_in_description_for_the(String scenario) {
		poll.enter_InvalidDescription(scenario);
	}

	@Then("the user should see an error message {string} below the Description field")
	public void the_user_should_see_an_error_message_below_the_description_field(String value) {
		Assert.assertTrue(poll.errorValidation_Description(value));
	}

	@When("the user creates a poll in a group {string}  successfully")
	public void the_user_creates_a_poll_in_a_group_successfully(String group) {
		group_Name = group;
		String pollQuestion = " is the poll created time";
		// retrieve the value from the Question text box and stored to variable
		question = poll.enterQuestion(pollQuestion);
		for (int i = 0; i <3; i++) {
			poll.add_Pollchoice();
		}
		poll.enter_Choice_1(choice_1);
		poll.enter_Choice_2(choice_2);
		poll.enter_Choice_3(choice_3);
		poll.enter_Choice_4(choice_4);
		poll.enter_Choice_5(choice_5);
		poll.clickAddDescription();
		poll.enter_Description(description);
		poll.open_ShareDropdown();
		poll.click_GroupsOption();
		String nameInDropDown = poll.selectGroup(group_Name);
		Assert.assertTrue(nameInDropDown.equalsIgnoreCase(group_Name),"Selected Group Name is not matching in Dropdown");
		poll.select_PollDuration(duration);
		poll.selectPollCategory(category);
		poll.click_CreatePoll();

		boolean successMessage = poll.successMessage();
		Assert.assertTrue(successMessage, "Success message not visible after poll creation");
	}

	@Then("the user navigates to Home page")
	public void the_user_navigates_to_Home_page() {
		if (!poll.verifTheCurrentUrl("home")) {
			poll.click_HomeMenu();
		}
	}

	@Then("verify the user can find that poll")
	public void verify_the_user_can_find_that_poll() {
		boolean findCreatedPoll = poll.findCreatedPoll(question);
		Assert.assertTrue(findCreatedPoll, "Failed: Unable to find the created poll");
		postID = poll.getThePollID(question);
	}

	@Then("check the details in the poll")
	public void check_the_details_in_the_poll() {
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(poll.getQuestion_InCreatedPoll(postID).contains(question),"Failed: Question is not matching in the created Poll");
		soft.assertTrue(poll.getDescription_InCreatedPoll(postID).contains(description),"Failed: Description is not matching in the created Poll");
		String pollChoices_InCreatedPoll = poll.getPollChoices_InCreatedPoll(postID);
		soft.assertTrue(pollChoices_InCreatedPoll.contains(choice_1),"Failed: choice_1 is not matching in the created Poll");
		soft.assertTrue(pollChoices_InCreatedPoll.contains(choice_1),"Failed: choice_1 is not matching in the created Poll");
		soft.assertTrue(pollChoices_InCreatedPoll.contains(choice_3),"Failed: choice_3 is not matching in the created Poll");
		soft.assertTrue(pollChoices_InCreatedPoll.contains(choice_4),"Failed: choice_4 is not matching in the created Poll");
		soft.assertTrue(pollChoices_InCreatedPoll.contains(choice_5),"Failed: choice_5 is not matching in the created Poll");
		soft.assertAll();
		ReportManager.logInfo("Success: Question,Description and choices in the Created Poll are displaying correctly");
	}

	@Then("the user navigates to that group details page")
	public void the_user_navigates_to_that_group_details_page() {
      poll.navigateToGroup_ByGeneral_SearchBar(group_Name);
	}

	@Then("the user navigates to his user profile page")
	public void the_user_navigates_to_his_user_profile_page() {
      poll.navigateToLoginUserProfile();
      poll.navigateTo_PostTab_InUserProfile();
	}
	@When("the user creates a poll to Everyone successfully")
	public void the_user_creates_a_poll_to_everyone_successfully() {
		String pollQuestion = " is the poll created time";
		// retrieve the value from the Question text box and stored to variable
		question = poll.enterQuestion(pollQuestion);
		for (int i = 0; i <3; i++) {
			poll.add_Pollchoice();
		}
		poll.enter_Choice_1(choice_1);
		poll.enter_Choice_2(choice_2);
		poll.enter_Choice_3(choice_3);
		poll.enter_Choice_4(choice_4);
		poll.enter_Choice_5(choice_5);
		poll.clickAddDescription();
		poll.enter_Description(description);
		poll.select_PollDuration(duration);
		poll.selectPollCategory(category);
		poll.click_CreatePoll();

		boolean successMessage = poll.successMessage();
		Assert.assertTrue(successMessage, "Success message not visible after poll creation");
	}
	@Then("The newly created poll should not be visible on the author's home page")
	public void the_newly_created_poll_should_not_be_visible_on_the_author_s_home_page() {
		int theCountOfLocators = poll.verifytheCreatedPollIsExist(question);          
		Assert.assertEquals(theCountOfLocators,0, "Failed: Poll Created for everyone is visible in Author's Home page");
		ReportManager.logInfo("Success: Poll created with audience set to 'Everyone' is not appeared on the home page for the author");
	}

}