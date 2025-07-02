package com.forum.bdd.web.step_definitions;

import com.forum.bdd.web.screens.Forum_UserProfileDetails_Screen;

import io.cucumber.java.en.*;

public class Forum_UserProfileDetails_Steps {
//	Forum_LoginWP OnlineLoginWP = new Forum_LoginWP();
	Forum_UserProfileDetails_Screen userProfileDetails = new Forum_UserProfileDetails_Screen();

	@When("the user clicks on the profile name")
	public void the_user_clicks_on_the_profile_name() {
		userProfileDetails.clickProfileName();
	}

	@Then("the user should be navigated to the user profile page")
	public void the_user_should_be_navigated_to_the_user_profile_page() {
		userProfileDetails.verifyUserProfilePage();
	}

	@When("the user clicks on the user edit button")
	public void the_user_clicks_on_the_user_edit_button() {
		userProfileDetails.clickEditButton();
	}

	@And("verify the user sees the edit summary page")
	public void verify_the_user_sees_the_edit_summary_page() {
		userProfileDetails.verifyEditSummaryPage();
	}

	@Then("the user enters the User name {string} on web")
	public void the_user_enters_the_user_name_on_web(String name) {
		userProfileDetails.enterUserName(name);
	}

	@Then("the user enters the User designation {string} on web")
	public void the_user_enters_the_user_designation_on_web(String designation) {
		userProfileDetails.enterUserDesignation(designation);
	}

	@Then("the user enters the User address {string} on web")
	public void the_user_enters_the_user_address_on_web(String address) {
		userProfileDetails.enterUserAddress(address);
	}

	@Then("the user enters the User web address {string} on web")
	public void the_user_enters_the_user_web_address_on_web(String webAddress) {
		userProfileDetails.enterUserWebAddress(webAddress);
	}

	@Then("the user clicks on the save button")
	public void the_user_clicks_on_the_save_button() {
		userProfileDetails.clickSaveButton();
	}

	@When("clears the name field")
	public void clears_the_name_field() {
		userProfileDetails.clears_the_name_field();
	}

	@Then("save button should be disabled")
	public void save_button_should_be_disabled() {
		userProfileDetails.save_button_should_be_disabled();
	}

	@Then("the user enters the new name {string} on web")
	public void the_user_enters_the_new_name_on_web(String name) {
		userProfileDetails.the_user_enters_the_new_name_on_web(name);
	}

	@Then("the user clicks on Cancel button")
	public void the_user_clicks_on_cancel_button() {
		userProfileDetails.clicks_the_cancel_button();
	}

	@Then("the previously saved name should remain unchanged")
	public void the_previously_saved_name_should_remain_unchanged() {
		userProfileDetails.the_previously_saved_name_should_remain_unchanged();
	}

	@When("the user enters more than allowed characters in the name field")
	public void the_user_enters_more_than_allowed_characters_in_the_name_field() throws InterruptedException {
		userProfileDetails.the_user_enters_more_than_allowed_characters_in_the_name_field();
	}

	@Then("an error message should be displayed for name field stating {string}")
	public void an_error_message_should_be_displayed_for_name_field_stating(String name) {
		userProfileDetails.an_error_message_should_be_displayed_for_name_field_stating(name);
	}

	@When("the user enters more than allowed characters in the designation field")
	public void the_user_enters_more_than_allowed_characters_in_the_designation_field() {
		userProfileDetails.the_user_enters_more_than_allowed_characters_in_the_designation_field();
	}

	@Then("an error message should be displayed for designation field stating {string}")
	public void an_error_message_should_be_displayed_for_designation_field_stating(String designation) {
		userProfileDetails.an_error_message_should_be_displayed_for_designation_field_stating(designation);
	}

	@When("the user enters more than allowed characters in the address field")
	public void the_user_enters_more_than_allowed_characters_in_the_address_field() {
		userProfileDetails.the_user_enters_more_than_allowed_characters_in_the_address_field();
	}

	@Then("an error message should be displayed for address field stating {string}")
	public void an_error_message_should_be_displayed_for_address_field_stating(String string) {
		userProfileDetails.an_error_message_should_be_displayed_for_address_field_stating(string);
	}

}
