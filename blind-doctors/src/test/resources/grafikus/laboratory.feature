Feature: Doctor steps on Laboratory
  Scenario: Doctor steps on Laboratory with no code
    Given there is a doctor
    And the doctor has no code
    And there is a laboratory with protection code
    When the doctor steps on the laboratory with protection code
    Then the doctor has protection code