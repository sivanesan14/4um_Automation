package com.forum.bdd.web.step_definitions;

import java.awt.AWTException;

import org.testng.Assert;

import com.forum.bdd.web.screens.Forum_Block_Screen;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Forum_Block_Steps {

	Forum_Block_Screen BlockUser = new Forum_Block_Screen();

	@And("user enters another username {string} on search box on web")
	public void user_enters_another_username_on_search_box_on_web(String Username) {
		BlockUser.enterTextonSearchBox(Username);
	}

	@And("user clicks on another username from search results on web")
	public void user_clicks_on_another_username_from_search_results_on_web() {
		BlockUser.selectSearchedUser();
	}

	@Then("verify user navigates to another user profile on web")
	public void verify_user_navigates_to_another_user_profile_on_web() {
		BlockUser.verifyOtherUserProfile();
	}

	@When("user clicks on post tab of user profile on web")
	public void user_clicks_on_post_tab_of_user_profile_on_web() {
		BlockUser.clickPostTab();
	}

	@And("user click on user post hamburger menu on web")
	public void user_click_on_user_post_hamburger_menu_on_web() {
		BlockUser.clickPostHamburgerMenu();
	}

	@And("user click on block options on web")
	public void user_click_on_block_options_on_web() {
		BlockUser.clickBlockOption();
	}

	@And("user click on block confirmation button on web")
	public void user_click_on_block_confirmation_button_on_web() {
		BlockUser.verifyBlockPopup();
		BlockUser.clickBlockConfirmButton();
	}

	@Then("user verify the user gets {string} blocked popup on web")
	public void user_verify_the_user_gets_blocked_popup_on_web(String value) {
		BlockUser.verifyUserBlockedSuccessPopup(value);
	}

	@And("user confirm another user blocked successfully")
	public void user_confirm_another_user_blocked_successfully() {
		BlockUser.clickCloseButton();
		Assert.assertTrue(BlockUser.verifyUserBlockedSuccessfully(), "Failed: The User is not blocked");
	}

	@And("the user enters the user mention {string} on web")
	public void the_user_enters_the_user_mention_on_web(String User) throws AWTException {
		BlockUser.enterUserMention(User);
	}

	@Then("verify that blocked user cannot mentioned in post")
	public void verify_that_blocked_user_cannot_mentioned_in_post() {
		Assert.assertTrue(BlockUser.verifyUserNotFoundMessage(), "Failed: The User is not blocked");
	}

	@Then("verify user cannot see any blocked user post")
	public void verify_user_cannot_see_any_blocked_user_post() {
		Assert.assertTrue(BlockUser.verifyUnblockButton(), "Failed: The User is not blocked");
		Assert.assertTrue(BlockUser.verifyNotPostMessage(), "Failed: The User is not blocked");
	}

	@When("user clicks on Friend list option on web")
	public void user_clicks_on_friend_list_option_on_web() {
		BlockUser.clickFriendList();
	}

	@Then("verify the friend list details on web")
	public void verify_the_friend_list_details_on_web() {
		BlockUser.verifyFriendTab();
	}

	@When("user clicks on unblock button on web")
	public void user_clicks_on_unblock_button_on_web() {
		BlockUser.clickUnblockBtn();
	}

	@And("user click on unblock confirmation button on web")
	public void user_click_on_unblock_confirmation_button_on_web() {
		BlockUser.verifyUnblockPopup();
		BlockUser.clickUnblockConfirmButton();
	}

	@Then("user verify the user gets {string} unblocked popup on web")
	public void user_verify_the_user_gets_unblocked_popup_on_web(String value) {
		BlockUser.verifyUserUnblockedSuccessPopup(value);
		BlockUser.clickCloseButton();
	}

	@And("verify user can see user post on web")
	public void verify_user_can_see_user_post_on_web() {
		BlockUser.verifyPostPage();
	}

	@Then("user can see the Unfriend button on web")
	public void user_can_see_the_Unfriend_button_on_web() {
		Assert.assertTrue(BlockUser.verifyUnfriendButton(), "Failed: The User is not Unblocked");
	}
}
