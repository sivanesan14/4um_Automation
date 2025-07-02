package com.forum.bdd.web.screens;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.forum.bdd.ccl.WebActions;
import com.forum.bdd.integrations.common_utils.RandomGenerator;
import com.forum.bdd.integrations.report_utils.ReportManager;
import com.microsoft.playwright.TimeoutError;

public class Forum_Polling_Screen {

	String home_MenuOption = "(//span[text()='Home'])[1]";
	String create_MenuButton = "//span[text()='Create']";
	String poll_MenuOption = "//span[contains(text(),'Poll')]";
	String title_CreatePoll = "//p[text()='Create Poll']";
	String btn_AddDescription = "//p[contains(text(),'Add Description')]";
	String textBox_Description = "//textarea[@placeholder='Write your Description']";
	String share_Dropdown = "(//div[@role='combobox'])[1]";
	String option_Group = "//li[@data-value='group']";
	String searchBox_Group = "//input[@id='title-input']";
	String firstGroupInResults = "(//li[@role='menuitem'])[1]";
	String btn_Ok = "//button[contains(text(),'Ok')]";
	String group_LabelInDropdown = "(//div[@role='combobox'])[1]/span/p";
	String poll_Question = "//textarea[@placeholder='Ask a question']";
	String btn_AddChoice = "(//*[@data-testid='AddIcon'])[2]/parent::span";
	String choice_1 = "//label[text()='Choice 1']/following-sibling::div/input";
	String choice_2 = "//label[text()='Choice 2']/following-sibling::div/input";
	String choice_3 = "//label[text()='Choice 3']/following-sibling::div/input";
	String choice_4 = "//label[text()='Choice 4']/following-sibling::div/input";
	String choice_5 = "//label[text()='Choice 5']/following-sibling::div/input";
	String duration_dropdown = "(//div[@role='combobox'])[2]";
	String duration_days = "//li[@data-value='%s']";
	String selectedDays = "(//div[@role='combobox'])[2]/following-sibling::input";
	String dropDown_PollCategory = "(//div[@role='combobox'])[3]";
	String dynamic_CategoryOption = "//li[contains(text(),'%s')]";
	String create_Button = "//button[text()='Create']";
	String success_Message = "//p[contains(@title,'Successfully created poll')]";
	// Error messages
	String error_Question = "//textarea[@placeholder='Ask a question']/parent::div/parent::div/following-sibling::div";
	String dynamic_Error_Choice = "//div[contains(text(),'%s')]";
	String dynamic_Error_Description = "//div[contains(text(),'%s')]";

	String VerifyHomePage = "//*[@title='Home']";
	// dynamic locators
	String question_TextInPoll = "//span[contains(text(),'%s')]";
	String question_CreatedPoll = "//span[@id='question%s']";
	String description_CreatedPoll = "//span[@id='thought%s']";
	String poll_choices_CreatedPoll = "//div[@aria-labelledby='pollChoices%s']";
	String searchBar_General = "//input[@placeholder='Search Individuals, Groups etc.']";
	String loginUser_Avatar = "(//div[@id='userDetails']/div)[1]";
//	String userPost_Tab = "//button[text()='Posts']";
	String userPost_Tab = "//button[text()='Post']";
	String menuButtonInFirstPost = "(//button[@id='settings'])[1]";
	String deleteOption = "(//p[@title='Delete'])/parent::li";
	String infoMessageInDeletePoll = "(//p[@title='Are you sure you want to delete the poll?'])";
	String deletePostButton = "(//button[text()='Delete'])";
	String successMessageDeletePoll = "(//p[@title='The poll has been successfully deleted.'])";

	WebActions play = new WebActions();
	LocalDateTime currentDateTime = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	String formattedDateTime = currentDateTime.format(formatter);

	public void click_CreateMenu() {
		play.waitForClick(create_MenuButton, 10000, "Create Menu");
	}

	public void click_PollMenu() {
		play.waitForVisible(poll_MenuOption, 10000, "Poll Menu");
		play.mouseHover(poll_MenuOption, "Poll Menu");
		play.isEnabled(poll_MenuOption, "Poll Menu");
		play.waitForClick(poll_MenuOption, 10000, "Poll Menu");
	}

	public void verifyTitleInPollPage() {
		play.isVisible(title_CreatePoll, "Title : Create Poll");
	}

	public boolean verifTheCurrentUrl(String path) {
		String currentUrl = play.getCurrentUrl();
		boolean contains = currentUrl.contains(path);
		return contains;
	}

	public String enterQuestion(String type) {
		String value;
		if (type.contains("max")) {
			value = RandomGenerator.randomString(150);
		} else if (type.contains("min")) {
			value = RandomGenerator.randomString(1);
			;
		} else {
			value = (type == null) ? formattedDateTime + " Test Question" : formattedDateTime + " " + type;
		}
		play.sendKeys(poll_Question, value, "Poll Question");
		return value;
	}

	public String enter_Choice_1(String type) {
		String value;
		if (type.contains("max")) {
			value = RandomGenerator.randomString(40);
		} else if (type.contains("min")) {
			value = "A";
		} else {
			value = (type == null) ? "Test 1" : type;
		}
		play.sendKeys(choice_1, value, "Choice_One");
		return value;
	}

	public String enter_Choice_2(String type) {
		String value;
		if (type.contains("max")) {
			value = RandomGenerator.randomString(40);
		} else if (type.contains("min")) {
			value = "B";
		} else {
			value = (type == null) ? "Test 2" : type;
		}
		play.sendKeys(choice_2, value, "Choice_two");
		return value;
	}

	public String enter_Choice_3(String type) {
		play.scrollToElement(choice_3, "Choice 3");
		String value;
		if (type.contains("max")) {
			value = RandomGenerator.randomString(40);
		} else if (type.contains("min")) {
			value = "C";
		} else {
			value = (type == null) ? "Test 3" : type;
		}
		play.sendKeys(choice_3, value, "Choice_three");
		return value;
	}

	public String enter_Choice_4(String type) {
		play.scrollToElement(choice_4, "Choice 4");
		String value;
		if (type.contains("max")) {
			value = RandomGenerator.randomString(40);
		} else if (type.contains("min")) {
			value = "D";
		} else {
			value = (type == null) ? "Test 4" : type;
		}
		play.sendKeys(choice_4, value, "Choice_Four");
		return value;
	}

	public String enter_Choice_5(String type) {
		play.scrollToElement(choice_5, "Choice 5");
		String value;
		if (type.contains("max")) {
			value = RandomGenerator.randomString(40);
		} else if (type.contains("min")) {
			value = "E";
		} else {
			value = (type == null) ? "Test 5" : type;
		}
		play.sendKeys(choice_5, value, "Choice_Five");
		return value;
	}

	public void add_Pollchoice() {
		play.scrollToElement(btn_AddChoice, "Add Choice Button");
		play.waitForClick(btn_AddChoice, 100000, "Wait for Add Choice button");
	}

	public void clickAddDescription() {
		play.scrollToElement(btn_AddDescription, "Add Description Button");
		play.click(btn_AddDescription, "Add Description");
	}

	public String enter_Description(String type) {
		String value;
		if (type.contains("max")) {
			value = RandomGenerator.randomString(1900);
		} else if (type.contains("min")) {
			value = RandomGenerator.randomString(1);
			;
		} else {
			value = formattedDateTime + " " + type;
		}
		play.sendKeys(textBox_Description, value, "Poll Description");
		return value;
	}

	public void open_ShareDropdown() {
		play.scrollToElement(share_Dropdown, "Share Drop Down");
		play.waitForClick(share_Dropdown, 10000, "Share Drop down");
	}

	public void click_GroupsOption() {
		play.waitForClick(option_Group, 10000, "Groups Option");
	}

	public String selectGroup(String value) {
		play.waitForVisible(searchBox_Group, 10000, "Search Box");
		play.waitForClick(searchBox_Group, 10000, "Search Box");
		play.sendKeys(searchBox_Group, value, "Search Group");
		play.waitForVisible(firstGroupInResults, 10000, "Select First group from the results");
		play.waitForClick(firstGroupInResults, 10000, "Select First group from the results");
		String nameInTextBox = play.getAttributeValue(searchBox_Group, "value");
		play.verifyText(nameInTextBox, value);
		play.waitForClick(btn_Ok, 10000, "Ok Button");
		play.waitForVisible(group_LabelInDropdown, 10000, "Selected Group label in the DropDown");
		String nameInDropDown = play.getAttributeValue(group_LabelInDropdown, "title");
		play.verifyText(nameInDropDown, value);
		return nameInDropDown;
	}

	public void selectPollCategory(String value) {
		play.waitForVisible(dropDown_PollCategory, 10000, "Poll Category dropdown");
		play.scrollToElement(dropDown_PollCategory, "Poll Category dropdown");
		play.click(dropDown_PollCategory, "Poll Category dropdown");
		play.scrollToElement(String.format(dynamic_CategoryOption, value), "Poll category");
		play.click(String.format(dynamic_CategoryOption, value), "Poll category");
		String pollCategory = play.getText(dropDown_PollCategory);
		play.verifyText(pollCategory, value);

	}

	public void select_PollDuration(String daysInNumber) {
		play.waitForVisible(duration_dropdown, 10000, "Poll Duration dropdown");
		play.scrollToElement(duration_dropdown, "Poll Duration Dropdown");
		play.click(duration_dropdown, "Poll Duration Dropdown");
		play.click(duration_days.formatted(daysInNumber), "Number Of Days");
		String attributeValue = play.getAttributeValue(selectedDays, "value");
		play.verifyText(attributeValue, daysInNumber);
	}

	public boolean successMessage() {
		return play.waitForVisible(success_Message, 10000, "Wait for Poll creation success message");
	}

	public void click_CreatePoll() {
		play.scrollToElement(create_Button, "Create Button");
		play.click(create_Button, "Create Poll");
	}

	public void verifyHomePageAfterPollCreation() {
		play.waitForVisible(VerifyHomePage, 10000, "Navigated to Home page");
	}

	public void enter_InvalidQuestion(String scenario) {
		String value;
		if (scenario.contains("empty")) {
			value = "";
		} else {
			value = "     ";
		}
		play.sendKeys(poll_Question, value, "Poll Question");
	}

	public boolean errorValidation_Question(String value) {
		return play.waitForVisible(error_Question, 10000, "Error Message for Invalid Question");
	}

	public void enter_InvalidChoices(String scenario, int choice) {
		String value;
		if (scenario.contains("empty")) {
			value = "";
		} else if (scenario.contains("space")) {
			value = "     ";
		} else {
			value = "Test";
		}
		switch (choice) {
		case 1:
			play.sendKeys(choice_1, value, "Poll Choice 1");

			break;
		case 2:
			play.sendKeys(choice_2, value, "Poll Choice 2");
		default:
			ReportManager.logInfo("Failed: Choice index is wrong");
			break;
		}
	}

	public boolean errorValidation_Choices(String message) {
		return play.waitForVisible(String.format(dynamic_Error_Choice, message), 10000,
				"Error Message for Invalid Choice");
	}

	public void enter_InvalidDescription(String scenario) {
		String value;
		if (scenario.contains("empty")) {
			value = "";
		} else if (scenario.contains("space")) {
			value = "     ";
		} else {
			value = "Poll Description have been Automated";
		}
		play.sendKeys(textBox_Description, value, "Poll Description");
	}

	public boolean errorValidation_Description(String message) {
		return play.waitForVisible(String.format(dynamic_Error_Description, message), 10000,
				"Error Message for Invalid Description");
	}

	public boolean pollNotCreated() {
		boolean dispaly = false;
		try {
			dispaly = play.waitForVisible(success_Message, 2000, "Success Message - Poll not created");
			ReportManager.logInfo("Failed: The success message appeared even though there was an error in a field.");
		} catch (TimeoutError e) {
			ReportManager.logInfo(
					"Success: Due to a field error, the poll was not created, and no success message was displayed.");
		}
		return dispaly;
	}

	public void enterQuestion_Dynamic(String value) {
		play.sendKeys(poll_Question, formattedDateTime + value, "Poll Question");
	}

	public void click_HomeMenu() {
		play.waitForClick(home_MenuOption, 10000, "Home Menu");
	}

	public boolean findCreatedPoll(String questionTxt) {
		play.scrollToElement(question_TextInPoll.formatted(questionTxt), "Question in Poll");
		return play.waitForVisible(question_TextInPoll.formatted(questionTxt), 10000, "Question in Poll");
	}

	public String getThePollID(String questionTxt) {
		String attributeValue = play.getAttributeValue(question_TextInPoll.formatted(questionTxt), "id");
		String id = attributeValue.substring(attributeValue.indexOf("_"));
		return id;
	}

	public String getQuestion_InCreatedPoll(String postID) {

		String text = play.getText(question_CreatedPoll.formatted(postID));

		return text;
	}

	public String getDescription_InCreatedPoll(String postID) {

		String text = play.getText(description_CreatedPoll.formatted(postID));

		return text;
	}

	public String getPollChoices_InCreatedPoll(String postID) {

		String text = play.getText(poll_choices_CreatedPoll.formatted(postID));

		return text;
	}

	public void navigateToGroup_ByGeneral_SearchBar(String GroupName) {
		play.sendKeys(searchBar_General, GroupName, "General Search Bar");
		play.waitForClick(firstGroupInResults, 10000, "Group Name in Results");

	}

	public void navigateToLoginUserProfile() {
		play.waitForClick(loginUser_Avatar, 100000, "Login User Avatar");
	}

	public void navigateTo_PostTab_InUserProfile() {
		play.waitForClick(userPost_Tab, 10000, "Post Tab in User Profile");
	}

	public int verifytheCreatedPollIsExist(String questionTxt) {

		int theCountOfLocators = play.getTheCountOfLocators(question_TextInPoll.formatted(questionTxt),
				"Created Poll Question");
		return theCountOfLocators;

	}

}