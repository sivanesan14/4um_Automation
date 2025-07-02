package com.forum.bdd.web.step_definitions;

import java.awt.AWTException;

import org.testng.Assert;

import com.forum.bdd.web.screens.Forum_CreatePost_Screen;
import com.forum.bdd.web.screens.Forum_Login_Screen;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Forum_CreatePost_Steps {

	Forum_Login_Screen OnlineLogin = new Forum_Login_Screen();
	Forum_CreatePost_Screen CreatePost = new Forum_CreatePost_Screen();

	@When("user login with validate details on web")
	public void user_login_with_on_web() throws InterruptedException {
		OnlineLogin.enterURL();
		OnlineLogin.enterEmailID();
		OnlineLogin.enterPassword();
		OnlineLogin.clickNextButton();
		OnlineLogin.verifyHomePage();
	}
	
	@When("user login with another user validate details on web")
	public void user_login_with_another_on_web() throws InterruptedException {
		OnlineLogin.enterURL();
		OnlineLogin.enterAnotherUserEmailID();
//		OnlineLogin.enterAnotherUserPassword();
		OnlineLogin.enterPassword();
		OnlineLogin.clickNextButton();
		OnlineLogin.verifyHomePage();
	}

	@When("the user click on the create post options on web")
	public void the_user_click_on_the_create_post_options_on_web() {
		CreatePost.clickCreatePostOptions();
	}

	@Then("the user should be navigated to the Create Post page")
	public void the_user_should_be_navigated_to_the_create_post_page() {
		CreatePost.verifyTitleInPostPage();
		Assert.assertTrue(CreatePost.verifTheCurrentUrl("createPost"));
	}

	@When("the user enters the post title on web")
	public void the_user_enters_the_post_title_on_web() {
		CreatePost.enterPostTitle();
	}

	@When("the user enters a valid question \"([^\"]*)\" on web$")
	public void the_user_enters_a_valid_question(String value) {
		CreatePost.enterPostDescription(value);
	}

	@And("the user enters the post title \"([^\"]*)\" on web$")
	public void the_user_enters_the_post_title_on_web(String Title) {
		CreatePost.enterPostTitle(Title);
	}

	@And("the user enters the post title more than limit on web")
	public void the_user_enters_the_post_title_more_than_limit_on_web() {
		CreatePost.enterMorePostTitle();
	}

	@And("the user enters the post description on web")
	public void the_user_enters_the_post_description_on_web() {
		CreatePost.enterDescription();
	}

	@And("the user enters the post description with user mention on web")
	public void the_user_enters_the_post_description_with_user_mention_on_web() throws AWTException {
		CreatePost.enterDescriptionWithUser();
	}

	@And("the user enters the post description more than limit on web")
	public void the_user_enters_the_post_description_more_on_web() {
		CreatePost.enterMoreDescription();
	}

	@When("the user enters the group name for post creation on web")
	public void the_user_enters_the_group_name_for_post_creation_on_web() throws AWTException {
		CreatePost.enterGroupName();
	}

	@And("the user enters the post description \"([^\"]*)\" on web$")
	public void the_user_enters_the_post_description_on_web(String Description) {
		CreatePost.enterPostDescription(Description);
	}

	@When("the user enters the invalid post description on web {string}")
	public void the_user_enters_the_invalid_post_description_on_web(String scenario) {
		CreatePost.enterInvalidDescription(scenario);
	}

	@And("the user upload the media files for post creation on web")
	public void the_user_upload_the_media_files_for_post_creation_on_web() {
		CreatePost.uploadMediafile();
	}

	@And("the user upload the more media files for post creation on web")
	public void the_user_upload_the_more_media_files_for_post_creation_on_web() {
		CreatePost.selectMultipleMediafiles();
	}

	@And("the user click on the Create post button on web")
	public void the_user_click_on_the_create_post_button_on_web() {
		CreatePost.clickCreatePostButton();
	}

	@Then("the user verify the post created successfully on web")
	public void the_user_verify_the_post_created_successfully_on_web() {
		boolean successMessage = CreatePost.successMessage();
		Assert.assertTrue(successMessage, "Success message not visible");
		CreatePost.verifyHomePageAfterPostCreation();
	}

	@Then("the user verify no description error message \"([^\"]*)\" for validation$")
	public void the_user_verify_no_description_error_message_for_validation(String ErrorMessage) {
		CreatePost.verifyNoDescErrorMessage(ErrorMessage);
	}

	@Then("the user verify more media file error message \"([^\"]*)\" for validation$")
	public void the_user_verify_more_media_file_error_message_for_validation(String ErrorMessage) {
		CreatePost.verifyMediafileErrorMessage(ErrorMessage);
	}

	@Then("the user verify more description error message for validation")
	public void the_user_verify_more_description_error_message_for_validation() {
		CreatePost.verifyMoreDescriptionErrorMessage();
	}

	@Then("the user verify more Title error message for validation")
	public void the_user_verify_more_title_error_message_for_validation() {
		CreatePost.verifyMoreTitleErrorMessage();
	}

	@Then("the post should not created")
	public void the_post_should_not_created() {
		Assert.assertFalse(CreatePost.postNotCreated(), "Post Created with Invalid Inputs");
	}

	@And("user navigate to user profile page on web")
	public void user_navigate_to_user_profile_page_on_web() {
		CreatePost.clickUserProfileDetails();
	}
		
	@And("user click on user {string} post hamburger menu on web")
	public void user_click_on_post_hamburger_menu_on_web(String username) {
		CreatePost.clickPostHamburgerMenu(username);
	}

	@And("user click on the delete post option on web")
	public void user_click_on_the_delete_post_option_on_web() {
		CreatePost.clickPostDeleteOptions();
	}

	@And("user click on delete post confirmation button on web")
	public void user_click_on_delete_post_confirmation_button_on_web() {
		CreatePost.verifyPostDeleteConfirmationPopup();
		CreatePost.clickPostDeleteConfirmButton();
	}

	@Then("user verify the post deleted successfully on web")
	public void user_verify_the_post_deleted_successfully_on_web() {
		CreatePost.verifyPostDeleteSucessfully();
	}
}
