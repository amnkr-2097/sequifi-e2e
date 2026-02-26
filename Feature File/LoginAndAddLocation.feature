Feature: Login to Solar Stage and Configure Settings (Location, Department, Tiers, Product)

  Background:
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Solar" and Logs-In
    And verify user is logged in

  # ══════════════════════════════════════════════════
  # SECTION 1: LOCATION - HAPPY PATH
  # ══════════════════════════════════════════════════

  @settings @location @smoke
  Scenario: TC-LOC-001 - Add a new Office location with redline
    Given user navigates to Settings and opens the Location tab
    When user clicks the Add New location button
    And user selects state "California" from the state dropdown
    And user enters location code "CA-TEST01"
    And user checks the Office checkbox
    And user enters office name "CA Test Office"
    And user enters office address "123 Main Street, Los Angeles, CA 90001"
    And user enters redline values:
      | Min  | Standard | Max  |
      | 2.00 | 2.30     | 2.80 |
    And user enters effective date as today
    And user clicks the Add Location button
    Then verify location "CA-TEST01" is created successfully
    And user close the window

  @settings @location @smoke
  Scenario: TC-LOC-002 - Add a Redline-only location (no office)
    Given user navigates to Settings and opens the Location tab
    When user clicks the Add New location button
    And user selects state "Texas" from the state dropdown
    And user enters location code "TX-TEST01"
    And user enters redline values:
      | Min  | Standard | Max  |
      | 1.80 | 2.10     | 2.50 |
    And user enters effective date as today
    And user clicks the Add Location button
    Then verify location "TX-TEST01" is created successfully
    And user close the window

  @settings @location
  Scenario: TC-LOC-003 - Search for a location in the list
    Given user navigates to Settings and opens the Location tab
    When user searches for location "CA-TEST01"
    Then verify location "CA-TEST01" appears in the location list
    And user close the window

  # ══════════════════════════════════════════════════
  # SECTION 2: DEPARTMENT - HAPPY PATH
  # ══════════════════════════════════════════════════

  @settings @department @smoke
  Scenario: TC-DEPT-001 - Create a new root department
    Given user navigates to Settings and opens the Department tab
    When user clicks the Create New department button
    And user enters department name "QA Department"
    And user clicks the Create department button
    Then verify department "QA Department" is created successfully
    And user close the window

  @settings @department @smoke
  Scenario: TC-DEPT-002 - Create a child department under an existing parent
    Given user navigates to Settings and opens the Department tab
    When user clicks the Create New department button
    And user selects parent department "QA Department"
    And user enters department name "QA Sub-Team Alpha"
    And user clicks the Create department button
    Then verify department "QA Sub-Team Alpha" is created successfully
    And user close the window

  @settings @department
  Scenario: TC-DEPT-003 - Search for a department in the list
    Given user navigates to Settings and opens the Department tab
    When user searches for department "QA Department"
    Then verify department "QA Department" appears in the department list
    And user close the window

  # ══════════════════════════════════════════════════
  # SECTION 3: TIERS - HAPPY PATH
  # ══════════════════════════════════════════════════

  @settings @tiers @smoke
  Scenario: TC-TIER-001 - Create a new tier schema
    Given user navigates to Settings and opens the Tiers tab
    When user clicks the Add New tier button
    And user enters tier schema name "QA Tier Schema"
    And user enters tier schema description "Tier schema created for QA testing"
    And user selects tier system "Automatic"
    And user selects tier metrics "Account"
    And user selects tier type "Standard"
    And user clicks the Save tier button
    Then verify tier "QA Tier Schema" is created successfully
    And user close the window

  @settings @tiers
  Scenario: TC-TIER-002 - Search for a tier in the list
    Given user navigates to Settings and opens the Tiers tab
    When user searches for tier "QA Tier Schema"
    Then verify tier "QA Tier Schema" appears in the tier list
    And user close the window

  # ══════════════════════════════════════════════════
  # SECTION 4: PRODUCT - HAPPY PATH
  # ══════════════════════════════════════════════════

  @settings @product @smoke
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

  @settings @product @smoke
  Scenario: TC-PROD-002 - Create a product with redline value
    Given user navigates to Settings and opens the Product tab
    When user clicks the Add New product button
    And user enters product name "QA Premium Panel"
    And user enters product ID "QA-PROD-002"
    And user enters product description "Premium product with redline"
    And user enters product redline "2.50"
    And user enters product effective date as today
    And user clicks the Create product button
    Then verify product "QA Premium Panel" is created successfully
    And user close the window

  @settings @product
  Scenario: TC-PROD-003 - Search for a product in the list
    Given user navigates to Settings and opens the Product tab
    When user searches for product "QA Solar Panel"
    Then verify product "QA Solar Panel" appears in the product list
    And user close the window
