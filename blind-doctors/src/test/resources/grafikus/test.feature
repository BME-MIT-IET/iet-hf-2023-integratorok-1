Feature: Doctor's Material Management
  Scenario: Doctor collects materials from a shelter
    Given there is a doctor
    And the doctor has 100 max material
    And there is a shelter
    When the doctor steps on the shelter
    Then the doctor has max 150 material