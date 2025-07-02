package com.forum.bdd.web.screens;

import java.util.Arrays;

import com.forum.bdd.ccl.WebActions;

public class Forum_UserProfileDetails_Screen {
	String homepage = "//p[@title = 'Home']";
	String profilename = "//img[@data-testid='user_profile']";
	String userProfilePage = "//p[@data-testid='typographyWith-i18n' and @title='Profile']";
	String editbutton = "(//button[@id='education-edit-icon']//*[name()='svg'])[1]";
	String editeSummary = "//p[text()='Edit Summary']";
	String name = "//input[@placeholder='Name']";
	String Designation = "//input[@placeholder='Designation']";
	String address = "//textarea[@placeholder='Address']";
	String webAddress = "//input[@placeholder='Web Address']";
	String saveButton = "//button[text()='Save']";
	String CancelButton = "//button[@id='button-contain' and text()='cancel']";
	String UserProfileName = "//p[@id='user-name']";
	String nameError = "//span[contains(text(), 'cannot exceed') and contains(text(), 'Name')]";
	String designationError = "//span[contains(text(), 'The Designation cannot exceed')]";
	String addressError = "//span[contains(text(), 'The Address cannot exceed')]";

	WebActions play = new WebActions();

	public void clickProfileName() {
		play.click(profilename, "Profile Name");
	}

	public void verifyUserProfilePage() {
		String actualText = play.getText(userProfilePage);
		play.verifyText(actualText, "Profile");
	}

	public void clickEditButton() {
		play.click(editbutton, "edit button");
	}

	public void verifyEditSummaryPage() {
		String actualText = play.getText(editeSummary);
		play.verifyText(actualText, "Edit Summary");
	}

	public void enterUserName(String name) {
		play.sendKeys(this.name, name, "John Doe");
	}

	public void enterUserDesignation(String designation) {
		play.sendKeys(Designation, designation, "Software Engineer");
	}

	public void enterUserAddress(String addressText) {
		play.sendKeys(address, addressText, "123 Main Street, Cityville");
	}

	public void enterUserWebAddress(String webAddr) {
		play.sendKeys(webAddress, webAddr, "www.johndoe.dev");
	}

	public void clickSaveButton() {
		play.click(saveButton, "Save Button");
	}

	public void clears_the_name_field() {

		play.clear(name, name);
	}

	public void save_button_should_be_disabled() {
		play.isDisabled(saveButton, "Save button should be disabled");
		System.out.println("Verified: Save button is disabled");
	}

	public void the_user_enters_the_new_name_on_web(String string) {
		play.clear(name, name);
		play.sendKeys(this.name, string, "Dhoni");
	}

	public void clicks_the_cancel_button() {

		play.click(CancelButton, "Cancel Button");
	}

	public void the_previously_saved_name_should_remain_unchanged() {
		String actualText = play.getText(UserProfileName);
		play.verifyText(actualText, "John Doe");

	}

	public String generateString(char ch, int count) {
		char[] chars = new char[count];
		Arrays.fill(chars, ch);
		return new String(chars);
	}

	public void the_user_enters_more_than_allowed_characters_in_the_name_field() throws InterruptedException {
		String longName = generateString('A', 26); // Max allowed is 25
		play.clear(name, "Name");
		Thread.sleep(1000);
		play.sendKeys(name, longName, "Name");
	}

	public void an_error_message_should_be_displayed_for_name_field_stating(String string) {

		String actualText = play.getText(nameError);
		play.verifyText(actualText, "The Name cannot exceed 25 characters.");
	}

	public void the_user_enters_more_than_allowed_characters_in_the_designation_field() {
		String longDeg = generateString('A', 51); // Max allowed is 50
		play.clear(Designation, "Designation");
		play.sendKeys(Designation, longDeg, "Designation");

	}

	public void an_error_message_should_be_displayed_for_designation_field_stating(String string) {
		String actualText = play.getText(designationError);
		play.verifyText(actualText, "The Designation cannot exceed 50 characters.");

	}

	public void the_user_enters_more_than_allowed_characters_in_the_address_field() {
		String longAdd = generateString('A', 200); // Max allowed is 200
		play.clear(address, "Address");
		play.sendKeys(address, longAdd, "Address");

	}

	public void an_error_message_should_be_displayed_for_address_field_stating(String string) {
		String actualText = play.getText(addressError);
		play.verifyText(actualText, "The Address cannot exceed 200 characters.");

	}

}
