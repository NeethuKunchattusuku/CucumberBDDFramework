Feature: Analyse the tabs and images

  Scenario Outline:Verify each tab and check the basic testing
    Given the user is logged in "neemo@test.com" and password as "Check@123"
    And Navigate to Show All Desktops
    Then verify each tabs are present
    And navigate through each menu which has data "<Menu>" and Sort it by name
    And update the count to 100 per page
    Examples:
    |Menu|
    |Desktops    |
    |PC         |
    |Mac        |


