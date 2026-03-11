# Sequifi E2ETC100 - End-to-End Regression Suite

Automated end-to-end test suite for the **Sequifi Platform** using Selenium WebDriver, Cucumber BDD, and TestNG.

---

## Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming language |
| Selenium WebDriver | 4.27.0 | Browser automation |
| Cucumber | 7.20.1 | BDD test framework (Gherkin) |
| TestNG | 7.10.2 | Test runner |
| ExtentReports | 5.1.2 | HTML test reporting |
| Maven | 3.x | Build & dependency management |

---

## Project Structure

```
E2ETC100/
|
|-- Feature File/
|   |-- E2ETC100.feature              # Login, Dashboard, Navigation, Session tests
|   |-- E2ETC200.feature              # Settings + Hiring + Sales (modular scenarios)
|   |-- LoginAndAddLocation.feature   # Location, Department, Tiers, Product
|   |-- SolarEndToEnd.feature         # Full Solar End to End flow (single scenario)
|
|-- src/test/java/
|   |-- HelperClasses/                          # Page Object Model (POM)
|   |   |-- pgGeneric.java                      # Base class (driver, waits, utilities, Miscellaneous)
|   |   |-- pgLogin.java                        # Login & logout actions
|   |   |-- pgLocation.java                     # Settings > Location
|   |   |-- pgDepartment.java                   # Settings > Department
|   |   |-- pgTiers.java                        # Settings > Tiers
|   |   |-- pgMilestone.java                    # Settings > Milestone
|   |   |-- pgProduct.java                      # Settings > Product
|   |   |-- pgPosition.java                     # Settings > Position
|   |   |-- pgPosition_Setup.java               # Position setup (Wages, Commission, etc.)
|   |   |-- pgHiring.java                       # Hiring > Onboarding Employees
|   |   |-- pgProjectExpectedVariables.java     # Test data storage (HashMap)
|   |
|   |-- StepDefination/               # Cucumber step definitions
|   |   |-- LoginStepDef.java
|   |   |-- E2ETC100StepDef.java
|   |   |-- LocationStepDef.java
|   |   |-- DepartmentStepDef.java
|   |   |-- TiersStepDef.java
|   |   |-- MilestoneStepDef.java
|   |   |-- ProductStepDef.java
|   |   |-- PositionStepDef.java
|   |   |-- Position_SetUPStepDef.java
|   |   |-- HiringStepDef.java
|   |
|   |-- TestRunner/                    # TestNG + Cucumber runners
|       |-- E2ETC100Runner.java        # Runs E2ETC100.feature
|       |-- TestRunner.java            # Runs E2ETC200.feature
|
|-- Reports/                           # Generated test reports
|-- pom.xml                            # Maven dependencies
|-- testng.xml                         # TestNG suite config
```

---

## Design Pattern

**Page Object Model (POM)** with inheritance:

```
pgGeneric (Base)
  |-- pgLogin
  |-- pgLocation
  |-- pgDepartment
  |-- pgTiers
  |-- pgMilestone
  |-- pgProduct
  |-- pgPosition
  |-- pgPosition_Setup
  |-- pgHiring
  |-- pgAddingSales
```

- All page objects extend `pgGeneric` which provides driver, waits, dropdown helpers, and date utilities
- `pgProjectExpectedVariables` stores expected test data in static HashMaps so values can be shared across steps
- Random suffixes are appended to names/IDs at runtime to avoid duplicate conflicts

---

## Feature Files

### E2ETC100.feature - Core Regression (13 Scenarios)

| Tag | Test | Description |
|-----|------|-------------|
| @E2ETC1001 | TC001 | Successful login with valid credentials |
| @E2ETC1002 | TC002 | Login fails with invalid credentials |
| @E2ETC1003 | TC003 | Login fails with empty email |
| @E2ETC1004 | TC004 | Login fails with empty password |
| @E2ETC1005 | TC005 | Login across multiple environments (Solar, Turf, Pest, Fiber) |
| @E2ETC1006 | TC006 | Dashboard loads after login |
| @E2ETC1007 | TC007 | All main modules accessible |
| @E2ETC1008 | TC008 | Navigate to Hiring module |
| @E2ETC1009 | TC009 | Navigate to Sales module |
| @E2ETC1010 | TC010 | Navigate to Payroll module |
| @E2ETC1011 | TC011 | Navigate to Settings module |
| @E2ETC1012 | TC012 | Logout redirects to login page |
| @E2ETC1013 | TC013 | Back button after logout doesn't restore session |

### E2ETC200.feature - Settings + Hiring (15 Scenarios)

| Tag        | Test        | Description                      |
|------------|-------------|----------------------------------|
| @E2ETC2001 | TC-LOC-001  | Add office location with redline |
| @E2ETC2002 | TC-LOC-002  | Search for location              |
| @E2ETC2003 | TC-DEPT-001 | Create parent department         |
| @E2ETC2004 | TC-DEPT-002 | Create child department          |
| @E2ETC2005 | TC-DEPT-003 | Search department                |
| @E2ETC2006 | TC-TIER-001 | Create tier schema               |
| @E2ETC2007 | TC-TIER-002 | Search tier                      |
| @E2ETC2008 | TC-MS-002   | Create milestone schema          |
| @E2ETC2009 | TC-MS-010   | Search milestone                 |
| @E2ETC2010 | TC-PROD-001 | Create product                   |
| @E2ETC2011 | TC-PROD-003 | Search product                   |
| @E2ETC2012 | TC-POS-001  | Create position with full setup  |
| @E2ETC2013 | TC-POS-002  | Search position                  |
| @E2ETC2014 | TC-HIRE-001 | Hire new rep (full 9-step flow)  |
| @E2ETC2015 | TC-HIRE-002 | Search hired rep                 |
| @E2ETC2016 | TC-SALE-001 | Adding sales                     |

### SolarEndToEnd.feature - Full E2E Flow (Single Scenario)

One scenario covering the complete Solar business workflow:

```
Login --> Location --> Department --> Tiers --> Milestone --> Product --> Position
     --> Hire Rep --> Add Sale --> Validate Commission
```

---

## E2E Test Flow

The full end-to-end flow follows this dependency chain:

```
1. LOGIN
   |
2. SETTINGS
   |-- Create Location (State, Redline, Office)
   |-- Create Departments (Parent + Child)
   |-- Create Tier Schema (Metrics, System, Type)
   |-- Create Milestone Schema (System, Trigger)
   |-- Create Product (links Milestone, Redline)
   |-- Create Position (links Department, Product, Tier)
   |       |-- Configure Wages (Hourly, PTO)
   |       |-- Configure Commission (Setter, Closer, Self Gen)
   |
3. HIRING
   |-- Hire New Rep (Details, Organization, Redline, Commission, Agreement)
   |
4. SALES
   |-- Add Sale (ID, Customer, Product, KW, EPC, Closer, Setter)
   |-- Verify Sale Created
   |-- Validate Commission (Expected vs Actual comparison table)
```

Each step stores data in `pgProjectExpectedVariables` so subsequent steps can reference it.

---

## Environments

| Environment | URL |
|-------------|-----|
| Solar Stage | `https://solarstage.sequifi.com/auth` |
| Turf Stage | `https://turfstage.sequifi.com/auth` |
| Pest Stage | `https://peststage.sequifi.com/auth` |
| Fiber Stage | `https://fiberstage.sequifi.com/auth` |
---

## How to Run

### Prerequisites
- Java 17+
- Maven 3.x
- Google Chrome (latest)
- ChromeDriver is auto-managed by Selenium 4.x

### Run All Tests
```bash
mvn clean test
```

### Run by Tag
```bash
mvn test -Dcucumber.filter.tags="@smoke"
mvn test -Dcucumber.filter.tags="@E2ETC1001"
mvn test -Dcucumber.filter.tags="@SolarE2E"
```

### Run Specific Runner
```bash
mvn test -Dtest=TestRunner.TestRunner
mvn test -Dtest=TestRunner.E2ETC100Runner
```

### Common Tags
| Tag | Scope |
|-----|-------|
| `@smoke` | All smoke tests |
| `@SolarE2E` | Full end-to-end Solar flow |
| `@login` | Login tests only |
| `@settings` | All settings module tests |
| `@location` | Location tests |
| `@department` | Department tests |
| `@tiers` | Tier tests |
| `@milestone` | Milestone tests |
| `@product` | Product tests |
| `@position` | Position tests |
| `@hiring` | Hiring tests |
| `@sales` | Sales & commission tests |

---

## Test Reports

After execution, reports are generated at:

| Report          | Location | Type |
|-----------------|----------|------|
| ExtentReport    | `Reports/report.html` | Detailed HTML with pass/fail per step |
| Cucumber Report | `Reports/Settings_Report.html` | E2ETC200 results |

Reports include:
- Pass/Fail/Warning status per step
- Expected vs Actual comparison tables for commission validation
- Timestamps and execution duration

---

## Key Utilities (pgGeneric)

| Method | Purpose                                             |
|--------|-----------------------------------------------------|
| `initDriver()` | Starts Chrome with maximized window                 |
| `getExplicitWait(sec)` | Returns WebDriverWait                               |
| `selectByVisibleText()` | Handles native + PrimeReact custom dropdowns        |
| `selectByPartialText()` | Partial match dropdown selection                    |
| `selectRandom()` | Random dropdown option selection                    |
| `enterDate()` / `selectToday()` | Prime React calendar date entry                     |
| `selectPastDate()` / `selectFutureDate()` | Relative date selection                             |
| `setReactDateField()` | React-aware date field via JS                       |
| `setToggle()` | Enable/disable toggle switches                      |
| `refreshPage()` | Page refresh via JavaScript                         |
| `compareAndLog()` | Expected vs Actual comparison table in ExtentReport |
| `switchToFrameByLocator()` | iFrame navigation                                   |

---

## Application Under Test

**Sequifi** - A solar/field sales commission management platform.

| Module | Coverage |
|--------|----------|
| Settings | Location, Department, Tiers, Milestone, Product, Position |
| Hiring | Employee onboarding (9-step wizard) |
| Sales | Adding sales with product & rep assignment |
| Reports | Commission validation (Expected vs Actual) |
