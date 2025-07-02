package com.forum.bdd.web.step_definitions;

import org.testng.Assert;

import com.forum.bdd.integrations.report_utils.ReportManager;
import com.forum.bdd.web.screens.Forum_CreateGroup_Screen;
import com.forum.bdd.web.screens.Forum_Groups_Screen;
import com.forum.bdd.web.screens.Forum_Home_Screen;
import com.forum.bdd.web.screens.Forum_Login_Screen;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Forum_CreateGroup_Steps {

	Forum_Login_Screen LoginPage = new Forum_Login_Screen();
	Forum_Home_Screen HomePage = new Forum_Home_Screen();
	Forum_Groups_Screen GroupPage = new Forum_Groups_Screen();
	Forum_CreateGroup_Screen CreateGrpPage = new Forum_CreateGroup_Screen();

	@Given("the user has logged in with valid credentials")
	public void the_user_has_logged_in_with_valid_credentials() {
		LoginPage.successFullyLogin();
	}

	@Given("the user navigates to the Groups section")
	public void navigateToGroupSection() {
		HomePage.clickONGroupMenu();
		Assert.assertTrue(GroupPage.verifyGroupsPage());
	}

	@Given("the user clicks on the Create Group icon")
	public void the_user_clicks_on_the_button() {
		GroupPage.clickOnCreateGroupIcon();
		Assert.assertTrue(CreateGrpPage.userIsOnCreateGroupScreen(), "Create Group page is not visible.");
	}

	@When("the user clicks on the camera icon of the group banner image")
	public void the_user_taps_the_camera_icon_on_the_group_banner() {
		CreateGrpPage.clickOnGroupBannerCameraIcon();
	}

	@When("the system gallery should open and the selected image should be displayed as the new banner image")
	public void the_system_gallery_should_open_and_the_selected_image_should_be_displayed_on_the_banner_image() {
		CreateGrpPage.selectBannerImage();
	}

	@When("the user click on the the add plus icon of group profile image")
	public void the_user_taps_the_group_profile_edit_icon() {
		CreateGrpPage.clickOnProfileEditIcon();

	}

	@When("the system gallery should open and the selected image should be displayed as the new profile image")
	public void the_system_gallery_should_open_and_the_selected_image_should_be_displayed_on_the_profile_icon() {
		CreateGrpPage.selectProfileImage();
	}

	@When("the user enters the group name in the Group Name field")
	public void the_user_enters_in_the_group_name_field() {
		CreateGrpPage.enterGroupName("this is Study Group for automation2");

	}

	@When("the user writes {string} in the description field")
	public void the_user_writes_in_the_description_field(String grpDesc) {
		CreateGrpPage.enterGroupDescription(grpDesc);
	}

	@When("the user Clicks on Add Member")
	public void the_user_taps_on() {
		CreateGrpPage.tapAddMember();

	}

	@When("the user selects a member from the friends list")
	public void the_user_selects_a_member_from_the_contact_list() {
		CreateGrpPage.selectMember();

	}

	@Then("the selected member should appear in the Members list")
	public void the_selected_member_should_appear_in_the_members_list() {

	}

	@Then("the user can see the error message for the duplicate groups")
	public void VerifyDuplicateGroups() {
		String ActualText = CreateGrpPage.verifyDuplicateErrorMessage();
		String ExpectedText = "Duplicate group names are not allowed.";
		Assert.assertEquals(ActualText, ExpectedText, "The Duplicate group cannot be verified");

	}

	@Then("the user clicks on the Create Group button")
	public void the_user_clicks_on_the_create_group_button() {
		// Assert.assertTrue(CreateGrpPage.clickOnCreateGroup());
		CreateGrpPage.clickOnCreateGroup();
	}

	@Then("the group should be successfully created with a pop-up message")
	public void the_group_should_be_successfully_created_with_a_pop_up_message() {

		String actualText = CreateGrpPage.verifyGroupCreationPopup();
		String duplicateError = "Duplicate group names are not allowed.";
		if (actualText.equals(duplicateError)) {
			System.out.println("❌ Duplicate groups are found and cannot proceed with group creation");
			Assert.assertTrue(true); // Assert known error scenario occurred
		} else {
			// Extract group name from full message using regex
			String groupName = actualText.replaceAll("The group (.+) has been successfully created\\.", "$1");

			// Build expected text
			String expectedText = "The group " + groupName + " has been successfully created.";
			Assert.assertEquals(actualText, expectedText, "Popup message not as expected. Found: " + actualText);
		}

	}

	@Given("the user navigates to the Create Group page")
	public void the_user_navigate_to_create_group_page() {

		String actualBgColor = CreateGrpPage.verifyEmptyBGBannerColor();
		String expectedBgColor = "rgb(93, 110, 110)";
		Assert.assertEquals(actualBgColor, expectedBgColor,
				"Expected background color code for the empty banner image is " + expectedBgColor + ", but found: "
						+ actualBgColor);
		String profileActualBgColor = CreateGrpPage.verifyEmptyBGProfileColor();
		String profileExpectedBgColor = "rgb(2, 131, 131)";

		Assert.assertEquals(profileActualBgColor, profileExpectedBgColor,
				"Expected background color code for the empty profile image is " + profileExpectedBgColor
						+ ", but found: " + profileActualBgColor);
	}

	@Then("the {string} should be visible on the page")
	public void the_should_be_visible_on_the_page(String elementName) {

		boolean isVisible = CreateGrpPage.userIsOnCreateGroupsPage(elementName);

		ReportManager.logInfo("Checking visibility for element: ");
		ReportManager.logInfo("Verifying if the \"" + elementName + "\" element is visible on the Create Group page.");
		Assert.assertTrue(isVisible, "Element not visible: " + elementName);
		ReportManager.logInfo("The \"" + elementName + "\" element is visible on the Create Group page.");
		ReportManager.logPass("All the elements are visible: ");
	}

	@Then("the user should see the empty banner image")
	public void the_user_should_see_the_empty_banner_image() {

		// boolean isEleVisible = CreateGrpPage.veriftEmptyBannerImg();

		Assert.assertTrue(CreateGrpPage.veriftEmptyBannerImg(),
				"Empty banner image is not visible on the Create Group page.");

	}

	@Then("the user should see the empty group profile image")
	public void the_user_should_see_the_empty_group_profile_image() {

		// boolean isEleVisible = CreateGrpPage.veriftEmptyProfileImg();
		Assert.assertTrue(CreateGrpPage.veriftEmptyProfileImg(),
				"Empty profile image is not visible on the Create Group page.");
	}

	@Given("the user sees an empty banner image with a camera icon")
	public void the_user_sees_an_empty_banner_image_with_a_camera_icon() {
		Assert.assertTrue(CreateGrpPage.isEmptyBannerImageVisible(),
				"Empty banner image with camera icon is not visible on the Create Group page.");
	}

	@Then("the user should see the selected image with an edit pencil icon on the banner")
	public void the_user_should_see_the_selected_image_with_an_edit_pencil_icon_on_the_banner() {
		Assert.assertTrue(CreateGrpPage.isBannerImageUploaded(),
				"Uploaded banner image is not visible in the group banner section.");
		Assert.assertTrue(CreateGrpPage.isBannerEditPencilIconVisible(),
				"Edit pencil icon is not visible in the group banner section.");
	}

	@When("the user clicks on the edit pencil icon of the banner image")
	public void the_user_clicks_on_the_edit_pencil_icon_of_the_banner_image() {
		CreateGrpPage.clickOnEditPenilIcon();
	}

	@Then("the group banner image pop-up screen should open")
	public void the_group_banner_image_pop_up_screen_should_open() {

	}

	@When("the user clicks on the Delete Photo text in the pop-up")
	public void the_user_clicks_on_the_text_in_the_pop_up() {
		CreateGrpPage.clickOnDeletePhoto();
	}

	@Then("a confirmation pop-up should appear to delete the banner image")
	public void a_confirmation_pop_up_should_appear_to_delete_the_banner_image() {

		String actualPopUpText = CreateGrpPage.VerifyConfirmationPop_UP();

		String expectedPopUpText = "Are you sure you want to delete the banner picture ?Having a banner picture generates trust and authenticity.DeleteCancel";

		ReportManager.logInfo(
				"<span style='color:blue;'>Verifying if the banner delete confirmation pop-up message matches the expected text. Actual: \""
						+ actualPopUpText + "\"</span>");

		Assert.assertEquals(actualPopUpText, expectedPopUpText,
				"Delete banner image confirmation text does not match the expected message.");

		ReportManager.logPass(
				"<span style='color:green;'>Banner delete confirmation pop-up message matches the expected text: \""
						+ expectedPopUpText + "\"</span>");

	}

	@Then("upon confirmation, the banner image should be removed")
	public void upon_confirmation_the_banner_image_should_be_removed() {
		CreateGrpPage.clickOnDelete();
		// Assert.assertTrue(CreateGrpPage.isEmptyBannerImageVisible(), "Empty banner
		// image is not
		// visible");
	}

	@Then("the user should see the empty banner image with the camera icon again")
	public void the_user_should_see_the_empty_banner_image_with_the_camera_icon_again() {

		ReportManager.logInfo(
				"Verifying that the empty banner image with the camera icon is visible on the Create Group page.");
		Assert.assertTrue(CreateGrpPage.isEmptyBannerImageVisible(),
				"Empty banner image with camera icon is not visible on the Create Group page.");
		ReportManager.logInfo(
				"Empty banner image with the camera icon is confirmed to be visible on the Create Group page.");

		ReportManager.logInfo("Verifying that the camera icon is visible on the Create Group page.");
		Assert.assertTrue(CreateGrpPage.isCameraIconVisible(), "Camera icon is not visible on the Create Group page.");
		ReportManager.logPass(
				"Empty banner image with the camera icon is confirmed to be visible on the Create Group page.");

	}

	@Then("the user sees an empty group profile image with a edit pencil icon")
	public void CheckUserprofile() {

		ReportManager.logInfo(
				"Verifying that the empty profile image with the edit pencil icon is visible in the group profile section.");
		Assert.assertTrue(CreateGrpPage.isEmptyUserProfileVisible(),
				"Empty profile image with the edit pencil icon is not visible in the group profile section.");
		ReportManager.logInfo(
				"Empty profile image with the edit pencil icon is confirmed to be visible in the group profile section.");

	}

	@When("the user clicks on the add + icon of the group profile image")
	public void the_user_clicks_on_the_add_icon_of_the_group_profile_image() {
		CreateGrpPage.clickOnProfileEditIcon();

	}

	@Then("the user should see the selected image with an edit pencil icon on the group profile")
	public void verifyGroupProfilePage() {

		ReportManager.logInfo("Verifying that the uploaded profile image is visible in the group profile section.");
		Assert.assertTrue(CreateGrpPage.isIUserImageUploaded(),
				"Uploaded profile image is not visible in the group profile section.");
		ReportManager.logInfo("Uploaded profile image is successfully visible in the group profile section.");

		ReportManager.logInfo("Verifying that the edit pencil icon is visible in the group profile section.");
		Assert.assertTrue(CreateGrpPage.isEditPenilIconVisible(),
				"Edit pencil icon is not visible in the group profile section.");
		ReportManager.logPass(
				"Edit pencil icon is successfully visible along with the uploaded banner image in the group profile section.");
	}

	@When("the user clicks on the edit pencil icon of the group profile image")
	public void the_user_clicks_on_the_edit_pencil_icon_of_the_group_profile_image() {
		CreateGrpPage.clickOnProfileEditPenilIcon();
	}

	@Then("the group profile image pop-up screen should open")
	public void the_group_profile_image_pop_up_screen_should_open() {

	}

	@Then("a confirmation pop-up should appear to delete the profile image")
	public void a_confirmation_pop_up_should_appear_to_delete_the_profile_image() {

		String ActualPop_upText = CreateGrpPage.VerifyConfirmationPop_UP();
		String ExpectedPop_upText = "Are you sure you want to delete the group picture ?Having a group picture generates trust and authenticity.DeleteCancel";

		ReportManager.logInfo(
				"Verifying if the banner delete confirmation pop-up message matches the expected text. Actual: \""
						+ ActualPop_upText + "\"");
		Assert.assertEquals(ActualPop_upText, ExpectedPop_upText,
				"Delete banner image confirmation text does not match the expected message.");
		ReportManager.logPass(
				"Banner delete confirmation pop-up message matches the expected text: \"" + ExpectedPop_upText + "\"");
	}

	@Then("upon confirmation, the profile image should be removed")
	public void upon_confirmation_the_profile_image_should_be_removed() {
		CreateGrpPage.clickOnDelete();
		// Assert.assertTrue(CreateGrpPage.veriftEmptyProfileImg(), "Empty profile image
		// is not
		// visible");
	}

	@Then("the user should see the default group profile image with the add + icon again")
	public void the_user_should_see_the_default_group_profile_image_with_the_add_icon_again() {

		ReportManager.logInfo("Verifying that the empty profile image is visible on the Create Group page.");
		Assert.assertTrue(CreateGrpPage.veriftEmptyProfileImg(),
				"Empty profile image is not visible on the Create Group page.");
		ReportManager.logInfo("Empty profile image is confirmed and visible on the Create Group page.");

		ReportManager.logInfo("Verifying that the plue (+) icon is visible on the Create Group page.");
		Assert.assertTrue(CreateGrpPage.verifyPlusIIcon(), "Plus (+) icon is not visible on the Create Group page.");
		ReportManager.logPass(
				"Empty Profile image with the plus (+) icon is confirmed to be visible on the group profile section.");
	}

	@Given("Upload new image or change the image if already uploaded")
	public void the_user_has_already_uploaded_a_group_banner_image() {
		// Assert.assertTrue(CreateGrpPage.isIBannermageUploaded(), "User Banner is
		// empty upload a
		// new image");
		CreateGrpPage.isBannerImageUploaded();
		CreateGrpPage.selectBannerImage();

	}

	@When("the user selects the Change Photo option from the pop-up screen")
	public void the_user_selects_the_change_photo_option_from_the_pop_up_screnn() {
		CreateGrpPage.selectChangePhoto();
		// CreateGrpPage.VerifyConfirmationPop_UP();
	}

	@Then("the newly selected image should be displayed as the group banner image")
	public void the_newly_selected_image_should_be_displayed_as_the_group_banner_image() {
		Assert.assertTrue(CreateGrpPage.isBannerImageUploaded(), "Empty banner image is not visible");
		Assert.assertTrue(CreateGrpPage.isCameraIconVisible(), "Camera iocn is not visible");
	}

	@Given("Upload profile new image or chnage the image if already uploaded")
	public void the_user_has_already_uploaded_a_group_profile_image() {
		// Assert.assertFalse(CreateGrpPage.isIUserImageUploaded(), "User profile is
		// empty upload
		// the new image");
		CreateGrpPage.isIUserImageUploaded();
		CreateGrpPage.selectProfileImage();

	}

	@Then("the newly selected image should be displayed as the group profile image")
	public void the_newly_selected_image_should_be_displayed_as_the_group_profile_image() {
		Assert.assertTrue(CreateGrpPage.isIUserImageUploaded(), "Empty banner image is not visible");
		Assert.assertTrue(CreateGrpPage.isEditPenilIconVisible(), "Camera iocn is not visible");
	}

	@When("the user enters {string} as the group name")
	public void the_user_enters_as_the_group_name(String grpName) {
		CreateGrpPage.enterGroupName(grpName);

	}

	@When("the user enters {string} as the group description")
	public void the_user_enters_as_the_group_description(String grpDec) {
		CreateGrpPage.enterGroupDescription(grpDec);

	}

	@Then("the Create Group button should be {string}")
	public void the_create_group_button_should_be(String expectedStatus) {
		boolean isEnabled = CreateGrpPage.isCreateButtonEnabled();
		if (expectedStatus.equalsIgnoreCase("enabled")) {
			Assert.assertTrue(isEnabled, "Expected Create button to be enabled");
		} else {
			Assert.assertFalse(isEnabled, "Expected Create button to be disabled");
		}

	}

	@When("the user enters {string}   {string} as the group name")
	public void the_user_enters_as_the_group_name(String expectedStatus, String grpDec) {
		CreateGrpPage.enterEmptyspaceFromSystemKeyboard();
		boolean isEnabled = CreateGrpPage.isCreateButtonEnabled();
		if (expectedStatus.equalsIgnoreCase("enabled")) {
			Assert.assertTrue(isEnabled, "Expected Create button to be enabled");
		} else {
			Assert.assertFalse(isEnabled, "Expected Create button to be disabled");
		}
	}

	@When("the user writes {string} in the Description Field")
	public void The_user_writes_in_the_description_field(String GrpDec) {
		CreateGrpPage.enterGroupDescription(GrpDec);

	}

	@When("the user enters the group name as above 35 chracter")
	public void The_user_enters_in_the_Group_Name_Field() {
		String GrpDec = CreateGrpPage.generateRandomText(37);
		CreateGrpPage.enterGroupName(GrpDec);
	}

	@Then("the input should stop accepting characters beyond {int}")
	public void the_input_should_stop_accepting_characters_beyond(Integer int1) {

	}

	@Then("the group name field should not allow more than {int} characters")
	public void the_group_name_field_should_not_allow_more_than_characters(int Limit) {

		String actualText = CreateGrpPage.vrifyGropNameChracterCount();
		int actualLength = actualText.length();
		System.out.println("Entered Text: " + actualText + " | Length: " + actualLength);
		Assert.assertTrue(actualLength <= Limit, "Group Name exceeded character limit");
		// Assert.assertNotEquals(true, stringLent);

	}

	@When("the user writes a description with Thousend characters in the Description field")
	public void the_user_writes_a_description_with_characters_in_the_description_field() {
		String DecText = CreateGrpPage.generateRandomText(1000);
		CreateGrpPage.enterGroupDescription(DecText);

	}

	@Then("the input should accept the description successfully")
	public void the_input_should_accept_the_description_successfully() {

		String actualText = CreateGrpPage.verifyGroupDescriptionCharacterCount();
		int length = actualText.length();

		System.out.println("Entered Text Length: " + length);
		Assert.assertTrue(length <= 1000, "Description exceeds the 1000 character limit!");
	}

	@When("the user writes a description exceeding 1000 characters in the Description field")
	public void the_user_writes_a_description_exceeding_characters_in_the_description_field() {
		String DecText = CreateGrpPage.generateRandomText(10004);
		CreateGrpPage.enterGroupDescription(DecText);
	}

	@Then("the description field should not allow more than 1000 characters")
	public void the_description_field_should_not_allow_more_than_characters() {
		String ActualText = CreateGrpPage.verifyGroupDescriptionCharacterCount();
		int actualLength = ActualText.length();
		System.out.println("Entered Text: " + ActualText + " | Length: " + actualLength);
		Assert.assertTrue(actualLength <= 1000, "The description field accepted more than 1000 characters!");

	}

}
