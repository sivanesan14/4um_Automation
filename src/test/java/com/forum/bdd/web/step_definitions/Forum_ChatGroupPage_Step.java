/**
 * 
 */
package com.forum.bdd.web.step_definitions;

import org.testng.Assert;

import com.forum.bdd.web.screens.Forum_ChatGroups_Screen;
import com.forum.bdd.web.screens.Forum_ChatWindow_Screen;
import com.forum.bdd.web.screens.Forum_Chat_Screen;
import com.forum.bdd.web.screens.Forum_CreateGroup_Screen;
import com.forum.bdd.web.screens.Forum_CreateMessage_Screen;
import com.forum.bdd.web.screens.Forum_GroupDeatil_Screen;
import com.forum.bdd.web.screens.Forum_Groups_Screen;
import com.forum.bdd.web.screens.Forum_Home_Screen;
import com.forum.bdd.web.screens.Forum_Login_Screen;
import com.forum.bdd.web.screens.Forum_UserProfileDetails_Screen;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Forum_ChatGroupPage_Step {

	Forum_Login_Screen LP = new Forum_Login_Screen();
	Forum_Home_Screen HP = new Forum_Home_Screen();
	Forum_Groups_Screen GP = new Forum_Groups_Screen();
	Forum_CreateGroup_Screen CGP = new Forum_CreateGroup_Screen();
	Forum_Chat_Screen CP = new Forum_Chat_Screen();
	Forum_ChatWindow_Screen CWP = new Forum_ChatWindow_Screen();
	Forum_UserProfileDetails_Screen UPP = new Forum_UserProfileDetails_Screen();
	Forum_CreateMessage_Screen CMP = new Forum_CreateMessage_Screen();
	Forum_ChatGroups_Screen GroupPage = new Forum_ChatGroups_Screen();
	Forum_GroupDeatil_Screen GDP = new Forum_GroupDeatil_Screen();

	String expectedGroupFromSearch;
	String expectedGroupNameFromGT;

	@When("the user enters a group's name in the search input field")
	public void the_user_enters_a_friend_s_name_in_the_search_input_field() {
		GroupPage.enterGroupsName("QA TESTING");
	}

	@When("the user enters group's name in the search input field")
	public void enterGroupNamrNotPart() {
		GroupPage.enterGroupsName("4UM automation");
	}

	@Then("the that group name should be displayed in the search results")
	public void the_friend_s_name_should_be_displayed_in_the_search_results() {

//		expectedGroupFromSearch = GroupPage.getGroupNameFromSerch();
//		String expectedNmae = expectedGroupFromSearch;
//		Assert.assertTrue(CP.getSearchResult(),
//				"Searched group name '" + expectedNmae + "' is not found in the search list.");
	}

	@When("the user selects the group from the search results")
	public void the_user_selects_the_friend_s_name_from_the_search_results() {
		expectedGroupFromSearch = GroupPage.getGroupNameFromSerch();
		String selectGroup = expectedGroupFromSearch;
		GroupPage.selectGroupFromSearchList(selectGroup);
	}

	@Then("the user should be navigated to the chat window with that group")
	public void the_user_should_be_navigated_to_the_chat_window_with_that_Group() {

		// expectedGroupNameFromCW = GroupPage.getGroupNameFromGroupsTab();
		String expectedGroupName = expectedGroupFromSearch;
		String actualGroupName = CWP.verifyChatWindow();

		Assert.assertEquals(actualGroupName, expectedGroupName,
				"Searched group name '" + expectedGroupName + "' is not displayed on chat window");
	}
	
	@Then("the user should be navigated to the chat window with that Group")
	public void vrifyChatWindoe() {

		
		String expectedGroupName = "QA TESTING";
		String actualGroupName = CWP.verifyChatWindow();

		Assert.assertEquals(actualGroupName, expectedGroupName,
				"Searched group name '" + expectedGroupName + "' is not displayed on chat window");
	}

	@Then("the user should be navigated to the group detail Profile page")
	public void navigateToGroupDeatailPage() {
		Assert.assertTrue(GDP.verifyGroupDetailPage(), "User anable to navigate to group detail page");
	}

	@Then("the user should see a {string} option to start a conversation")
	public void verifyJoinAndLeaveGroupText(String JoinGroup) {

		Assert.assertEquals(GDP.verifyJoinAndLeaveText(), JoinGroup,
				"User Uable to see the '" + JoinGroup + "' option to start a conversation");
	}

	@Then("the user selects any group's from the Groups_tab")
	public void selectGroupFromGroupsTab() {

		expectedGroupNameFromGT=GroupPage.getGroupNameFromGroupsTab();
		GroupPage.selectGroupFromGroupTab();
	}

	@Then("user select the Groups tab from chat window page")
	public void selectGroupsTab() {

		CP.selectGroupsTab();

	}
	
	@Then("that group should not be visible on Groups tab")
	public void verifyLeaveGroup_From_GroupsTab() {

		
		Assert.assertEquals(expectedGroupNameFromGT, "QA TESTING", "After leave the group is still visible on the chat groups tab page");
	}

}
