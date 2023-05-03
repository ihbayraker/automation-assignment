@all @web @demoqa @bookstore
Feature: Bookstore-Assign books then verify

  Creates a new bookstore account
  Assign several books
  Verify the account on the frontend
  After everything is verified delete the created account

  Scenario Outline: Assign books then verify
    Given I create a new bookstore account
    And I generate a Token for the created account
    And I filter available books with "<publisher>"
    And I add filtered books to the created account
    Then I verify that books are added to the account
    And I navigate to Bookstore page
    And I click Login
    And I enter the created username, password and press login
    Then I validate that i Successfully logged in
    Then I validate the books from "<publisher>" added to the account
    Then I validate the details of the books in my collection
    And I log out of the account
    And I delete the account I created
    Examples:
      | publisher       |
      | No Starch Press |