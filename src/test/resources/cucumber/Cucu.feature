Feature: Freestyle1 job

  Scenario: Create1 job
    When Go1 to NewJob
    And Type1 job name "test name"
    And Choose1 job type as Freestyle
    And Click1 Ok and go to config
    And Save1 config and go to Freestyle job
    Then Freestyle1 job name is "Project test name"