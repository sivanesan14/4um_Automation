@4um_Web  @4um_Web_Create_GroupPage
Feature: Verify that user can bale to create the group successfully

  Background: 
    Given the user has logged in with valid credentials
    And the user navigates to the Groups section

  #And the user clicks on the Create Group icon
  @Verify_CreateGroup_Page
  Scenario Outline: TC_001 Verify that the user can see all the element on Create Group page
    And the user clicks on the Create Group icon
    Then the "<Verify CreateGroup page Elements>" should be visible on the page

    Examples: 
      | Verify CreateGroup page Elements |
      | Group Name                       |
      | Description                      |
      | Empty Group banner image section |
      | Empty Group profile Image icon   |
      | Add Members button               |
      | Create button                    |
      | Cancel button                    |

  @Verify_Empty_banner_and_Empty_GroupProfile_Image
  Scenario: TC_002 Verify that the user can see empty banner and empty profile image on the Create Group page
    Given the user clicks on the Create Group icon
    Then the user should see the empty banner image
    And the user should see the empty group profile image

  @Upload_and_remove_group_banner_image
  Scenario: TC_003 Verify that the user can upload and remove the group banner image
    And the user clicks on the Create Group icon
    And the user sees an empty banner image with a camera icon
    When the user clicks on the camera icon of the group banner image
    Then the system gallery should open and the selected image should be displayed as the new banner image
    And the user should see the selected image with an edit pencil icon on the banner
    #When the user clicks on the edit pencil icon of the banner image
    #Then the group banner image pop-up screen should open
    When the user clicks on the Delete Photo text in the pop-up
    Then a confirmation pop-up should appear to delete the banner image
    And upon confirmation, the banner image should be removed
    And the user should see the empty banner image with the camera icon again

  @Upload_and_remove_Group_profile_image
  Scenario: TC_004 Verify that the user can upload and remove the group profile image
    Given the user clicks on the Create Group icon
    And the user sees an empty group profile image with a edit pencil icon
    When the user clicks on the add + icon of the group profile image
    Then the system gallery should open and the selected image should be displayed as the new profile image
    And the user should see the selected image with an edit pencil icon on the group profile
    #When the user clicks on the edit pencil icon of the group profile image
    #Then the group profile image pop-up screen should open
    When the user clicks on the Delete Photo text in the pop-up
    Then a confirmation pop-up should appear to delete the profile image
    And upon confirmation, the profile image should be removed
    And the user should see the default group profile image with the add + icon again

  @Change_Group_BannerImage
  Scenario: TC_005 - Verify that the user can change the group banner image
    Given the user clicks on the Create Group icon
    And Upload new image or change the image if already uploaded
    When the user clicks on the edit pencil icon of the banner image
    Then the group banner image pop-up screen should open
    When the user selects the Change Photo option from the pop-up screen
    Then the system gallery should open and the selected image should be displayed as the new banner image
    Then the newly selected image should be displayed as the group banner image

  #Upload new image or chnage the image if already uploaded
  @Change_User_ProfileImage
  Scenario: TC_006 - Verify that the user can change the group profile image
    Given the user clicks on the Create Group icon
    And Upload profile new image or chnage the image if already uploaded
    When the user clicks on the edit pencil icon of the group profile image
    Then the group profile image pop-up screen should open
    When the user selects the Change Photo option from the pop-up screen
    Then the system gallery should open and the selected image should be displayed as the new profile image
    Then the newly selected image should be displayed as the group profile image

  @Verify_CreateButton_For_mandatory_Fields
  Scenario Outline: TC_007 Verify that the Create Group button is disabled for mandatory fields
    Given the user clicks on the Create Group icon
    When the user enters "<group_name>" as the group name
    And the user enters "<description>" as the group description
    Then the Create Group button should be "<status>"

    Examples: 
      | group_name | description                     | status   |
      |            |                                 | disabled |
      |            | Some description                | disabled |
      | Group A    |                                 | disabled |
      | Group A    | Group Desc                      | enabled  |
      |            | Enter white space from keyboard | disabled |
      | 😀😀😀     | 🎉🎉🎉                          | disabled |
      | Group A    | 🎯 Group Launch                 | enabled  |
      | 🧠 DevTeam | Description here                | enabled  |

  @Create_Group_With_Members
  Scenario: TC_008 Verify that the user can create a group with members successfully
    Given the user clicks on the Create Group icon
    When the user clicks on the camera icon of the group banner image
    And the system gallery should open and the selected image should be displayed as the new banner image
    When the user click on the the add plus icon of group profile image
    And the system gallery should open and the selected image should be displayed as the new profile image
    And the user enters the group name in the Group Name field
    And the user writes "This is a automation group for study discussions" in the description field
    And the user Clicks on Add Member
    Then the user selects a member from the friends list
    And the user clicks on the Create Group button
    Then the group should be successfully created with a pop-up message

  @Create_Group_Without_Members
  Scenario: TC_009 Verify that the user can create a group without add member
    Given the user clicks on the Create Group icon
    When the user clicks on the camera icon of the group banner image
    And the system gallery should open and the selected image should be displayed as the new banner image
    When the user click on the the add plus icon of group profile image
    And the system gallery should open and the selected image should be displayed as the new profile image
    And the user enters the group name in the Group Name field
    And the user writes "This is a group for study discussions" in the description field
    And the user clicks on the Create Group button
    Then the group should be successfully created with a pop-up message

  @Verify_Duplicate_Group_Creation
  Scenario: TC_010  Verify wether user can create a duplicate groups
    Given the user clicks on the Create Group icon
    When the user clicks on the camera icon of the group banner image
    And the system gallery should open and the selected image should be displayed as the new banner image
    When the user click on the the add plus icon of group profile image
    And the system gallery should open and the selected image should be displayed as the new profile image
    And the user enters the group name in the Group Name field
    And the user writes "This is a group for study discussions" in the description field
    And the user clicks on the Create Group button
    Then the user can see the error message for the duplicate groups

  @Verify_GroupName_for_35Chracter_Length
  Scenario: TC_011 Verify that user can create a group with a 35-character length
    Given the user clicks on the Create Group icon
    When the user enters the group name in the Group Name field
    And the user writes "This is a group for study discuss" in the Description Field
    And the user clicks on the Create Group button
    Then the group should be successfully created with a pop-up message

  @Verify_GroupName_for_above_35Chracter_Length
  Scenario: TC_012 Verify that app does not accept group name longer than 35 characters
    Given the user clicks on the Create Group icon
    When the user enters the group name as above 35 chracter
    Then the input should stop accepting characters beyond 35
    And the group name field should not allow more than 35 characters

  @Verify_GroupDescription_for_1000Chracter_Length
  Scenario: TC_013 Verify that user can create a group description with 1000 character limit
    Given the user clicks on the Create Group icon
    When the user writes a description with Thousend characters in the Description field
    Then the input should accept the description successfully

  @Verify_GroupDescription_for_above_1000Character_Length
  Scenario: TC_014 Verify that the description field does not accept more than 1000 characters
    Given the user clicks on the Create Group icon
    When the user writes a description exceeding 1000 characters in the Description field
    Then the input should stop accepting characters beyond 1000
    And the description field should not allow more than 1000 characters
