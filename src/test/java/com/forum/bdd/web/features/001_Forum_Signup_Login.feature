@4um_Web
Feature: Verification of SignUp and Login feature for 4um application

  ############**********SignUp Functionality*******************
  #@4um_Web_Signup
  #Scenario: TC_001 - Verify the usser signup to 4um application
  #When the user enters the url for 4um application
  #And the user clicks on the create account button
  #And the user enters the email address for user creation
  #And the user enters the username for user creation
  #And the user enters the password for user creation
  #And the user enters the confirmation password for user creation
  #And the user clicks on the terms and privacy policy checkboxs
  #And the user click on the Register account button
  #Then the user enter the code
  #When the user clicks on next button
  #Then verify the user successfully register to 4um application
  #
  @4um_Web_login_Logout @4um_Web_Positive
  Scenario: TC_002 - Verify the user login and Logout to 4um application
    When the user enters the url for 4um application
    And the user enters the email address for login
    And the user enters the password for login
    When the user clicks on login next button
    Then verify the user successfully login to 4um application
    When the user logout from the 4um application
    And the user click on logout confirmation button
    Then the user logout successfully from 4um application
