Feature: Login with Valid Credentials

  @sanity @regression
  Scenario: Successful Login with Valid Credentials
    Given the user navigates to login page
    When user enters email as "neemo@test.com" and password as "Check@123"
    And the user clicks on the Login button
    Then the user should be redirected to the MyAccount Page

@regression
  Scenario Outline: Login Data Driven
    Given the user navigates to login page
    When user enters email as "<email>" and password as "<password>"
    And the user clicks on the Login button
    Then the user should be redirected to the MyAccount Page

    Examples:
      | email                	    | password |
      | neemo@test.com	      | Check@123  |



  @regression
  Scenario: Verify the count of sliding images
    Given the user navigates to login page
    And verify the number of images present in that grid and display the counts


  Scenario: Login to the page
    Given the user is logged in "neemo@test.com" and password as "Check@123"


