Feature: Filter invoices by status
  As a user, I want to filter invoices by their status (draft, pending, paid), so I can quickly find invoices based on their payment status.

  Scenario Outline: User filters invoices by status
    Given the user is on the invoices page
    When the user selects "<status>" from the status filter
    Then only "<status>" invoices are displayed

    Examples:
      | status   |
      | Draft    |
      | Pending  |
      | Paid     |
