# Sequifi E2E TC100 - Full Regression Suite

Automated end-to-end test suite for the **Sequifi** platform built with Cucumber BDD, Selenium WebDriver, and TestNG.

---

## Tech Stack

| Tool               | Version  |
|--------------------|----------|
| Java               | 17       |
| Selenium WebDriver | 4.27.0   |
| Cucumber           | 7.20.1   |
| TestNG             | 7.10.2   |
| ExtentReports      | 5.1.2    |
| Maven              | 3.x      |

---

## Project Structure

```
E2ETC100/
├── Feature File/
│   ├── E2ETC100.feature            # Login, Dashboard & Session tests
│   ├── E2ETC200.feature            # Settings E2E (Location -> Position)
│   └── LoginAndAddLocation.feature # Focused location & department tests
│
├── src/test/java/
│   ├── HelperClasses/                        # Page Objects
│   │   ├── pgGeneric.java                    # Base class (driver, waits, dropdowns, dates)
│   │   ├── pgLogin.java                      # Login & Logout
│   │   ├── pgLocation.java                   # Settings > Location
│   │   ├── pgDepartment.java                 # Settings > Department
│   │   ├── pgTiers.java                      # Settings > Tiers
│   │   ├── pgMilestone.java                  # Settings > Milestone
│   │   ├── pgProduct.java                    # Settings > Product
│   │   ├── pgPosition.java                   # Settings > Position
│   │   └── pgProjectExpectedVariables.java   # Cross-scenario test data store
│   │
│   ├── StepDefination/                # Step Definitions
│   │   ├── LoginStepDef.java          # Login / Logout / Hooks
│   │   ├── E2ETC100StepDef.java       # Dashboard & Module verification
│   │   ├── LocationStepDef.java
│   │   ├── DepartmentStepDef.java
│   │   ├── TiersStepDef.java
│   │   ├── MilestoneStepDef.java
│   │   ├── ProductStepDef.java
│   │   └── PositionStepDef.java
│   │
│   └── TestRunner/                    # Cucumber-TestNG Runners
│       ├── E2ETC100Runner.java        # Runs E2ETC100.feature
│       └── TestRunner.java            # Runs E2ETC200.feature
│
├── Reports/                           # Generated HTML reports
├── pom.xml                            # Maven dependencies & build config
└── testng.xml                         # TestNG suite definition
```

---

## Design Patterns

- **Page Object Model (POM)** - Each page/module has its own class under `HelperClasses/` with locators and interaction methods.
- **Base Class Inheritance** - All page objects extend `pgGeneric` which provides driver management, waits, dropdown helpers, and date utilities.
- **BDD (Behavior Driven Development)** - Gherkin feature files define test scenarios in plain English, mapped to Java step definitions.
- **Cross-Scenario State Management** - `pgProjectExpectedVariables` stores test data (names, IDs, dates) in static HashMaps so downstream scenarios can verify previously created entities.
- **Dynamic Test Data** - Random suffixes are appended to names and IDs at runtime to avoid duplicate conflicts across test runs.

---

## Modules Covered

| Module        | Feature Tag     | Scenarios                                         |
|---------------|-----------------|---------------------------------------------------|
| Login         | `@login`        | Valid login, invalid credentials, multi-env login  |
| Dashboard     | `@dashboard`    | Dashboard load, module accessibility, navigation   |
| Session       | `@session`      | Logout, session management                         |
| Location      | `@location`     | Create office & redline-only locations, search     |
| Department    | `@department`   | Create parent/child departments, search            |
| Tiers         | `@tiers`        | Create tier schema, search                         |
| Milestone     | `@milestone`    | Create milestone schema with triggers, search      |
| Product       | `@product`      | Create product with milestone linkage, search      |
| Position      | `@position`     | Create position with all dependencies, search      |

---

## Environment Support

Tests support multiple Sequifi staging environments:

| Environment | URL                                         |
|-------------|---------------------------------------------|
| Solar Stage | `https://solar-stage.sequifi.com`           |
| Pest Stage  | `https://pest-stage.sequifi.com`            |
| Turf Stage  | `https://turf-stage.sequifi.com`            |
| Fiber Stage | `https://fiber-stage.sequifi.com`           |

The target environment is configured via the feature file's `Given` step:
```gherkin
Given user navigates to "Solar Stage" WebPage
```

---

## Prerequisites

- **Java 17** or higher
- **Maven 3.x**
- **Google Chrome** (latest)
- ChromeDriver is auto-managed by Selenium 4.x

---

## How to Run

### Run full suite via Maven
```bash
mvn clean test
```

### Run a specific test suite
```bash
mvn test -Dsurefire.suiteXmlFiles=testng.xml
```

### Run by Cucumber tags
Update the `@CucumberOptions(tags = "...")` in the desired runner class, then:
```bash
mvn test -Dtest=TestRunner.TestRunner
```

**Common tags:**
- `@smoke` - Smoke test scenarios
- `@E2ETC1003` - Login & dashboard regression
- `@E2ETC2008` - Settings E2E flow
- `@settings` - All settings module tests
- `@location`, `@department`, `@tiers`, `@milestone`, `@product`, `@position` - Individual modules

---

## Test Reports

After execution, HTML reports are generated in the `Reports/` directory:

| Report                          | Description                    |
|---------------------------------|--------------------------------|
| `Reports/report.html`           | ExtentReports detailed report  |
| `Reports/CucumberReport.html`   | Cucumber HTML report (E2ETC100)|
| `Reports/Settings_Report.html`  | Cucumber HTML report (E2ETC200)|

---

## Key Utilities in pgGeneric

| Method                  | Description                                              |
|-------------------------|----------------------------------------------------------|
| `selectByVisibleText()` | Select dropdown option by exact text match                |
| `selectByPartialText()` | Select dropdown option by partial/contains text match     |
| `selectRandom()`        | Select a random option from any dropdown                  |
| `setReactDateField()`   | Set date on PrimeReact Calendar via JS React value setter |
| `selectToday()`         | Enter today's date into a date field                      |
| `selectPastDate()`      | Enter a past date (N days back)                           |
| `selectFutureDate()`    | Enter a future date (N days ahead)                        |
| `refreshPage()`         | Refresh the current browser page                          |

---

## E2E Test Flow (E2ETC200)

The Settings E2E suite follows a sequential dependency chain:

```
Login
  -> Create Location
    -> Create Department (uses Location)
      -> Create Tier Schema
        -> Create Milestone Schema (with triggers)
          -> Create Product (links Milestone)
            -> Create Position (links Department, Product, Tier, Milestone)
```

Each step stores its created data in `pgProjectExpectedVariables` so subsequent steps can reference it (e.g., Product links to the last created Milestone Schema).
