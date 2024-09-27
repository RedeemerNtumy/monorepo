#Feature: Toggle between light and dark modes
#  As a user, I want to toggle between light and dark modes, so I can choose the interface theme that best suits my viewing preferences.
#
#  Scenario: User toggles from light to dark mode
#    Given the user is on the settings page
#    When the user clicks the "Dark Mode" toggle button
#    Then the interface color scheme changes to dark mode
#    And the user’s preference is saved for the next visit
#
#  Scenario: User toggles from dark to light mode
#    Given the user is on the settings page with dark mode enabled
#    When the user clicks the "Light Mode" toggle button
#    Then the interface color scheme changes to light mode
#    And the user’s preference is saved for the next visit
#
#  Scenario: System remembers the last selected mode on next visit
#    Given the user previously selected "Dark Mode"
#    When the user revisits the application
#    Then the interface color scheme is already set to dark mode
