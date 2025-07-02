@4um_Web @4um_Web_Chat_Functionality
Feature: Test the functionality of the 4um chat implementation

  Background: 
    Given the user has logged in with valid credentials
    And the user navigates to the Chat Page

  @Chat_Access
  Scenario: TC_001 Verify that the chat feature is visible on the right-side menu
    #Given the user is on the 4um timeline page
    Then the user should see the chat feature enabled on the right-side menu
    When the user clicks on the chat feature
    Then the user should be navigated to the chat page

  @Chat_UI
  Scenario Outline: <TC_ID> Verify the UI elements displayed on the chat page
    Given the user is on the chat page
    Then the user should see the "<Element>" on the chat page

    Examples: 
      | TC_ID  | Element             |
      | TC_002 | Friends_Tab         |
      | TC_003 | Groups_Tab          |
      | TC_004 | Create Message Icon |
      | TC_005 | Search Input Field  |

  @Send_Message_To_Friend
  Scenario: TC_006 Verify that the user can send a message to a friend by searching their name
    Given the user is on the chat page
    When the user enters a friend's name in the search input field
    Then the that friend name should be displayed in the search results
    When the user selects the name from the search results
    Then the user should be navigated to the chat window with that friend
    When the user types a message in the Type your message… text box and sends it
    Then the message should appear in the chat window
    And the message status e.g Sent, Delivered, or Read should be displayed correctly

  @Send_Message_To_Not_Friend
  Scenario: TC_007 Verify that the user cannot send messages to another user who is not a friend
    Given the user is on the chat page
    When the user enters a name in the search input field
    Then the that name should be displayed in the search results
    When the user selects the name from the search results
    Then the user should be navigated to the Profile page
    And the user should see an option to send a friend request to start a conversation

  @Send_Message_From_Friends_Tab
  Scenario: TC_008 Verify by sending messages to a friend from friends tab
    Given the user is on the chat page
    And the user selects any friend from the Friends_tab
    Then the user should be navigated to the chat window with selected Friend
    When the user types a message in the Type your message… text box and sends it
    Then the message should appear in the chat window
    And the message status e.g Sent, Delivered, or Read should be displayed correctly

  @Send_Message_Via_Profile
  Scenario: TC_009 Verify that the user can send a message to friend via the user profile page
    Given the user is on the Home page
    When the user clicks on their profile name from the left side menu
    Then the user should be navigated to the Profile page
    When the user clicks on the Friends section
    Then the user should be navigated to the Friend List page
    And the user should see a chat icon next to each friend
    When the user selects any friend from the list or clicks on chat icon
    Then the user should be navigated to the chat window with selected Friend
    When the user types a message in the Type your message… text box and sends it
    Then the message should appear in the chat window
    And the message status e.g Sent, Delivered, or Read should be displayed correctly

  ######################################################### User Groups ##################################################################
  @Create_Group_Chat_From_Create_Message
  Scenario: TC_010 Verify that user can create a group chat using the Create Message option
    Given the user is on the chat page
    And the user clicks on the Create Message icon
    When the user enters the friend's name in the To input field section
    #Then the that friend name should be displayed in the search results
    And upon selecting the friend's name, it should be displayed in the To input field tab
    When the user enters the text message in the "Write your message…" box
    And the user clicks on the Create button
    Then the user should be navigated to the chat window with the group members
    Then the message should appear in the chat window

  @Chat_Create_Meassage_UI
  Scenario Outline: TC_011 Verify the UI elements displayed on the Create message pop_screen
    Given the user is on the chat page
    And the user clicks on the Create Message icon
    Then the user should see the "<Elements>" on the Create Message pop-up

    Examples: 
      | Elements               |
      | Create message heading |
      | Pop_Up Cancel button   |
      | Search input box       |
      | To: text               |
      | Chracter Limit         |
      | Send button            |
      | Cancel button          |

  @verify_Error_Msg
  Scenario: TC_012 Verify whether the user can add more than 7 members to a user group (i.e., the user should get a warning message)
    Given the user is on the chat page
    And the user clicks on the Create Message icon
    When the user enters the more then 7 members in the To input field section
    #Then the that name should be displayed in the search results
    And upon selecting the friend's name, it should be displayed in the To input field tab
    Then the user should see a warning message stating "You already added 7 members to the conversation. Adding more members is not allowed."

  #@Chat_WindowPage_UI
  #Scenario Outline: TC_009 Verify the UI elements displayed on chat window
  #Given the user is on the chat page
  #And the user selects any friend from the Friends_tab
  #Then the user should see the "<Element>" on chat window
  #
  #Examples:
  #| Element               |
  #| User Profile Name     |
  #| Hamburger Menu option |
  #| Add user Icon         |
  #| Chracter text Limit   |
  #| Emoji Icon            |
  #@TwoUserMessageExchange
  #Scenario: TC_010 Verify message exchange between two users friends
  #Given the user is on the chat page
  #When User A enters "User B" in the search input field
  #And User A selects the name from the search results
  #Then User A should be navigated to the chat window with User B
  #When User A sends a message to User B
  #Then the message should appear in the chat window
  #And the message status for User A e.g Sent, Delivered, or Read should be displayed correctly
  #And start conversation in edge with another user
  #Then the message status for User A should update to a double tick mark if the User B is Online
  #When User B receives the message from User A on chat page with unread message count
  #Then User B open User A message from chat window
  #Then the recent message should be visible on the User B Chat window
  #And User B replies to User A’s message
  #Then the reply from User B should appear in the same chat window thread
  #When User A receives the reply from User B
  #Then the message status for User B e.g. Sent, Delivered, or Read should be displayed correctly
  @Chat_Block_User_Function
  Scenario: TC_013 Verify that a user send or receive messages after block/Unblock a friend
    Given the user is on the chat page
    When the user enters a friend's name in the search input field
    Then the that friend name should be displayed in the search results
    When the user selects the name from the search results
    Then the user should be navigated to the chat window with that friend
    When the user clicks on the "Block" option from the Hamburger Menu
    Then a confirmation popup message "Are you sure you want to block [UserName]?" should appear
    And the user confirms the block action
    Then a success popup message "You have successfully blocked [UserName]" should be displayed
    #Then the chat input option should be disabled for the user
    And the message "4um member unable to receive messages." should be visible on the chat window page
    When the user clicks on the "Unblock" option from the Hamburger Menu
    Then a confirmation popup message "Are you sure you want to unblock [UserName]?" should appear
    And the user confirms the unblock action
    Then a success popup message "You have successfully unblocked [UserName]" should be displayed
    Then the chat input option should be disabled for the user

  @Reaction_To_Message
  Scenario: TC_014 Verify that a user react to sent or received messages after block and unblock a friend
    Given the user is on the chat page
    And the user selects any friend from the Friends_tab
    Then the user should be navigated to the chat window with selected Friend
    When the user clicks on the "Block" option from the Hamburger Menu
    Then a confirmation popup message "Are you sure you want to block [UserName]?" should appear
    And the user confirms the block action
    Then a success popup message "You have successfully blocked [UserName]" should be displayed
    When the user mouse over on a previously sent message
    Then the reaction options should not be available
    When the user mous over on a previously received message
    Then the reaction options should not be available
    #And User should see the delete icon on each message
    When the user clicks on the "Unblock" option from the Hamburger Menu
    Then a confirmation popup message "Are you sure you want to unblock [UserName]?" should appear
    And the user confirms the unblock action
    Then a success popup message "You have successfully unblocked [UserName]" should be displayed
    When the user mouse over on a previously sent message
    Then the reaction options should be available
    When the user mous over on a previously received message
    Then the reaction options should be available

  #And User should see the emoji plate on each message
  @Delete_Entire_Chat
  Scenario: TC_015 Verify by deleting entire conversation using the hamburger menu
    Given the user is on the chat page
    And the user selects any friend from the Friends_tab
    Then the user should be navigated to the chat window with selected Friend
    When the user clicks on the "Delete Chat" option from the Hamburger Menu
    Then a confirmation popup message "Are you sure you want to delete the conversation?" should appear
    And the user clicks on the delete button
    Then the all message should be deleted
    And user should navigated to chat page

  @Report_User_From_Chat
  Scenario: TC_016 Verify that a user can report another user from the chat window using the Hamburger Menu
    Given the user is on the chat page
    When the user enters a friend's name in the search input field
    Then the that friend name should be displayed in the search results
    When the user selects the name from the search results
    Then the user should be navigated to the chat window with that friend
    When the user clicks on the "Report" option from the Hamburger Menu
    Then a Report Details popup screen should appear
    And the user selects a reason from the dropdown menu
    And the user enters a description in the input field
    When the user clicks on the Send button
    Then a success popup message "Your report has been submitted. Our Admin will review and take appropriate action." should be displayed
    And the Report option should be disabled to prevent duplicate reporting

  ####################################################### 4um Groups ###########f########################################################
  @Send_MessageTo_4umGroup
  Scenario: TC_017 Verify that the user can send a message to a 4um group they are part of by searching for it
    Given the user is on the chat page
    When the user enters a group's name in the search input field
    Then the that group name should be displayed in the search results
    When the user selects the group from the search results
    Then the user should be navigated to the chat window with that group
    When the user types a message in the Type your message… text box and sends it
    Then the message should appear in the chat window
    And the message status e.g Sent, Delivered, or Read should be displayed correctly

  @Send_Message_To_4umGroup_Not_Part
  Scenario: TC_018 Verify that a user cannot send messages to a 4um group they are not a member of
    Given the user is on the chat page
    When the user enters group's name in the search input field
    Then the that group name should be displayed in the search results
    When the user selects the group from the search results
    Then the user should be navigated to the group detail Profile page
    And the user should see a "+ Join this group" option to start a conversation

  @Send_Message_To_4umGroups_From_Groups_Tab
  Scenario: TC_019 Verify by sending messages to a 4um groups from groups tab
    Given the user is on the chat page
    When user select the Groups tab from chat window page
    Then the user selects any group's from the Groups_tab
    Then the user should be navigated to the chat window with that Group
    When the user types a message in the Type your message… text box and sends it
    Then the message should appear in the chat window
    And the message status e.g Sent, Delivered, or Read should be displayed correctly

  @Send_Message_To_4umGroups_Via_ProfilePage
  Scenario: TC_020 Verify that the user can send a message to 4um groups via the user profile page
    Given the user is on the Home page
    When the user clicks on their profile name from the left side menu
    Then the user should be navigated to the Profile page
    When the user clicks on the Groups section
    Then the user should be navigated to the Groups List page
    And the user should see a chat icon next to each group
    When the user selects any Group from the list or clicks on chat icon
    Then the user should be navigated to the chat window with that Group
    When the user types a message in the Type your message… text box and sends it
    Then the message should appear in the chat window
    And the message status e.g Sent, Delivered, or Read should be displayed correctly

  #@Leave4um_Group
  #Scenario: TC_011 Verify that a user can leave the group from hamburger menu
  #Given the user is on the chat page
  #When user select the Groups tab from chat window page
  #Then the user selects any group's from the Groups_tab
  #Then the user should be navigated to the chat window with that Group
  #When the user clicks on the "Leave Group" option from the Hamburger Menu
  #Then a confirmation popup message "Are you sure you want to Leave the Group?" should appear
  #And the user click on leave button
  #And the user should be navigated to the chat page
  #Then that group should not be visible on Groups tab
  @Leave4um_Group @Send_Message
  Scenario: TC_021 Verify that a user send or receive messages after leave a group via group chat window page
    Given the user is on the chat page
    When user select the Groups tab from chat window page
    Then the user selects any group's from the Groups_tab
    Then the user should be navigated to the chat window with that Group
    When user clicks on the group profile or group name
    Then the user navigates to the selected group detail page
    When the user clicks on the Leave This Group button from the group detail page
    Then a success popup message "You have left the group" should appear
    And the user Navigate back to group chat window
    Then User should see the "Join the 4um Group to resume the conversation." on the the chat window

  @Max_Message_Length @Input_Limit_Validation
  Scenario: TC_022 Verify whether the user can send more than 1900 characters (text + emoji) as a message in a 4um group
    Given the user is on the chat page
    When user select the Groups tab from chat window page
    Then the user selects any group's from the Groups_tab
    Then the user should be navigated to the chat window with that Group
    When the user types a message with more than 1900 characters in the Type your message… text box
    Then the user should see an "Input Limit Reached" warning message displayed below the input box

  Scenario: Verify by reacting to a friends messages from emoij popup when the user mouse over the message

  Scenario: Verify by deleting entire conversation using the hamburger menu
