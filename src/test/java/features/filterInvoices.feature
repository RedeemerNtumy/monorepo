Feature: Filter invoices by status
  As a user, I want to filter invoices by their status (draft, pending, paid), so I can quickly find invoices based on their payment status.

  Scenario: User filters invoices by "Draft" status
    Given the user is on the invoices page
    When the user selects "Draft" from the status filter
    Then only "Draft" invoices are displayed

  Scenario: User filters invoices by "Pending" status
    Given the user is on the invoices page
    When the user selects "Pending" from the status filter
    Then only "Pending" invoices are displayed

  Scenario: User filters invoices by "Paid" status
    Given the user is on the invoices page
    When the user selects "Paid" from the status filter
    Then only "Paid" invoices are displayed

