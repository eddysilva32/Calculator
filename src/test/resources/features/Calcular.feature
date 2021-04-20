Feature: Calculate
  Scenario: Add Two Positive Integer numbers
    Given that I open the google calculator on my smartphone
    When I select "8" plus "5" and press the equal button
    Then it displays the result as "13"

  Scenario Outline: Add Two Integer numbers Data Driven
    Given that I open the google calculator on my smartphone
    When I select <num1> plus <num2> and press the equal button
    Then it displays the result as <expectedResult>
    Examples:
    | num1 | num2 | expectedResult |
    | "8"  | "5"  | "13"           |
    | "7"  | "9"  | "16"           |
    | "3"  | "3"  | "6"            |

  Scenario: Calculate with Test Mass
    Given that I use the mass "db/massaCalc.csv" to test the calculator App
    When I perform the operation with two numbers
    Then it compare the current result with the expected




