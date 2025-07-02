package com.forum.bdd.web.step_definitions;

import com.forum.bdd.web.screens.Forum_Login_Screen;

import io.cucumber.java.en.*;

public class Forum_Login_Steps {

	Forum_Login_Screen OnlineLogin = new Forum_Login_Screen();

	@When("the user enters the url for 4um application")
	public void the_user_enters_the_url_for_4um_application() {
		OnlineLogin.enterURL();
	}

	@And("the user clicks on the create account button")
	public void the_user_clicks_on_the_create_account_button() {

	}

	@And("the user enters the email address for user creation")
	public void the_user_enters_the_email_address_for_user_creation() {

	}

	@And("the user enters the username for user creation")
	public void the_user_enters_the_username_for_user_creation() {

	}

	@And("the user enters the password for user creation")
	public void the_user_enters_the_password_for_user_creation() {

	}

	@And("the user enters the confirmation password for user creation")
	public void the_user_enters_the_confirmation_password_for_user_creation() {

	}

	@And("the user clicks on the terms and privacy policy checkboxs")
	public void the_user_clicks_on_the_terms_and_privacy_policy_checkboxs() {

	}

	@And("the user click on the Register account button")
	public void the_user_click_on_the_register_account_button() {

	}

	@Then("the user enter the code")
	public void the_user_enter_the_code() {

	}

	@When("the user clicks on next button")
	public void the_user_clicks_on_next_button() {

	}

	@Then("verify the user successfully register to 4um application")
	public void verify_the_user_successfully_register_to_4um_application() {

	}

	@And("the user enters the email address for login")
	public void the_user_enters_the_email_address_for_login() {
		OnlineLogin.enterEmailID();
	}

	@And("the user enters the password for login")
	public void the_user_enters_the_password_for_login() {
		OnlineLogin.enterPassword();
	}

	@When("the user clicks on login next button")
	public void the_user_clicks_on_login_next_button() {
		OnlineLogin.clickNextButton();
	}

	@Then("verify the user successfully login to 4um application")
	public void verify_the_user_successfully_login_to_4um_application() throws InterruptedException {
		OnlineLogin.verifyHomePage();
	}
	
	@When("the user logout from the 4um application")
	public void Logout_from_the_4um_Application() {
		OnlineLogin.clickLogoutOptions();
		OnlineLogin.clickLogout();
	}
	
	@And("the user click on logout confirmation button")
	public void the_user_click_on_logout_confirmation_button() {
		OnlineLogin.clickLogoutPopupButton();
	}
	
	@Then("the user logout successfully from 4um application")
	public void logout_successfully_from_4um_application() {
		OnlineLogin.verifyLogout();
	}
}
