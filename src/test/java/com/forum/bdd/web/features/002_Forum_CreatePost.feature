@4um_Web @4um_Web_CreatePost
Feature: Verification of Create Post feature for 4um application

  @4um_Web_CreatePost @4um_Web_Positive
  Scenario: TC_001 - Verify the user create a post on 4um application
    When user login with validate details on web
    And the user click on the create post options on web
    Then the user should be navigated to the Create Post page
    When the user enters the post title on web
    And the user enters the post description on web
    And the user enters the group name for post creation on web
    And the user upload the media files for post creation on web
    And the user click on the Create post button on web
    Then the user verify the post created successfully on web

  @4um_Web_CreatePost @4um_Web_Positive
  Scenario Outline: <TC_ID> - Verify the user create a post with <Scenario> description on 4um application
    When user login with validate details on web
    And the user click on the create post options on web
    Then the user should be navigated to the Create Post page
    When the user enters the post title on web
    And the user enters the post description "<InputType>" on web
    And the user enters the group name for post creation on web
    #And the user upload the media files for post creation on web
    And the user click on the Create post button on web
    Then the user verify the post created successfully on web

    Examples: 
      | TC_ID  | Scenario       | InputType |
      | TC_002 | Max char limit | max       |
      | TC_003 | Min Char limit | min       |

  @4um_Web_Delete @4um_Web_Positive
  Scenario: TC_004 - Verify the user delete a post/poll in user profile page on 4um application
    When user login with validate details on web
    And user navigate to user profile page on web
    And user click on user "Rahul KL" post hamburger menu on web
    And user click on the delete post option on web
    And user click on delete post confirmation button on web
    Then user verify the post deleted successfully on web

  @4um_Web_CreatePostwithMention @4um_Web_Positive
  Scenario: TC_005 - Verify the user create a post with User Mention on 4um application
    When user login with validate details on web
    And the user click on the create post options on web
    Then the user should be navigated to the Create Post page
    When the user enters the post title on web
    And the user enters the post description with user mention on web
    And the user enters the group name for post creation on web
    #And the user upload the media files for post creation on web
    And the user click on the Create post button on web
    Then the user verify the post created successfully on web

  @4um_Web_Delete @4um_Web_Positive
  Scenario: TC_006 - Verify the user delete a post/poll in timeline page on 4um application
    When user login with validate details on web
    And user click on user "Rahul KL" post hamburger menu on web
    And user click on the delete post option on web
    And user click on delete post confirmation button on web
    Then user verify the post deleted successfully on web

  @4um_Web_CreatePostwithoutTitle @4um_Web_Positive
  Scenario: TC_007 - Verify the user create a post without Title on 4um application
    When user login with validate details on web
    And the user click on the create post options on web
    And the user enters the post description on web
    And the user upload the media files for post creation on web
    And the user click on the Create post button on web
    Then the user verify the post created successfully on web

  @4um_Web_CreatePostWithoutdescription @4um_Web_Negative
  Scenario Outline: <TC_ID> - Verify the user create a post <Scenario> on 4um application
    When user login with validate details on web
    And the user click on the create post options on web
    And the user enters the post title "<Title>" on web
    And the user enters the invalid post description on web "<Scenario>"
    And the user upload the media files for post creation on web
    And the user click on the Create post button on web
    Then the user verify no description error message "<Error Message>" for validation
    And the post should not created

    Examples: 
      | TC_ID  | Scenario                       | Title               | Error Message          |
      | TC_008 | is left empty                  | wihtout description | Please fill this field |
      | TC_009 | only contains only white space | wihtout description | Please fill this field |

  @4um_Web_CreatePostwithMoreFiles @4um_Web_Negative
  Scenario Outline: <TC_ID> - Verify the user create a post <Scenario> on 4um application
    When user login with validate details on web
    And the user click on the create post options on web
    And the user enters the post title "<Title>" on web
    And the user enters the post description "<Description>" on web
    And the user upload the more media files for post creation on web
    And the user click on the Create post button on web
    Then the user verify more media file error message "<Error Message>" for validation
    And the post should not created

    Examples: 
      | TC_ID  | Scenario                      | Title                 | Description      | Error Message                            |
      | TC_010 | With More than 12 media Files | with More media Files | More media Files | The Upload Media cannot exceed 12 files. |

  @4um_Web_CreatePostwithMoreDescription @4um_Web_Negative
  Scenario: - TC_0011 - Verify the user create a post more than 2500 char Description on 4um application
    When user login with validate details on web
    And the user click on the create post options on web
    And the user enters the post title on web
    And the user enters the post description more than limit on web
    Then the user verify more description error message for validation
    And the post should not created

  @4um_Web_CreatePostwithMoreTitle @4um_Web_Negative
  Scenario: - TC_0012 - Verify the user create a post more than 200 char Title on 4um application
    When user login with validate details on web
    And the user click on the create post options on web
    And the user enters the post title more than limit on web
    Then the user verify more Title error message for validation
    And the post should not created
