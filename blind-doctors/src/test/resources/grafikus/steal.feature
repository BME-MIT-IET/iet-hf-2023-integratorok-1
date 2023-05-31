Feature: Doctor Steals
  Scenario: Doctor steals material
    Given there are two doctors on an empty field
    And both of them have 30 material
    And the second doctor is stunned
    When the first doctor steals material from the second
    Then the doctor has 60 material

  Scenario: Doctor steals Bag
    Given there are two doctors on an empty field
    And the second doctor has a bag
    And the second doctor is stunned
    When the first doctor steals the bag
    Then the doctor now has a bag