Feature: Doctor steps on Storage
  Scenario: Doctor steps on Storage with no material
    Given there is a doctor
    And the doctor begins with 0 material
    And there is a storage
    When the doctor steps on the storage
    Then the doctor has 50 material
  Scenario: Doctor steps on Storage with almost full material
    Given there is a doctor
    And the doctor begins with 90 material
    And there is a storage
    When the doctor steps on the storage
    Then the doctor has 100 material
  Scenario: Doctor steps on Storage with a bag
    Given there is a doctor
    And the doctor begins with 90 material
    And the doctor has a bag
    And there is a storage
    When the doctor steps on the storage
    Then the doctor has 140 material