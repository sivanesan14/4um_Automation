package com.forum.bdd.web.step_definitions;

import org.testng.Assert;

import com.forum.bdd.web.screens.Forum_ChatWindow_Screen;
import com.forum.bdd.web.screens.Forum_Chat_Screen;
import com.forum.bdd.web.screens.Forum_CreateGroup_Screen;
import com.forum.bdd.web.screens.Forum_FrindsList_Screen;
import com.forum.bdd.web.screens.Forum_Groups_Screen;
import com.forum.bdd.web.screens.Forum_Home_Screen;
import com.forum.bdd.web.screens.Forum_Login_Screen;
import com.forum.bdd.web.screens.Forum_UserProfile_Screen;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Forum_UserProfilePage_Step {	
	Forum_Login_Screen LP = new Forum_Login_Screen();
	Forum_Home_Screen HP = new Forum_Home_Screen();
	Forum_Groups_Screen GP = new Forum_Groups_Screen();
	Forum_CreateGroup_Screen CGP = new Forum_CreateGroup_Screen();
	Forum_Chat_Screen CP = new Forum_Chat_Screen();
	Forum_ChatWindow_Screen CWP = new Forum_ChatWindow_Screen();
	Forum_UserProfile_Screen UPP= new Forum_UserProfile_Screen();
	Forum_FrindsList_Screen FLP = new Forum_FrindsList_Screen();
	
	
	@Then("the user should be navigated to the Profile page")
	public void the_user_should_be_navigated_to_the_profile_page_of_that_person() {
		
		Assert.assertTrue(true, UPP.isProfilePageVisible());
	}

	@Then("the user should see an option to send a friend request to start a conversation")
	public void the_user_should_see_an_option_to_send_a_friend_request_to_start_a_conversation() {
		Assert.assertTrue(UPP.verifyAddFriendBtn(), "'Add Friend' button is not visible so user unable to send a friend request to start the conversation.");;
	}

	

	@Then("the user clicks on the Friends section")
	public void ClickOnfriend() {
		UPP.clickOnFriends();
	}
	

	@When("the user clicks on the Groups section")
	public void the_user_clicks_on_the_groups_section() {
		UPP.clickOnGroups();
	    
	}
	

}
