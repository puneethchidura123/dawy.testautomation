#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: SignUp Test

  Background: 
    Given I open the Dawy app
    And i am on the landing screen

  Scenario: i enter all required details and complete signup
    When i tap on the profile icon in landing page
    Then i am taken to the Sign in screen where i can see a link as Register Now
    When i tap on the Register now link
    Then i am taken to the First Signup screen where i can see a text field to enter ID
    And the ID text field is prefilled with "Your national ID / Iqama ID number"
    And a next button should be available
    When i enter National Id/Iqama
    And tap on Next button
    Then i am taken to Second Sign Up screen
    When i enter Full_Name "Full_Name"
    And i enter Email "Email"
    And i enter Phone_Number "Phone_Number"
    And i select Nationality "Nationality"
    And i select Date_of_birth "Date_of_birth"
    And i enter password "password"
    And i hit SignUp button
    Then i Should see a popup with a Success Message
    And click on ok button
