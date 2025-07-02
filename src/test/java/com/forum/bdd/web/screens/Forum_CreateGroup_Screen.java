package com.forum.bdd.web.screens;

import com.forum.bdd.ccl.WebActions;
import com.forum.bdd.integrations.common_utils.RandomGenerator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class Forum_CreateGroup_Screen {

	String EmptyBannerImg = "//div[@class='MuiBox-root css-rfxw2']";
	String EmptyProfileImg = "//div[@class='MuiBox-root css-8254ik']";
	String createGroup = "//p[@title='Create Group']";
	String navigateBack = "";
	String CameraIcon = "(//button[@id='iconButton-contain'])[1]";
	String BannerImage = "//label[@for='fileUploadButton']";
	String imageSave = "(//button[normalize-space()='Save'])[1]";
	String UserImage = "(//div[@class='MuiBox-root css-1l2a94u'])[1]";
	String ProfileEditIcon = "//*[name()='circle' and contains(@class,'st0')]";
	String GroupName = "//div[@data-testid='title-input']//input[@id='title-input']";
	String GroupNameText = "//*[@data-testid='title-input']//*[@value='']";
	String Description = "//textarea[@placeholder=\"Group Description...\"]";
	String AddMember = "//*[@title='Add Members']";
	String MembersList = "//div[@id='friendsList_content']";
	String selectMember = "//*[@class='infinite-scroll-component__outerdiv']//*[@data-testid='iconButton-contain']";
	String selectMemberXPath = "//*[@class='flexCenter MuiBox-root css-11mzvaq']//*[@data-testid='iconButton-contain']";
	String DeleteText = "//button[normalize-space()='Delete Photo']";
	String clickCreateGroup = "(//button[normalize-space()='Create'])[1]";
	String PopupMessage = "//div[@role='dialog']//p[@data-testid='typographyWith-i18n']";
	String scrollToElement = "//div[@id='scrollToElement']";
	String ConfirmationText = "(//div[@role='presentation'])[4]";
	String editIocn = "//div[@class='MuiBox-root css-ku06rt']";
	String pencilIcon = "(//button[@id='iconButton-contain'])[2]";

	WebActions play = new WebActions();
	Page page = play.getPage();

	public boolean userIsOnCreateGroupsPage(String elementName) {
		System.out.println("Checking visibility for element: " + elementName);
		Locator locator;

		switch (elementName.trim()) {
		case "Group Name":
			locator = page.locator("//p[@id='group-name']"); // use actual selector
			break;
		case "Description":
			locator = page.locator("//p[@id='desPost']");
			break;
		case "Empty Group banner image section":
			locator = page.locator("//div[@class='MuiBox-root css-rfxw2']");
			break;
		case "Empty Group profile Image icon":
			locator = page.locator("//*[@class='MuiBox-root css-8254ik']");
			break;
		case "Add Members button":
			locator = page.locator("//*[@title='Add Members']");
			break;
		case "Create button":
			locator = page.locator("(//button[normalize-space()='Create'])[1]");
			break;
		case "Cancel button":
			locator = page.locator("//button[2]");
			break;
		default:
			System.err.println("Unknown element: " + elementName);
			throw new IllegalArgumentException("Unknown element: " + elementName);
		}

		try {
			locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
			boolean visible = locator.isVisible();
			System.out.println("Is visible: " + visible);
			return visible;
		} catch (Exception e) {
			System.err.println("Error waiting for element: " + e.getMessage());
			return false;
		}
	}

	public boolean userIsOnCreateGroupScreen() {
		boolean createGrpText = play.isVisible(createGroup, "Checking create group page heading");
		if (createGrpText == true) {
			System.out.println("Successfully navigated to create group page");

		} else {
			System.out.println("Create group page not visible");
		}
		return createGrpText;

	}

	public String verifyEmptyBGBannerColor() {

		// Locate the banner element using its unique class
		Locator banner = page.locator("//div[@class='MuiBox-root css-rfxw2']");

		// Get the computed background color (returns RGBA)
		String bgColor = banner.evaluate("ele => getComputedStyle(ele).backgroundColor").toString();
		System.out.println("Background color is: " + bgColor);

		// Check if it contains the RGB values for #757575 (which is rgb(117, 117, 117))
		if (bgColor.contains("93, 110, 110")) {
			System.out.println("✅ Empty Banner Background color coode for gray is #757575 as expected");
		} else {
			System.out.println("❌ Unexpected Empty Banner Background color coode for gray is: " + bgColor);
		}
		return bgColor;
	}

	public String verifyEmptyBGProfileColor() {

		// Locate the banner element using its unique class
		Locator banner = page.locator("//div[@class='MuiBox-root css-8254ik']");

		// Get the computed background color (returns RGBA)
		String bgColor = banner.evaluate("ele => getComputedStyle(ele).backgroundColor").toString();
		System.out.println("Background color is: " + bgColor);

		// Check if it contains the RGB values for #757575 (which is rgb(117, 117, 117))
		if (bgColor.contains("2, 131, 131")) {
			System.out.println("✅ Background color for profile as expected");
		} else {
			System.out.println("❌ Unexpected background color for profile: " + bgColor);
		}
		return bgColor;

	}

	public void clickOnGroupBannerCameraIcon() {
		play.click(CameraIcon, "Clicked on Camera icon");
	}

	public void selectBannerImage() {
		play.waitForUpload(BannerImage, System.getProperty("user.dir") + "\\src\\test\\resources\\Images\\peacock.jpg",
				"Uploaded File");
		play.click(imageSave, "Image saved");
	}

	public void clickOnProfileEditIcon() {
		play.click(ProfileEditIcon, "Clicked on profile Add(+) Icon");
	}

	public void selectProfileImage() {
		play.waitForUpload(BannerImage, System.getProperty("user.dir") + "\\src\\test\\resources\\Images\\peacock.jpg",
				"Uploaded File");
		play.click(imageSave, "Clicked on save button");
	}

	public void enterGroupName(String GrpName) {
		play.clear(GroupName, "Clread");
		play.enterText(GroupName, GrpName, " Group name entred");
	}

	public void enterGroupDescription(String description) {
		play.sendKeys(Description, description, " Text in the Group name input description");
	}

	public void tapAddMember() {
		play.click(AddMember, "Clicked on add member");

	}

	public void selectMember() {
		play.click(selectMember, "member selected");
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clickOnCreateGroup() {
		play.click(clickCreateGroup, "Clicked on Create button");
	}

	public String verifyGroupCreationPopup() {
		String duplicateError = verifyDuplicateErrorMessage();

		if (duplicateError != null && duplicateError.equals("Duplicate group names are not allowed.")) {
//	        System.out.println("❌ Duplicate groups are found and cannot proceed with group creation");
//	        Assert.assertTrue(true); // Assert known error scenario occurred
			return duplicateError; // Exit early and skip the popup logic
		}

		// If no duplicate error, continue to fetch popup text
		String popupLocator = "//div[@role='dialog']//p[@data-testid='typographyWith-i18n']";
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String popupText = play.getText(popupLocator);
		System.out.println("✅ Group created successfully. Popup message: " + popupText);
		return popupText;
	}

	public String verifyDuplicateErrorMessage() {
		String errormsg = "//div[@class='MuiBox-root css-5eewlp']/p[@id='typographyWith-i18n']";
		return play.getText(errormsg);

	}

	public boolean veriftEmptyBannerImg() {
		return play.isVisible(EmptyBannerImg, "Checking Empty Banner image");

	}

	public boolean veriftEmptyProfileImg() {

		return play.isVisible(EmptyProfileImg, "Group profile section with edit iconis dislyed");

	}

	public boolean verifyPlusIIcon() {

		return play.isVisible(ProfileEditIcon, "Group profile section with edit icon is dislyed");

	}

	public String passMandatoryFields(String MandatoryFields) {

		return MandatoryFields;

	}

	public boolean isCreateButtonEnabled()

	{
		play.isVisible(clickCreateGroup, "Create button is displayed on the page");
		return play.isEnabled(clickCreateGroup, "Checked status of create button");

	}

	public void enterEmptyspaceFromSystemKeyboard() {
		System.out.println("wait for 10sec to enter the space from keyboard");
		try {
			play.wait(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String vrifyGropNameChracterCount() {

		Locator input = page.locator("[data-testid='title-input'] input");

		String value = input.getAttribute("value");
		play.clear(GroupName, "Text clread");
		play.enterText(GroupName, "Study Group for automation", "2nd data");
		System.out.println("The value is: " + value);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	public String verifyGroupDescriptionCharacterCount() {

		play.isDisplayed(Description, "Checking group description character length");
		page.waitForTimeout(3000);
		String descriptionText = play.getText(Description);

		if (descriptionText.length() <= 1000) {
			System.out.println("\n========== Group Description Text ==========");
			System.out.println(descriptionText);
			System.out.println("============================================");
			System.out.println("Total characters entered: " + descriptionText.length());
			System.out.println("============================================\n");
		} else {
			System.out.println("❌ Description exceeds the 1000 character limit!");
			System.out.println("Current Length: " + descriptionText.length());
		}

		return descriptionText;
	}

	public boolean isEmptyBannerImageVisible() {
		boolean isVisible = play.isVisible(EmptyBannerImg, "Checking Empty Banner Image");
		if (isVisible == true) {
			System.out.println("Empty banner image is visible");

		} else {
			System.out.println("Empty banner image is not visible");
		}

		return isVisible;
		// return play.isVisible(EmptyBannerImg, "Empty Banner is visible");

	}

	public void clickOnDelete() {
		String delete = "//button[normalize-space()='Delete']";
		play.click(delete, "Delete pop_up accepected");
	}

	public boolean isCameraIconVisible() {
		return play.isVisible(CameraIcon, "Camera iocn is visible");

	}

	public boolean isBannerImageUploaded() {
		String BannerIamge = "//img[@alt='img-profile']";

		// boolean flag=false;
		boolean isVisible = play.isVisible(BannerIamge, "Checking image avalibality on banner Section");
		if (isVisible) {

			System.out.println("Image is visible on banner image");
			play.click(editIocn, "Clicked on edit penciel button");

		} else {

			System.out.println("Image is not visible on banner image");
			play.click(CameraIcon, "Clicked on camera icon");
		}

		return isVisible;

	}

	public boolean isBannerEditPencilIconVisible() {
		String editIocn = "//div[@class='MuiBox-root css-ku06rt']";
		boolean isVisible = play.isVisible(editIocn, "Edit pencil iocn is visible on banner image");
		if (isVisible == true) {
			System.out.println("Edit pencil iocn is visible on banner image");

		} else {
			System.out.println("Edit pencil iocn is not visible on banner image");
		}

		return isVisible;

	}

	public void clickOnEditPenilIcon() {

		play.click(CameraIcon, "Clicked on edit pencil button");

	}

	public void clickOnDeletePhoto() {
		play.click(DeleteText, "Clicked on delete text");
	}

	public String VerifyConfirmationPop_UP() {
		String Text = play.getText(ConfirmationText);
		return Text;

	}

	public boolean isIUserImageUploaded() {
		String uderImg = "//img[@id='crate']";
		String EditImg = "//img[@id='userAvatar']";

		boolean isVisible = play.isVisible(uderImg, "Checking image visibility on profile section");
		boolean isVisible2 = play.isVisible(EditImg, "Checking image visibility on profile section");
		if (isVisible) {

			System.out.println("Profile image is visible on profile section");
			play.click(pencilIcon, "Clicked on edit pincel button");

		} else if (isVisible2)

			System.out.println("Upload image is not visible on Profile section");

		// play.click(pencilIcon, "Clicked on edit pincel button");
		play.click(ProfileEditIcon, "Clicked on edit pincel button");
		return isVisible2 || isVisible;

	}

	public boolean isEditPenilIconVisible() {
		String pencilIcon = "(//button[@id='iconButton-contain'])[2]";
		boolean isVisible = play.isVisible(pencilIcon, "Edit pencil iocn is visible on user image");
		if (isVisible == true) {
			System.out.println("Edit pencil iocn is visible on user image");

		} else {
			System.out.println("Edit pencil iocn is not visible on user image");
		}

		return isVisible;

	}

	public void clickOnProfileEditPenilIcon() {

		String pencilIcon = "(//button[@id='iconButton-contain'])[2]";
		play.click(pencilIcon, "Clicked on edit pencil button");

	}

	public boolean isEmptyUserProfileVisible() {
		String profilImage = "//div[@class='MuiBox-root css-8254ik']//*[name()='svg']";
		boolean isVisible = play.isVisible(profilImage, "Checking Empty group profile image");
		if (isVisible == true) {
			System.out.println("Empty user profile image is visible");

		} else {
			System.out.println("Empty user profile image is not visible");
		}

		return isVisible;
		// return play.isVisible(EmptyBannerImg, "Empty Banner is visible");

	}

	public void selectChangePhoto() {
		String chnagePhoto = "//button[normalize-space()='Change Photo']";
		play.click(chnagePhoto, "Phot has been changed");
	}

	public String generateRandomText(int count) {
//		return WebActions.generateRandomText(count);
		return RandomGenerator.randomString(count);
	}

}
