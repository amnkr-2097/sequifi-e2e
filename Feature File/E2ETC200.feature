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

  @E2ETC2003 @settings @department @smoke
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

  @E2ETC2005 @settings @department @smoke
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

  # ══════════════════════════════════════════════════
  # SECTION 6: Position
  # ══════════════════════════════════════════════════

  @E2ETC2012 @settings @position @smoke
  Scenario: TC-POS-001 - Create a new position with basic details and pay configuration
    Given user navigates to Settings and opens the Position tab
    When user clicks the Add New position button
    And user enters position name "QA_Pos"
    And user enters custom products created
    And user selects Worker type "1099"
    And user selects pay frequency "Monthly"
    And user selects position department
    And user selects position main role "Self Gen"
    And user selects position permission group "Super Admin"
    And user enters next button for new position
    # completing Tier Schema page
    Then verify the Select Tier Schemas modal is displayed
    And  user "enables" Commission Tiers
    And  user selects Commission Tier Schema
    And  user selects Commission Tier Advancement "All sales of this product"
    And  user "enables" Upfront Tiers
    And  user selects Upfront Tier Schema
    And  user selects Upfront Tier Advancement "All sales of this product"
    And  user "enables" Override Tiers
    And  user selects Override Tier Schema
    And  user selects Override Tier Advancement "All sales of this product"
    And  user clicks Create on Select Tier Schemas modal
    # Completing Wadges
    And   user "enables" the Wages toggle in position setup
    And   user selects wages type "Hourly" in position setup
    And   user enters wages amount "20.00" in position setup
    And   user enters pto hours as "5"
    And   user select unused pto as "Expires Monthly"
    And   user clicks Save and Continue in position setup
    # Setting Up commission
    And   user "enables" the Commission toggle in position setup
    And   user enters commission default amount "10" for "Setter"
    And   user selects commission default type "Percent" for "Setter"
    And   user clicks on Next in position setup
    And   user enters commission default amount "10" for "Closer"
    And   user selects commission default type "Percent" for "Closer"
    And   user clicks on Next in position setup
    And   user enters commission default amount "10" for "Self Gen"
    And   user selects commission default type "Percent" for "Self Gen"
    And   user clicks on Next in position setup
    And   user enters commission default amount "10" for "Setter"
    And   user selects commission default type "Percent" for "Setter"
    And   user clicks on Next in position setup
    And   user enters commission default amount "10" for "Closer"
    And   user selects commission default type "Percent" for "Closer"
    And   user clicks on Next in position setup
    And   user enters commission default amount "10" for "Self Gen"
    And   user selects commission default type "Percent" for "Self Gen"
    And   user clicks Submit button in position setup

  @E2ETC2013 @settings @position @smoke
  Scenario: TC-POS-002 - Search for a position in the list
    Given user navigates to Settings and opens the Position tab
    When user searches for position
    Then verify position appears in the position list
    And user close the window

  # ------------------------------------------------------------------DONE----------------------------------------------------------------------------------------

  @E2ETC2014 @hiring @direct-hire @smoke
  Scenario: TC-HIRE-001 - Hire a new rep directly with all details
    Given user navigates to Hiring and opens Onboarding Employees tab
    # ── Step 1: Details Tab ──
    When user clicks the Hire New button
    And user enters first name "QA_First"
    And user enters last name "Last"
    And user enters personal email "aman.k++@sequifi.com"
    And user enters phone number "1234567890"
    And user selects office state "Alabama"
    And user selects office name first available
    And user clicks Save and Continue on Details tab
    # ── Step 2: Organization Tab ──
    And user selects department "Sales"
    And user selects position first available
    And user selects manager first available
    And user clicks Save and Continue on Organization tab
    # ── Step 3: Redline Tab ──
    And user fills redline for all roles with value "2.50" and type "Fixed"
    And user clicks Save and Continue on Redline tab
    # ── Step 4: Commission Tab ──
    And user clicks Next on Commission tab
    # ── Step 5: Upfront Tab ──
    And user clicks Next on Upfront tab
    # ── Step 6: Overrides Tab ──
    And user clicks Next on Overrides tab
    # ── Step 7: Agreement Tab ──
    And user fills agreement details
    And user clicks Save and Continue on Agreement tab
    # ── Step 8: Additional Information Tab ──
    And user clicks Save and Continue on Additional Information tab
    # ── Step 9: Review & Finish Tab ──
    And user clicks Finish to complete hiring
    Then verify rep is hired successfully
    And user close the window

  @E2ETC2015 @hiring @search
  Scenario: TC-HIRE-002 - Search for the hired rep in onboarding list
    Given user navigates to Hiring and opens Onboarding Employees tab
    When user searches for hired rep
    Then verify hired rep appears in the onboarding list
    And user close the window