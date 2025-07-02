package com.forum.bdd.web.step_definitions;

import org.testng.Assert;

import com.forum.bdd.web.screens.Forum_ChatGroups_Screen;
import com.forum.bdd.web.screens.Forum_GroupsList_Screen;
import com.forum.bdd.web.screens.Forum_UserProfileDetails_Screen;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Froum_GroupList_Step {

	Forum_GroupsList_Screen GLP = new Forum_GroupsList_Screen();
	Forum_ChatGroups_Screen GroupPage = new Forum_ChatGroups_Screen();

	Forum_UserProfileDetails_Screen UPP = new Forum_UserProfileDetails_Screen();

	@Then("the user should be navigated to the Groups List page")
	public void the_user_should_be_navigated_to_the_groups_list_page() {
		Assert.assertTrue(GLP.verifyGroupListPage(), "User unable to navigate to Group List page");
	}

	@Then("the user should see a chat icon next to each group")
	public void the_user_should_see_a_chat_icon_next_to_each_group() {
		Assert.assertTrue(GLP.isChatIconVisibleForAnyGroup(), "Chat icon is not visible next to the each group");

	}

	@When("the user selects any Group from the list or clicks on chat icon")
	public void the_user_selects_any_group_from_the_list_or_clicks_on_chat_icon() {
		GLP.selectGroupsFromGroupListPage("QA TESTING ");

	}

}
