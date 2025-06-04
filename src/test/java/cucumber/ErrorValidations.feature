
@tag
Feature: Error Validations
  I want to use this template for my feature file


  @ErrorValidations
  Scenario Outline: Title of your scenario outline
    Given I landed on the login page
    When Logged in with username <name> and password <password>
    Then "Incorrect Email and Password" message is displayed.
    Then I verify the <status> in step

    Examples: 
      | name  								| password  | Product Name 
      | cpatel@gmail.com		 	| Chi@123 	|	ZARA COAT 3  
