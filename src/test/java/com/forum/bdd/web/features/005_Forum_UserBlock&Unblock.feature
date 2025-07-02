@4um_Web
Feature: Verify the block feature for 4um application

  @4um_Web_BlockUser @4um_Web_Positive
  Scenario: TC_001 - Verify the user blocks another user on 4um application
    When user login with validate details on web
    And user enters another username "Suryaputra" on search box on web
    And user clicks on another username from search results on web
    Then verify user navigates to another user profile on web
    When user clicks on post tab of user profile on web
    And user click on user post hamburger menu on web
    And user click on block options on web
    And user click on block confirmation button on web
    Then user verify the user gets "Suryaputra" blocked popup on web
    And user confirm another user blocked successfully

  @4um_Web_BlockUser @4um_Web_Positive
  Scenario: TC_002 - Verify that the blocked user cannot mention in post description on 4um application
    When user login with validate details on web
    And the user click on the create post options on web
    And the user should be navigated to the Create Post page
    And the user enters the user mention "Suryaputra" on web
    Then verify that blocked user cannot mentioned in post

  @4um_Web_BlockUser @4um_Web_Positive
  Scenario: TC_003 - Verify that the user cannot see post and friendlist of blocked user on 4um application
    When user login with validate details on web
    And user enters another username "Suryaputra" on search box on web
    And user clicks on another username from search results on web
    Then verify user navigates to another user profile on web
    When user clicks on post tab of user profile on web
    Then verify user cannot see any blocked user post
    When user clicks on Friend list option on web
    Then verify the friend list details on web

  @4um_Web_BlockUser @4um_Web_Positive
  Scenario: TC_004 - Verify the user unblock the blocked user on 4um application
    When user login with validate details on web
    And user enters another username "Suryaputra" on search box on web
    And user clicks on another username from search results on web
    Then verify user navigates to another user profile on web
    When user clicks on unblock button on web
    And user click on unblock confirmation button on web
    Then user verify the user gets "Suryaputra" unblocked popup on web
    When user clicks on post tab of user profile on web
    And verify user can see user post on web
    Then user can see the Unfriend button on web
    When user clicks on Friend list option on web
    Then verify the friend list details on web
