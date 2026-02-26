@E2ETC100
Feature: E2ETC100 - Full Regression Suite for Sequifi Platform

  # ──────────────────────────────────────────────────
  # SECTION 1: LOGIN & AUTHENTICATION
  # ──────────────────────────────────────────────────

  @E2ETC1001 @login
  Scenario: TC001 - Successful login with valid credentials
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Solar" and Logs-In
    And verify user is logged in
    Then user logs out from the website
    And verify user is redirected to login page
    And user close the window

  @E2ETC1002 @login @negative
  Scenario: TC002 - Login fails with invalid credentials
    Given user navigates to "Solar Stage" WebPage
    When user enters the "invalid@sequifi.com" and "PW:Solar" and Logs-In
    Then verify login error message is displayed
    And user close the window

  @E2ETC1003 @login @negative
  Scenario: TC003 - Login fails with empty email
    Given user navigates to "Solar Stage" WebPage
    When user enters the "" and "PW:Solar" and Logs-In
    Then verify login error message is displayed
    And user close the window

  @E2ETC1004 @login @negative
  Scenario: TC004 - Login fails with empty password
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Empty" and Logs-In
    Then verify login error message is displayed
    And user close the window

  @E2ETC1005 @login
  Scenario Outline: TC005 - Login across multiple environments
    Given user navigates to "<Environment>" WebPage
    When user enters the "<Email>" and "<PasswordKey>" and Logs-In
    And verify user is logged in
    Then user logs out from the website
    And user close the window

    Examples:
      | Environment  | Email                      | PasswordKey |
      | Solar Stage  | superadmin@sequifi.com     | PW:Solar    |
      | Turf Stage   | superadmin@sequifi.com     | PW:Turf     |
      | Pest Stage   | superadmin@sequifi.com     | PW:Pest     |
      | Fiber Stage  | superadmin@sequifi.com     | PW:Fiber    |

  # ──────────────────────────────────────────────────
  # SECTION 2: DASHBOARD & MODULE VERIFICATION
  # ──────────────────────────────────────────────────

  @E2ETC1006 @dashboard @smoke
  Scenario: TC006 - Dashboard loads after login
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Solar" and Logs-In
    And verify user is logged in
    Then user verifies the dashboard is loaded
    And user close the window

  @E2ETC1007 @dashboard
  Scenario: TC007 - All main modules are accessible from dashboard
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Solar" and Logs-In
    And verify user is logged in
    Then user verifies the following modules are accessible:
      | Settings  |
      | Payroll   |
      | Reports   |
      | Hiring    |
    And user close the window

  @E2ETC1008 @navigation
  Scenario: TC008 - Navigate to Hiring module
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Solar" and Logs-In
    And verify user is logged in
    Then user navigates to "Hiring" module
    And verify the "Hiring" page is loaded
    And user close the window

  @E2ETC1009 @navigation
  Scenario: TC009 - Navigate to Sales module
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Solar" and Logs-In
    And verify user is logged in
    Then user navigates to "Reports" module
    Then user navigates to "Sales" module
    And verify the "Sales" page is loaded
    And user close the window

  @E2ETC1010 @navigation
  Scenario: TC010 - Navigate to Payroll module
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Solar" and Logs-In
    And verify user is logged in
    Then user navigates to "Payroll" module
    And verify the "Payroll" page is loaded
    And user close the window

  @E2ETC1011 @navigation
  Scenario: TC011 - Navigate to Settings module
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Solar" and Logs-In
    And verify user is logged in
    Then user navigates to "Settings" module
    And verify the "Settings" page is loaded
    And user close the window

  # ──────────────────────────────────────────────────
  # SECTION 3: LOGOUT & SESSION
  # ──────────────────────────────────────────────────

  @E2ETC1012 @logout @smoke
  Scenario: TC012 - Logout redirects to login page
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Solar" and Logs-In
    And verify user is logged in
    Then user logs out from the website
    And verify user is redirected to login page
    And user close the window

  @E2ETC1013 @session
  Scenario: TC013 - Back button after logout does not restore session
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Solar" and Logs-In
    And verify user is logged in
    Then user logs out from the website
    And verify user is redirected to login page
    When user clicks browser back button
    Then verify user is redirected to login page
    And user close the window
