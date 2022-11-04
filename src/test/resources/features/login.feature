Feature: Login Feature

  In order to stay up to date with new listings
  As a user of marketalertum
  I want to be able to see the 5 latest listings from scan malta laptops

  Scenario: Valid Login
    Given I am a user of marketalertum
    When I login using valid credentials
    Then I should see my alerts

  Scenario: Invalid Login
    Given I am a user of marketalertum
    When I login using invalid credentials
    Then I should see the login screen again
