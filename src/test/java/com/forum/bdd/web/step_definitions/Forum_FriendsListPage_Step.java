/**
 * 
 */
package com.forum.bdd.web.step_definitions;

import org.testng.Assert;
import com.forum.bdd.web.screens.Forum_FrindsList_Screen;
import com.forum.bdd.web.screens.Forum_UserProfileDetails_Screen;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Forum_FriendsListPage_Step {

	Forum_UserProfileDetails_Screen UPP = new Forum_UserProfileDetails_Screen();
	Forum_FrindsList_Screen FLP = new Forum_FrindsList_Screen();

	@Then("the user should be navigated to the Friend List page")
	public void user_should_navigate_to_friend_list_page() {
		FLP.verifyFriendListPage();
	}

	@Then("the user should see a chat icon next to each friend")
	public void user_should_see_the_chat_icon_on_the_each_friends() {
		Assert.assertTrue(FLP.isChatIconVisibleForAnyFriend(), "Chat icon is not visible next to the each user name");

	}

	@When("the user selects any friend from the list or clicks on chat icon")
	public void user_select_the_any_friend_from_the_friend_list() {

		String friendToSelect = "Suryaputra";
		String ExpectedFriendSelcted = FLP.selectUserFromFriendListPage(friendToSelect);
		Assert.assertEquals(friendToSelect, ExpectedFriendSelcted,
				"The selected frind is : " + friendToSelect + "is not found in friend list page");
	}

}
