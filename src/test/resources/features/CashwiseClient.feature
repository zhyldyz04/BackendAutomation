Feature: Getting all clients

  @positiveScenario
  Scenario: Get all clients
    Given the user hits the API with endpoint "/api/myaccount/clients"
    Then the user validates that status code is 200
    Then the user verifies if all clients id are not empty


