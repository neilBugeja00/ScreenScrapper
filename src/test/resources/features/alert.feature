Feature: Alert Feature

  Text

  Scenario: Uploading and viewing 3 alerts
    Given I am an administrator of the website and I upload 3 alerts
    Given I am a user of marketalertum
    When I view a list of alerts
    Then each alert should contain an icon
    And each alert should contain a heading
    And each alert should contain a description
    And each alert should contain an image
    And each alert should contain a price
    And each alert should contain a link to the original product website

  Scenario: Uploading more than 5 alerts
    Given I am an administrator of the website and I upload 6 alerts
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 5 alerts
