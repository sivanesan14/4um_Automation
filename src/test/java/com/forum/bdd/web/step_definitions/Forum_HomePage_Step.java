/**
 * 
 */
package com.forum.bdd.web.step_definitions;

import org.testng.Assert;

import com.forum.bdd.web.screens.Forum_Home_Screen;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class Forum_HomePage_Step {
	Forum_Home_Screen HP = new Forum_Home_Screen();

	@Given("the user is on the Home page")
	public void user_is_on_home_page() {
		Assert.assertTrue(HP.verify4umLogoOnHomeapge(), "4um logo is not displayed on Home page");
	}

	@When("the user clicks on their profile name from the left side menu")
	public void user_clicks_on_the_user_profile_name_from_left_side_menu_icon() {

		HP.clickOnUserProfile();

	}

}
