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

  Scenario: User removes the status filter
    Given the user has applied a status filter
    When the user clears the filter
    Then all invoices are displayed

  Scenario: User changes the status filter
    Given the user has applied a "Draft" filter
    When the user changes the filter to "Pending"
    Then only "Pending" invoices are displayed
