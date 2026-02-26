@E2ETC200
Feature: Login to Solar Stage and Configure Settings (Location, Department, Tiers, Product)

  Background:
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Solar" and Logs-In
    And verify user is logged in

  # ══════════════════════════════════════════════════
  # SECTION 1: LOCATION
  # ══════════════════════════════════════════════════

  @E2ETC2001 @settings @location @smoke1
  Scenario: TC-LOC-001 - Add a new Office location with redline
    Given user navigates to Settings and opens the Location tab
    When user clicks the Add New location button
    And user selects state "California" from the state dropdown
    And user enters location code "-TEST01"
    And user enters redline values:
      | Min  | Standard | Max  |
      | 2.00 | 2.30     | 2.80 |
    And user enters effective date as today
    And user clicks the Add Location button
    Then verify location is created successfully
    And user close the window

  @E2ETC2002 @settings @location @smoke1
  Scenario: TC-LOC-002 - Search for a location in the list
    Given user navigates to Settings and opens the Location tab
    When user searches for location
    Then verify location appears in the location list
    And user close the window

  # ══════════════════════════════════════════════════
  # SECTION 2: DEPARTMENT
  # ══════════════════════════════════════════════════

  @E2ETC2003 @settings @department @smoke1
  Scenario: TC-DEPT-001 - Create a new root department
    Given user navigates to Settings and opens the Department tab
    When user clicks the Create New department button
    And user enters "Parent" department name "QA_Dept_Test"
    And user clicks the Create department button
    Then verify department "Parent Dept" is created successfully
    And user close the window

  @E2ETC2004 @settings @department @smoke1
  Scenario: TC-DEPT-002 - Create a child department under an existing parent
    Given user navigates to Settings and opens the Department tab
    When user clicks the Create New department button
    And user enters "Child" department name "QA_Sub_Team"
    And user clicks the Create department button
    Then verify department "Child" is created successfully
    And user close the window

  @E2ETC2005 @settings @department @smoke1
  Scenario: TC-DEPT-003 - Search for a department in the list
    Given user navigates to Settings and opens the Department tab
    When user searches for department "Parent"
    Then verify department "Parent" appears in the department list
    And user close the window
  # ------------------------------------------------------------------DONE----------------------------------------------------------------------------------------
  # ══════════════════════════════════════════════════
  # SECTION 3: TIERS
  # ══════════════════════════════════════════════════

  @E2ETC2006 @settings @tiers @smoke
  Scenario: TC-TIER-001 - Create a new tier schema
    Given user navigates to Settings and opens the Tiers tab
    When user clicks the Add New tier button
    And user enters tier schema name "QA_Tier_Schema"
    And user enters tier schema description "Tier schema created for QA testing"
    And user selects tier system "Tiered based on job metrics (Exact Match)"
    And user selects tier metrics "Location Code"
    And user selects tier type "N/A"
    And user clicks the Save tier button
    Then verify tier is created successfully
    And user close the window

  @E2ETC2007 @settings @tiers
  Scenario: TC-TIER-002 - Search for a tier in the list
    Given user navigates to Settings and opens the Tiers tab
    When user searches for tier
    Then verify tier appears in the tier list
    And user close the window

  # ══════════════════════════════════════════════════
  # SECTION 4: PRODUCT
  # ══════════════════════════════════════════════════

  @E2ETC2008 @settings @product @smoke
  Scenario: TC-PROD-001 - Create a new product with basic details
    Given user navigates to Settings and opens the Product tab
    When user clicks the Add New product button
    And user enters product name "QA Solar Panel"
    And user enters product ID "QA-PROD-001"
    And user enters product description "Product created for QA regression testing"
    And user enters product effective date as today
    And user clicks the Create product button
    Then verify product "QA Solar Panel" is created successfully
    And user close the window

  @E2ETC2009 @settings @product
  Scenario: TC-PROD-003 - Search for a product in the list
    Given user navigates to Settings and opens the Product tab
    When user searches for product "QA Solar Panel"
    Then verify product "QA Solar Panel" appears in the product list
    And user close the window
