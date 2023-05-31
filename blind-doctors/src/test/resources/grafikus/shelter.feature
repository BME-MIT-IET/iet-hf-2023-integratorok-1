Feature: Doctor steps on Shelter
  Scenario: Doctor steps on shelter with bag
    Given there is a doctor
    And the doctor has 100 max capacity
    And there is a shelter with bag
    When the doctor steps on the shelter
    Then the doctor has max 150 material
  Scenario: Doctor steps on shelter, but gets no equipment,because he has 3 already
    Given there is a doctor
    And the doctor has three equipment
    And there is a shelter with bag
    When the doctor steps on the shelter
    Then the doctor still has three equipment