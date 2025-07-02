@4um_Web
Feature: Verify the Functionality of the Polling

  @4um_Create_Poll
  Scenario Outline: <TC_ID>-Verify the Poll creation with mandatory fields
    When the user logs in with valid credentials on the web application
    And the user clicks on the Create Poll option in the main menu
    Then the user should be navigated to the Create Poll page
    When the user enters a valid question "<Question>"
    And the user enters a valid first choice "<Choice one>"
    And the user enters a valid second choice "<Choice two>"
    And the user selects poll duration "<Duration>" days from the dropdown
    And the user clicks the Create button
    Then a success message confirming poll creation should be displayed

    Examples: 
      | TC_ID  | Question                         | Choice one | Choice two | Duration |
      | TC_001 | is the date the poll was created | yes        | No         |        2 |

  @4um_Create_Poll
  Scenario Outline: <TC_ID>-Verify the Poll creation with mandatory and Optional fields for <Scenario>
    When the user logs in with valid credentials on the web application
    And the user clicks on the Create Poll option in the main menu
    Then the user should be navigated to the Create Poll page
    When the user enters a valid question "<InputType>"
    And the user enters a valid first choice "<InputType>"
    And the user enters a valid second choice "<InputType>"
    And the user clicks the Add Option button
    And the user enters a valid third choice "<InputType>"
    And the user clicks the Add Option button
    And the user enters a valid fourth choice "<InputType>"
    And the user clicks the Add Option button
    And the user enters a valid fifth choice "<InputType>"
    And the user clicks the Add Description button
    And the user enters a valid Description "<InputType>"
    And the user clicks on the groups in the Share drop down
    And the user selects the group "Mind Voice" from the search result
    And the user selects poll duration "2" days from the dropdown
    And the user selects poll Category "Automotive" from the dropdown
    And the user clicks the Create button
    Then a success message confirming poll creation should be displayed

    Examples: 
      | TC_ID  | Scenario       | InputType |
      | TC_002 | Max char limit | max       |
      | TC_003 | Min Char limit | min       |

  @4um_Create_Poll @4um_Poll_Validation
  Scenario Outline: <TC_ID> -Verify validation error when the Question field <Scenario> during poll creation
    When the user logs in with valid credentials on the web application
    And the user clicks on the Create Poll option in the main menu
    Then the user should be navigated to the Create Poll page
    When the user enters an invalid question for the scenario "<Scenario>"
    And the user enters a valid first choice "Yes"
    And the user enters a valid second choice "No"
    And the user selects poll duration "2" days from the dropdown
    And the user clicks the Create button
    Then the user should see an error message "<Error Message>" below the Question field
    And the poll should not be created

    Examples: 
      | TC_ID  | Scenario                  | Error Message        |
      | TC_004 | is left empty             | Please fill question |
      | TC_005 | contains only white space | Please fill question |

  @4um_Create_Poll @4um_Poll_Validation
  Scenario Outline: <TC_ID> -Verify validation error when the Choice field <Scenario> during poll creation
    When the user logs in with valid credentials on the web application
    And the user clicks on the Create Poll option in the main menu
    Then the user should be navigated to the Create Poll page
    When the user enters a valid question "Question"
    And the user enters an invalid input in choice 1 for the "<Scenario>"
    And the user enters an invalid input in choice 2 for the "<Scenario>"
    And the user selects poll duration "2" days from the dropdown
    And the user clicks the Create button
    Then the user should see an error message "<Error Message>" below the Choice field
    And the poll should not be created

    Examples: 
      | TC_ID  | Scenario                 | Error Message              |
      | TC_006 | is left empty            | Please fill all choices    |
      | TC_007 | contains white space     | Please fill all choices    |
      | TC_008 | contains duplicate value | All options must be unique |

  @4um_Create_Poll @4um_Poll_Validation @4um_Error_Description
  Scenario Outline: <TC_ID> -Verify validation error when the Description field <Scenario> during poll creation
    When the user logs in with valid credentials on the web application
    And the user clicks on the Create Poll option in the main menu
    Then the user should be navigated to the Create Poll page
    When the user enters a valid question "Question"
    And the user enters a valid first choice "Yes"
    And the user enters a valid second choice "No"
    And the user selects poll duration "2" days from the dropdown
    And the user clicks the Add Description button
    And the user enter the invalid input in Description for the "<Scenario>"
    And the user clicks the Create button
    Then the user should see an error message "<Error Message>" below the Description field
    And the poll should not be created

    Examples: 
      | TC_ID  | Scenario             | Error Message           |
      | TC_009 | is left empty        | Please fill description |
      | TC_010 | contains white space | Please fill description |

  @4um_Create_Poll @4um_Poll_Integration
  Scenario: TC_011 - Verify the poll created in group is visible in Home page
    When the user logs in with valid credentials on the web application
    And the user clicks on the Create Poll option in the main menu
    Then the user should be navigated to the Create Poll page
    When the user creates a poll in a group "Mind Voice"  successfully
    Then the user navigates to Home page
    Then verify the user can find that poll
    And check the details in the poll

  @4um_Create_Poll @4um_Poll_Integration
  Scenario: TC_012 - Verify the poll created in group is visible in Group page
    When the user logs in with valid credentials on the web application
    And the user clicks on the Create Poll option in the main menu
    Then the user should be navigated to the Create Poll page
    When the user creates a poll in a group "Mind Voice"  successfully
    Then the user navigates to that group details page
    Then verify the user can find that poll
    And check the details in the poll

  @4um_Create_Poll @4um_Poll_Integration
  Scenario: TC_013 - Verify the poll created in group is visible in profile page of that user
    When the user logs in with valid credentials on the web application
    And the user clicks on the Create Poll option in the main menu
    Then the user should be navigated to the Create Poll page
    When the user creates a poll in a group "Mind Voice"  successfully
    Then the user navigates to his user profile page
    Then verify the user can find that poll
    And check the details in the poll

  @4um_Create_Poll @4um_Poll_Integration
  Scenario: TC_014 - Poll created with audience set to Everyone should not appear on the home page for the author
    When the user logs in with valid credentials on the web application
    And the user clicks on the Create Poll option in the main menu
    Then the user should be navigated to the Create Poll page
    When the user creates a poll to Everyone successfully
    Then the user navigates to Home page
    And The newly created poll should not be visible on the author's home page

  @4um_Create_Poll @4um_Poll_Integration
  Scenario: TC_015 - Poll created with audience set to Everyone should be displayed on the author_s profile page
    When the user logs in with valid credentials on the web application
    And the user clicks on the Create Poll option in the main menu
    Then the user should be navigated to the Create Poll page
    When the user creates a poll to Everyone successfully
    Then the user navigates to his user profile page
    Then verify the user can find that poll
    And check the details in the poll
