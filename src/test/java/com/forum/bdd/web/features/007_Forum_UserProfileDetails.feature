@4um_Web
Feature: Edit User Profile Details on 4um Application

  @4um_Web_UserProfileDetails
  Scenario: TC_001 -  Edit the user profile information
    When user login with validate details on web
    And the user clicks on the profile name
    Then the user should be navigated to the user profile page
    When the user clicks on the user edit button
    And verify the user sees the edit summary page
    And the user enters the User name "John Doe" on web
    And the user enters the User designation "Software Engineer" on web
    And the user enters the User address "123 Main Street, Cityville" on web
    And the user enters the User web address "www.johndoe.dev" on web
    Then the user clicks on the save button

  Scenario: TC_002 -  Attempt to save profile without name fields
    When user login with validate details on web
    And the user clicks on the profile name
    Then the user should be navigated to the user profile page
    When the user clicks on the user edit button
    And clears the name field
    Then save button should be disabled

  Scenario: TC_003 -  Verify cancel button not saving the edited name
    When user login with validate details on web
    And the user clicks on the profile name
    Then the user should be navigated to the user profile page
    When the user clicks on the user edit button
    And the user enters the new name "Dhoni" on web
    And the user clicks on Cancel button
    And the user should be navigated to the user profile page
    Then the previously saved name should remain unchanged

  Scenario: TC_004 - Validate maximum character limits for name, designation, and address fields
    When user login with validate details on web
    And the user clicks on the profile name
    Then the user should be navigated to the user profile page
    When the user clicks on the user edit button
    And the user enters more than allowed characters in the name field
    Then an error message should be displayed for name field stating "Maximum 25 characters allowed"
    When the user enters more than allowed characters in the designation field
    Then an error message should be displayed for designation field stating "Maximum 50 characters allowed"
    When the user enters more than allowed characters in the address field
    Then an error message should be displayed for address field stating "Maximum 200 characters allowed"
