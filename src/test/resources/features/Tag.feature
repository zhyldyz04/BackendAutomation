Feature: User should be able to use tags
  @createTag
  Scenario Outline: verify user can create a tag
    Given base url "https://backend.cashwise.us/"
    When I provide valid authorization token
    And I provide "name_tag" with "<name_tag>"
    And I provide "description" with "<description>"
    And I hit POST endpoint "api/myaccount/tags"
    Then verify status code is 201
    And verify response body contains "name_tag" with "<name_tag>"
    And I retrieve id "id"
    And I hit DELETE endpoint "api/myaccount/tags/"
    Then verify status code is 200

    Examples:
      | name_tag | description           |
      | dog      | apple_tag             |
      | cat      | short tag description |
      | camel    |                       |


Scenario:
  create new tag
  catch its id
  update this tag
  get tag
  delete tag






  Scenario Outline: Verify tag cannot be created without required field tag_name
    Given base url "https://backend.cashwise.us/"
    When I provide valid authorization token
    And I provide "name_tag" with "<name_tag>"
    And I provide "description" with "Jika's tag description"
    And I hit POST endpoint "/api/myaccount/tags"
    Then verify status code is 400
    And verify response body contains "error" with "<error_message>"
    Examples:
      | name_tag            | error message                                                         |
      |                     | missing name                                                          |
      | #$BRB               | wrong name_tag, please make sure no digits and special chars are used |
      | jika1               | wrong name_tag, please make sure no digits and special chars are used |
      | extra long name tag | wrong name tag, size limit is 25 chars                                |
      | name with spaces    | wrong name_tag, no spaces are allowed|