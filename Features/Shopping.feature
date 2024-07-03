Feature: Product Add

  Scenario: Verify that user is able to add a product from the featured Section
    Given the user is logged in "neemo@test.com" and password as "Check@123"
    And Add the products in the Featured Section
    Then Verify the Cart and make sure all the products are added
    And Verify the Count and Price
    And Click on Checkout
    Then Remove the items from the cart which are not available



