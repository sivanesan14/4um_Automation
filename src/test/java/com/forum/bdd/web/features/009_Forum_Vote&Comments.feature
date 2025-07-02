@4um_Web @4um_Web_Vote_Comments
Feature: Verify the Functionality of Vote the post

  @4um_Web_Vote
  Scenario: TC_001 Verify the vote functionality in the post
    When the user logs in with valid credentials on the web application
    And the user navigates to Home page
    And the user votes on the first post in the Home page
    Then the vote icon on that post should be highlighted
    And the vote count for that post should increase by one
    When the user unvote on the first post in the Home page
    Then the vote icon on that post should not be hightlighted
    And the vote count for that post should decrease by one
    And validate all assertions after execution