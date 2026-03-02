@E2ETC200
Feature: Login to Solar Stage and Configure Settings

  Background:
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Solar" and Logs-In
    And verify user is logged in

  # ══════════════════════════════════════════════════
  # SECTION 1: LOCATION
  # ══════════════════════════════════════════════════

  @E2ETC2001 @settings @location @smoke
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

  @E2ETC2002 @settings @location @smoke
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

  @E2ETC2004 @settings @department @smoke
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

  @E2ETC2007 @settings @tiers @smoke
  Scenario: TC-TIER-002 - Search for a tier in the list
    Given user navigates to Settings and opens the Tiers tab
    When user searches for tier
    Then verify tier appears in the tier list
    And user close the window

  # ══════════════════════════════════════════════════
  # SECTION 4: MILESTONE
  # ══════════════════════════════════════════════════

  @E2ETC2008 @settings @milestone @smoke
  Scenario: TC-MS-002 - Create a new milestone schema with single trigger
    Given user navigates to Settings and opens the Milestone tab
    When user clicks the Add New milestone button
    And user enters schema name "QA_Single_Trigger"
    And user enters schema description "Single trigger schema for QA testing"
    And user captures initial trigger data
    Then user clicks add trigger button
    And user enters trigger name "Payment-1" at index 1
    And user selects trigger date "M1 Date" at index 1
    And user clicks the Save milestone button
    Then verify milestone is created successfully
    And verify milestone appears in the list
    And user close the window

  @E2ETC2009 @settings @milestone @smoke
  Scenario: TC-MS-010 - Search milestone schemas by name
    Given user navigates to Settings and opens the Milestone tab
    When user searches for milestone
    Then verify milestone appears in the list
    And user close the window

  # ══════════════════════════════════════════════════
  # SECTION 5: PRODUCT
  # ══════════════════════════════════════════════════

  @E2ETC2010 @settings @product @smoke
  Scenario: TC-PROD-001 - Create a new product with all details
    Given user navigates to Settings and opens the Product tab
    When user clicks the Add New product button
    And user enters product name "QA Panel"
    And user enters product ID "QA/P/"
    And user enters product description "Product created for QA regression testing"
    And user selects product milestone schema last created milestone
    And user selects the override eligibility
    And user enters product redline "2.50"
    And user enters product effective date as today
    And user clicks the Create product button
    Then verify product is created successfully
    And user close the window

  @E2ETC2011 @settings @product @smoke
  Scenario: TC-PROD-003 - Search for a product in the list
    Given user navigates to Settings and opens the Product tab
    When user searches for product
    Then verify product appears in the product list
    And user close the window

  # ------------------------------------------------------------------DONE----------------------------------------------------------------------------------------

  # ══════════════════════════════════════════════════
  # SECTION 6: Position
  # ══════════════════════════════════════════════════

  @E2ETC2011 @settings @position @smoke
  Scenario: TC-POS-001 - Create a new position with basic details and pay configuration
    Given user navigates to Settings and opens the Position tab
    When user clicks the Add New position button
    And user enters position name "QA_Setter_Position"
    And user enters position description "Position created for QA regression testing"
    And user selects pay type "Per Watt"
    And user enters upfront pay "3.50"
    And user selects position department "N/A"
    And user selects position product "N/A"
    And user selects position tier schema "N/A"
    And user selects position milestone schema "N/A"
    And user enters position effective date as today
    And user clicks the Save position button
    Then verify position is created successfully
    And user close the window

  @E2ETC2012 @settings @position @smoke
  Scenario: TC-POS-002 - Search for a position in the list
    Given user navigates to Settings and opens the Position tab
    When user searches for position
    Then verify position appears in the position list
    And user close the window
