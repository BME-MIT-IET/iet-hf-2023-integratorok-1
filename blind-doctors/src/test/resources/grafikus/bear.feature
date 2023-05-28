Feature: Bear related
  Scenario: Bear infects doctor with bear virus successfully
    Given There are two empty fields connected
    And one of them has a doctor the other a bear
    When the bear steps to the other field
    Then the doctor becomes a bear
  Scenario: Bear dies while trying to infect doctor
    Given There are two empty fields connected
    And one of them has a doctor the other a bear
    And the doctor has an axe
    When the bear steps to the other field
    Then the bear dies