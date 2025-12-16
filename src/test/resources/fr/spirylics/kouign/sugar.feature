Feature: Sugar API Tests

  Background:
    * url 'http://localhost:9090/api/v1/sugar'

  Scenario: random
    Given path 'random'
    And param size = 10
    When method get
    Then status 200
    And match response == '#[10]'
    And match each response == '#string'

  Scenario: window random
    Given path 'randomWindow'
    And param size = 20
    And param windowSize = 5
    When method get
    Then status 200
    And match response == '#array'
    And match each response == '#[5]'

  Scenario: template
    Given path 'template'
    And param name = 'World'
    When method get
    Then status 200
    And match response == 'Hello World'
