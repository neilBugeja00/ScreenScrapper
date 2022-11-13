Feature: Icon Feature

  Text

  Scenario: Checkin icon type 1
    Given I am an administrator of the website and I upload an alert of type 1
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 1 alerts
    And the icon displayed should be icon-car.png

  Scenario: Checkin icon type 2
    Given I am an administrator of the website and I upload an alert of type 2
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 1 alerts
    And the icon displayed should be icon-boat.png

  Scenario: Checkin icon type 3
    Given I am an administrator of the website and I upload an alert of type 3
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 1 alerts
    And the icon displayed should be icon-property-rent.png

  Scenario: Checkin icon type 4
    Given I am an administrator of the website and I upload an alert of type 4
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 1 alerts
    And the icon displayed should be icon-property-sale.png

  Scenario: Checkin icon type 5
    Given I am an administrator of the website and I upload an alert of type 5
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 1 alerts
    And the icon displayed should be icon-toys.png

  Scenario: Checkin icon type 6
    Given I am an administrator of the website and I upload an alert of type 6
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 1 alerts
    And the icon displayed should be icon-electronics.png