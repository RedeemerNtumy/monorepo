Feature: Responsive layout adjustment
  As a user, I want the app layout to adjust based on my device's screen size, so I can have an optimal viewing experience on any device.

  Scenario: Layout adjusts for mobile device
    Given the user accesses the app on a mobile device
    Then the layout is optimized for mobile viewing
    And elements are resized and rearranged for better readability

  Scenario: Layout adjusts for tablet device
    Given the user accesses the app on a tablet device
    Then the layout is optimized for tablet viewing
    And elements are resized and rearranged for better readability

  Scenario: Layout adjusts for desktop device
    Given the user accesses the app on a desktop device
    Then the layout is optimized for desktop viewing
    And elements are spaced efficiently to make use of the larger screen size
