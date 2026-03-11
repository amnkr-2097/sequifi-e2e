@SolarE2E
Feature: Login to Solar Stage and Configure Settings

  @SolarE2E @settings @location @smoke
  Scenario: TC-LOC-001 - Add a new Office location with redline
    Given user navigates to "Solar Stage" WebPage
    When user enters the "superadmin@sequifi.com" and "PW:Solar" and Logs-In
    And verify user is logged in

    # Location
    And user navigates to Settings and opens the Location tab
    When user clicks the Add New location button
    And user selects state "California" from the state dropdown
    And user enters location code "-TEST01"
    And user enters redline values:
      | Min  | Standard | Max  |
      | 2.00 | 2.30     | 2.80 |
    And user enters effective date as today
    And user clicks the Add Location button
    Then verify location is created successfully
    And user refresh the page

    # Department
    Given user navigates to Settings and opens the Department tab
    When user clicks the Create New department button
    And user enters "Parent" department name "QA_Dept_Test"
    And user clicks the Create department button
    Then verify department "Parent Dept" is created successfully
    And user refresh the page

    Given user navigates to Settings and opens the Department tab
    When user clicks the Create New department button
    And user enters "Child" department name "QA_Sub_Team"
    And user clicks the Create department button
    Then verify department "Child" is created successfully
    And user refresh the page

    # Tiers
    Given user navigates to Settings and opens the Tiers tab
    When user clicks the Add New tier button
    And user enters tier schema name "QA_Tier_Schema"
    And user enters tier schema description "Tier schema created for QA testing"
    And user selects tier system "Tiered based on job metrics (Exact Match)"
    And user selects tier metrics "Location Code"
    And user selects tier type "N/A"
    And user clicks the Save tier button
    Then verify tier is created successfully
    And user refresh the page

    # Milestone
#    Given user navigates to Settings and opens the Milestone tab
#    When user clicks the Add New milestone button
#    And user enters schema name "QA_Single_Trigger"
#    And user enters schema description "Single trigger schema for QA testing"
#    And user captures initial trigger data
#    Then user clicks add trigger button
#    And user enters trigger name "Payment-1" at index 1
#    And user selects trigger date "M1 Date" at index 1
#    And user clicks the Save milestone button
#    Then verify milestone is created successfully
#    And verify milestone appears in the list
#    And user refresh the page

    # Product
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
    And user refresh the page

    # Position
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
    And user refresh the page

    # Hiring
    Given user navigates to Hiring and opens Onboarding Employees tab
    When user clicks the Hire New button
    And user enters first name "QA_First"
    And user enters last name "Last"
    # email need to be changed on every run
    And user enters personal email "aman.k+++13"
    And user enters phone number
    And user selects office state "Texas"
    And user selects office name "saza"
    And user clicks Save and Continue on Details tab
    And user selects department
    And user selects position
    And user "enable" manager checkbox
    And user clicks Save and Continue on Organization tab
    And user clicks Save and Continue on Organization tab

    # Redline Tab
    And user fills redline for "Closer" with value "20" and type "Fixed"
    And user fills redline for "Setter" with value "20" and type "Fixed"
    And user fills redline for "Self Gen" with value "20" and type "Fixed"
    And user clicks Save and Continue on Redline tab

    # Commission Tab -> Auto filled just need to skip this section
    And user clicks Next on Commission tab
    And user clicks Next on Commission tab
    And user clicks Next on Commission tab
    And user clicks Next on Commission tab
    And user clicks Next on Commission tab
    And user clicks Save and Continue on Commission tab

    # Agreement Tab
    And user fills agreement details
    And user clicks Save and Continue on Agreement tab

    # Additional Information Tab
    And user clicks Save and Continue on Additional Information tab

    # Review & Finish Tab
    And user complete Review and Finish for hiring
    Then verify rep is hired successfully
    And user refresh the page

    # Sales
    Given user navigates to the Sales page
    When user clicks the Add Sale button
    And user enter "ID" as "QA"
    And user enter "Customer Name" as "Customer-"
    And user selects the product
    And user selects the Customer state and Location Code
    And user enter "KW" as "40"
    And user enter "EPC" as "90"
    And user enter "Net EPC" as "100"
    And user enter the "Sale Date"
#    And user enter the "M1 Date"
#    And user enter the "FINAL PAYMENT"
    Then user select "Closer"
    Then user select "Setter"
    And user clicks Submit sale button
    Then verify sale is created successfully
    And user refresh the page
    When user searches for the sale
    And user opens the sale
    And verify commission is calculated correctly
    And user close the window