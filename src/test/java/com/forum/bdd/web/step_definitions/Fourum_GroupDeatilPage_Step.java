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

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Fourum_GroupDeatilPage_Step {

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

	@Given("the user selects any group from the My_Groups tab")
	public void the_user_selects_any_group_from_the_my_groups_tab() {
		GP.selectGroups();
	}

	@Given("the user navigates to the selected group detail page")
	public void the_user_navigates_to_the_selected_group_detail_page() {
		Assert.assertTrue(GDP.verifyGroupDetailPage(), "User unable to navigate to group detail page.");
	}

	@Then("a success popup message {string} should appear")
	public void a_success_popup_message_should_be_displayed(String ExpectedText) {
		Assert.assertEquals(GDP.verifyGroupLeave_JoinSuccessMsg(), ExpectedText,
				"The left group pop up message '" + ExpectedText + "' is not displayed");
	}

	@Then("the user Navigate back to group chat window")
	public void NavigateBackToChatWindow() {
		GDP.navigateBack();
	}

	@Then("User should see the {string} on the the chat window")
	public void verifyResumrConversionText(String ExpectedText) {
		Assert.assertEquals(CWP.verifyResumeConversionText(), ExpectedText,
				"The text '" + ExpectedText + " ' is not visible after left the group");
		CWP.clickOnGroupName();
		GDP.clcikOnJoinGroup();
	}

	@Then("the user clicks on the Leave This Group button from the group detail page")
	public void clickOnLeaveGroup() {
		GDP.clickOnLeaveGroup();
	}

	@Then("the user should see the {string} on the group detail page")
	public void the_user_should_see_the_on_the_group_detail_page(String elements) {
		GDP.verifyGroupDetailUIElements(elements);
	}

	@Given("user click on Joined_Groups tab")
	public void user_click_on_joined_groups_tab() {
		GP.clickOnJoined_Groups();
	}

	@Given("the user selects any group from the Joined_Groups tab")
	public void the_user_selects_any_group_from_the_joined_groups_tab() {
		GP.selectGroups();
	}

	@Given("the user clicks on the edit \\(pencil) icon")
	public void the_user_clicks_on_the_edit_pencil_icon() {
		GDP.clickOnEditPencilBtn();

	}

	@Given("the user clicks on the right tick mark to save changes")
	public void the_user_clicks_on_the_right_tick_mark_to_save_changes() {
		GDP.clickOnRightMark();
	}

	@Then("the updated group name should be visible on the page")
	public void the_updated_group_name_should_be_visible_on_the_page() {
		String previousGrpName = GDP.clickOnRightMark();
		String updatedGPName = GDP.groupUpdate();
		Assert.assertNotEquals(updatedGPName, previousGrpName,
				"The Updated group name " + updatedGPName + " is not displayed and User unable to edit the group");
	}

	@Then("the new group name should be displayed as the header of the group detail page")
	public void the_new_group_name_should_be_displayed_as_the_header_of_the_group_detail_page() {
		String previousGrpName = GDP.clickOnRightMark();
		String updatedGPName = GDP.verifyGroupUpdate();
		Assert.assertNotEquals(updatedGPName, previousGrpName, "The Updated group name " + updatedGPName
				+ " is not displayed as a header and User unable to edit the group");
	}

	@Then("Right button status should be {string}")
	public void right_button_status_should_be(String expectedStatus) {
		boolean isEnabled = GDP.isRightTickMarkBtnEnabled();
		if (expectedStatus.equalsIgnoreCase("enabled")) {
			Assert.assertTrue(isEnabled, "Expected Create button to be enabled");
		} else {
			Assert.assertFalse(isEnabled, "Expected Create button to be disabled");
		}
	}

}
