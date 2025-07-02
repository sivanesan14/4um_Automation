package com.forum.bdd.web.step_definitions;

import org.testng.Assert;

import com.forum.bdd.web.screens.Forum_AddFriends_Screen;
import com.forum.bdd.web.screens.Forum_Block_Screen;

import io.cucumber.java.en.*;

public class Forum_AddFriend_Steps {

	Forum_AddFriends_Screen AddFriend = new Forum_AddFriends_Screen();
	Forum_Block_Screen BlockUser = new Forum_Block_Screen();

	@When("user click on unfriend button on web")
	public void user_click_on_unfriend_button_on_web() {
		AddFriend.clickUnfriendButton();
	}

	@And("user clicks on unfriend confirmation button on web")
	public void user_clicks_on_unfriend_confirmation_button_on_web() {
		AddFriend.verifyUnfriendPopup();
		AddFriend.clickOnConfirmUnfriendBtn();
	}

	@And("user verify the user gets {string} unfriend popup on web")
	public void user_verify_the_user_gets_unfriend_popup_on_web(String value) {
		Assert.assertTrue(AddFriend.verifyUserUnfriendSuccessPopup(value), "Failed: The User is not Unfirend");
		BlockUser.clickCloseButton();
	}

	@And("user verify another user is unfriend successfully")
	public void user_verify_another_user_is_unfriend_successfully() {
		AddFriend.verifyUserUnfriendSuccessfully();
	}

	@When("user clicks on the Addfriend button on web")
	public void user_clicks_on_the_addfriend_button_on_web() {
		AddFriend.clickAddFriendBtn();
	}

	@And("user verify the user gets {string} friend request popup on web")
	public void user_verify_the_user_gets_friend_request_popup_on_web(String value) {
		Assert.assertTrue(AddFriend.verifyUserFriendSuccessPopup(value), "Failed: The User not send friend request");
		BlockUser.clickCloseButton();
	}

	@When("user navigate to notification option on web")
	public void user_navigate_to_notification_option_on_web() {
		AddFriend.clickNotificationMenu();
	}

	@When("user clicks on friend request tab on web")
	public void user_clicks_on_friend_request_tab_on_web() {
		AddFriend.clickFriendRequestTab();
	}

	@When("user click on Accept friend request button on web")
	public void user_click_on_accept_friend_request_button_on_web() {
		AddFriend.clickAcceptButton();
	}

	@Then("user verify the user gets {string} accept friend request popup on web")
	public void user_verify_the_user_gets_accept_friend_request_popup_on_web(String value) {
		Assert.assertTrue(AddFriend.verifyUserAcceptFriendReqSuccessPopup(value),
				"Failed: The User not accept friend request");
		BlockUser.clickCloseButton();
	}

	@And("verify the user is already a friend on web")
	public void verify_the_user_is_already_a_friend_on_web() {
		Assert.assertTrue(AddFriend.verifyUserAlreadyFriend(), "Failed: The User not accept friend request");
	}
}
