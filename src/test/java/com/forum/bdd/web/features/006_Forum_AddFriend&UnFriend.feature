@4um_Web @4um_Web_FriendRequest
Feature: Verify the block feature for 4um application

  @4um_Web_UnfriendUser @4um_Web_Positive
  Scenario: TC_001 - Verify the user Unfriend another user on 4um application
    When user login with validate details on web
    And user enters another username "Shreyas Iyer" on search box on web
    And user clicks on another username from search results on web
    Then verify user navigates to another user profile on web
    When user click on unfriend button on web
    And user clicks on unfriend confirmation button on web
    And user verify the user gets "Shreyas Iyer" unfriend popup on web
    Then user verify another user is unfriend successfully

  @4um_Web_AddFriendUser @4um_Web_Positive
  Scenario: TC_002 - Verify the user send friend request to another user on 4um application
    When user login with validate details on web
    And user enters another username "Shreyas Iyer" on search box on web
    And user clicks on another username from search results on web
    Then verify user navigates to another user profile on web
    When user clicks on the Addfriend button on web
    And user verify the user gets "Shreyas Iyer" friend request popup on web

  @4um_Web_AcceptFriendRequest @4um_Web_Positive
  Scenario: TC_003 - Verify the user accept friend request on 4um application
    When user login with another user validate details on web
    And user navigate to notification option on web
    And user clicks on friend request tab on web
    And user click on Accept friend request button on web
    Then user verify the user gets "Rahul KL" accept friend request popup on web

  @4um_Web_friendUser @4um_Web_Positive
  Scenario: TC_004 - Verify the user friend request accepted user on 4um application
    When user login with validate details on web
    And user enters another username "Shreyas Iyer" on search box on web
    And user clicks on another username from search results on web
    Then verify user navigates to another user profile on web
    And verify the user is already a friend on web
