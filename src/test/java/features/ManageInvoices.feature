Feature: Manage invoices
  As a user, I want to create, view, edit, and delete invoices, so that I can manage my billing records easily.

  Scenario: User creates a new invoice
    Given the user is on the create invoice page
    When the user fills in all required fields
    And the user clicks the "Create Invoice" button
    Then a new invoice is created with the entered details

  Scenario: User views the list of invoices
    Given the user is on the invoices page
    When the user loads the page
    Then a list of all invoices is displayed with details such as invoice number, date, amount, and status

  Scenario: User edits an existing invoice
    Given the user is on the invoices page
    When the user selects an invoice to edit
    And the user makes changes to the invoice
    And the user clicks the "Save" button
    Then the invoice is updated with the new details

  Scenario: User deletes an invoice
    Given the user is on the invoices page
    When the user selects an invoice to delete
    And the user clicks the "Delete" button
    Then the system prompts the user for confirmation
    When the user confirms the deletion
    Then the invoice is deleted from the system

  Scenario: User cancels deleting an invoice
    Given the user is on the invoices page
    When the user selects an invoice to delete
    And the user clicks the "Delete" button
    Then the system prompts the user for confirmation
    When the user cancels the deletion
    Then the invoice is not deleted
