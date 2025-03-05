Feature: create product

  @createProduct
  Scenario: Successfully creating product
    Given base cashwise url "https://backend.cashwise.us" with valid token
    Then I provide product_title with "Manty pumpkin food"
    And product price 15
    And provide service_type_id with 1
    And provide category_id with 1
    And provide product_description with "Kyrgyz food"
    And provide date_of_payment with "2025-03-06"
    And set remind_before_day to 5
    Then I hit POST endpoint "/api/myaccount/products" to create product
    Then verify status code is equal to 201
    Then verify that product_title equals to "Manty pumpkin food"


