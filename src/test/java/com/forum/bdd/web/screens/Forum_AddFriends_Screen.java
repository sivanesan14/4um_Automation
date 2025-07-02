package com.forum.bdd.web.screens;

import com.forum.bdd.ccl.WebActions;

public class Forum_AddFriends_Screen {

	String UnfriendBtn = "//*[@id='button-contain'][contains(text(),'Unfriend')]";
	String UnfriendPopup = "//*[@title='Unfriend User']";
	String UnfriendConfBtn = "//*[@id='button-contain'][contains(text(),'Confirm')]";
	String DynamicUnfriendPopupMes = "//*[contains(text(),'%s has been successfully removed from your friend list.')]";
	String AddFriendBtn = "//*[@id='button-contain'][contains(text(),'Add Friend')]";
	String DynamicFriendPopupMes = "//*[contains(text(),'You have successfully sent a friend request to %s.')]";
	String NotificationMenu = "(//*[contains(text(),'Notification')])[1]";
	String FriendRequestTab = "//*[@id='items_List'][contains(text(),'Friend Request')]";
	String AcceptBtn = "//*[@id='Accept']";
	String DynamicAcceptFriendPopupMes = "//*[contains(text(),'You have accepted the friend request from %s')]";

	
	WebActions play = new WebActions();

	public void clickUnfriendButton() {
		play.waitForClick(UnfriendBtn, 10000, "Unfriend Button");
	}

	public void verifyUnfriendPopup() {
		String ActualText = play.getText(UnfriendPopup);
		play.verifyText(ActualText, "Unfriend User");
	}

	public void clickOnConfirmUnfriendBtn() {
		play.click(UnfriendConfBtn, "Unfriend Confirmation Button");
	}

	public boolean verifyUserUnfriendSuccessPopup(String value) {
		return play.waitForVisible(String.format(DynamicUnfriendPopupMes, value), 1000, "The User is Unfriend successfully");
	}

	public void verifyUserUnfriendSuccessfully() {
		play.waitForVisible(AddFriendBtn, 10000, "Add Friend Button");
	}
	
	public void clickAddFriendBtn() {
		play.click(AddFriendBtn, "Add Friend Button");
	}
	
	public boolean verifyUserFriendSuccessPopup(String value) {
		return play.waitForVisible(String.format(DynamicFriendPopupMes, value), 1000, "The User is send friend request");
	}
	
	public void clickNotificationMenu() {
		play.click(NotificationMenu, "Notification Menu");
	}
	
	public void clickFriendRequestTab() {
		play.waitForClick(FriendRequestTab, 1000, "Friend Request Tab");
	}
	
	public void clickAcceptButton() {
		play.waitForClick(AcceptBtn, 1000, "Accept Button");
	}
	
	public boolean verifyUserAcceptFriendReqSuccessPopup(String value) {
		return play.waitForVisible(String.format(DynamicAcceptFriendPopupMes, value), 10000, "The User Accept friend request");
	}
	
	public boolean verifyUserAlreadyFriend() {
		return play.waitForVisible(UnfriendBtn, 10000, "The User is already Accepted the friend request");
	}
}
