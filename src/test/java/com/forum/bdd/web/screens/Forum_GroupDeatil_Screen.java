package com.forum.bdd.web.screens;

import com.forum.bdd.ccl.WebActions;
import com.forum.bdd.integrations.report_utils.ReportManager;
import com.microsoft.playwright.Page;

/**
 * Represents the Group Detail page elements for the Forum web application. This
 * class handles interactions and verifications on the "Group Details" page This
 * class stores XPath and identifier references used for locating UI components
 * on the Group Detail page, such as group name, banner image, description,
 * tabs, etc.
 * 
 */
public class Forum_GroupDeatil_Screen {

	String GropHeading = "//div[@class='MuiBox-root css-163nafr']//p[@id='typographyWith-i18n']";
	String editPencil = "//div[@class='MuiBox-root css-70qvj9']//*[@type='button']";

	String cancelMark = "//*[@id='education-Tick-icon'][1]";
	String rightMark = "//*[@id='education-Tick-icon'][1]";
	String groupName = "";
	String descreption = "";
	String postTab = "//button[text()='Posts']";
	String postCount = "";
	String membersTab = "//button[text()='Members']";
	String membersCount = "";
	String membersList = "";
	String postDetail = "";
	String emptyPostText = "";

	String CameraIcon = "(//button[@id='iconButton-contain'])[1]";
	String BannerImage = "//label[@for='fileUploadButton']";
	String imageSave = "(//button[normalize-space()='Save'])[1]";
	String UserImage = "(//div[@class='MuiBox-root css-1l2a94u'])[1]";
	String ProfileEditIcon = "//*[name()='circle' and contains(@class,'st0')]";
	String GroupName = "//div[@class='MuiFormControl-root MuiFormControl-fullWidth MuiTextField-root css-ni8oek']//*[@type='text']";
	String GroupNameText = "//*[@data-testid='title-input']//*[@value='']";
	String Description = "//textarea[@placeholder=\"Group Description...\"]";
	String EmptyBannerImg = "//div[@class='MuiBox-root css-rfxw2']";
	String EmptyProfileImg = "//div[@class='MuiBox-root css-8254ik']";
	String DeleteText = "//button[normalize-space()='Delete Photo']";
	String ConfirmationText = "(//div[@role='presentation'])[4]";
	String editIocn = "//div[@class='MuiBox-root css-ku06rt']";
	String pencilIcon = "(//button[@id='iconButton-contain'])[2]";

	// Locator for leave group and join group

	String leaveGroup = "//p[text()='x Leave this group']";
	String joinGroup = "//p[text()='+ Join this group']";

	String leavegrouPopupHeading = "//*[@role='dialog']//*[name()='svg']//*[@stroke='#FF3434']";
	String joinGroupPopUpHeading = "//*[@role='dialog']//*[name()='svg']//*[@clip-path='url(#clip0_5763_4744)']";
	String leaveGroupSuccessMessage = "//*[text()='You have left the group']";

	WebActions play = new WebActions();
	Page page = play.getPage();

	/**
	 * Verifies whether the group detail page is visible.
	 *
	 * @return true if the group detail heading is visible, false otherwise.
	 */
	public boolean verifyGroupDetailPage() {
		return play.isVisible(GropHeading, "Verified that user successfully navigated to the group detail page");
	}

	public void clickOnLeaveGroup() {
		play.click(leaveGroup, "Successfully clicked on levae this group");
	}

	public void clcikOnJoinGroup() {
		play.click(joinGroup, "Clicked on join group");
	}

	public void navigateBack() {
		page.goBack();
	}

	public String verifyGroupLeave_JoinSuccessMsg()

	{
		page.waitForTimeout(2000);
		boolean leavegrouPopupHeadingIcon = play.isVisible(leavegrouPopupHeading, "Verifying leave group icon");
		boolean joinGrouPopupHeadingIcon = play.isVisible(joinGroupPopUpHeading, "Verifying Join group icon");

		if (leavegrouPopupHeadingIcon) {
			String successMessage = play.getText(leaveGroupSuccessMessage);
			return successMessage;
		} else if (joinGrouPopupHeadingIcon)

		{
			String successMessage = play.getText(leaveGroupSuccessMessage);
			return successMessage;
		}

		System.out.println("No success message is displayed for join group and leave group");
		return null;

	}

	public void verifyGroupDetailUIElements(String elements) {
		System.out.println("Checking the visibility for elements: " + elements);
		ReportManager.logInfo("Checking the visibility for elements: - " + elements);

		switch (elements.trim()) {
		case "Edit pencil icon":
			play.isVisible(editPencil, "Verifying Edit pencil icon is visible on group detail page");
		case "Post Tab":
			play.isVisible(postTab, "Verifying post tab is visible on group detail page");
		case "Members Tab":
			play.isVisible(membersTab, "Verifying Members Tab is visible on group detail page");
		case "x Leave this group":
			play.isVisible(membersTab, "Verifying Members Tab is visible on group detail page");
			break;
		default:
			System.out.println("Unknown element: " + elements);
			throw new IllegalArgumentException("Unknown element: " + elements);
		}
	}

	public void clickOnEditPencilBtn() {
		play.click(editPencil, "Succussfully clicked on edit pencil button");
	}

	public String clickOnRightMark() {

		boolean editbtn = play.isVisible(editPencil, "Verifying edit button is visible");
		String grpName = play.getText(GropHeading);
		if (editbtn) {
			play.click(rightMark, "Succussfully clicked on right tick mark");

		} else {
			play.click(rightMark, "Succussfully clicked on right tick mark");
		}
		return grpName;

	}

	public void clickonCancelMark() {
		play.click(cancelMark, "Succussfully clicked on Cancel tick mark");
	}

	public boolean isRightTickMarkBtnEnabled() {
		play.isVisible(rightMark, "Verifying right tick mark is visible");
		return play.isEnabled(rightMark, "Verifying the enable status of Right tick mark button");
	}

	public String groupUpdate() {
		String grpName = play.getText(GroupName);
		return grpName;
	}

	public String verifyGroupUpdate() {
		// return groupUpdate();
		String beforeText = play.getText(GropHeading);
		return beforeText;

	}

	/**
	 * Verifies and returns the text of either "Join Group" or "Leave Group" if
	 * visible.
	 * 
	 * @return The text of the visible group status ("Join Group" or "Leave Group"),
	 *         or null if neither is visible.
	 */
	public String verifyJoinAndLeaveText() {
		page.waitForTimeout(3000);

		if (play.isVisible(joinGroup, "Verified 'Join Group' text is visible")) {
			String joinGroupText = play.getText(joinGroup);
			System.out.println("Join Group text: " + joinGroupText);
			return joinGroupText;
		} else if (play.isVisible(leaveGroup, "Verified 'Leave Group' text is visible")) {
			String leaveGroupText = play.getText(leaveGroup);
			System.out.println("Leave Group text: " + leaveGroupText);
			return leaveGroupText;
		}

		System.out.println("No group-related text is visible.");
		return null;
	}

}
