
@tag
Feature: Purchase the order from the Ecommerce Website.
  I want to use this template for my feature file

Background: 
Given I landed on the login page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <Product Name> to Cart
    And Checkout <Product Name> and submit the order
    Then VERIFY <confirmation page> is displayed

    Examples: 
      | name  								| password  | Product Name | confirmation 
      | cpatel@gmail.com		 	| Chin@123 |	ZARA COAT 3 | THANK YOU FOR THE ORDER

      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      