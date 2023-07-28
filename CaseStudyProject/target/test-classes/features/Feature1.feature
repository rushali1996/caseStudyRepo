
Feature: Case Study Features


  Scenario: Add item to cart
    Given User is logged in to the "application"
    When user add an "Samsung galaxy s6" to cart
    Then  user validates the "Samsung galaxy s6" is present
    
     