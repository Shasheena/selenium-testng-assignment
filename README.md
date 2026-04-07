# Selenium WebDriver with TestNG Assignment

## Project Overview
This project is developed as part of the **Selenium WebDriver with TestNG Assignment**. It demonstrates automation testing concepts using Selenium WebDriver integrated with the TestNG framework.

The project covers browser automation, element interaction, TestNG features, and advanced testing techniques using the eBay website.

## Technologies Used
- Java 24
- Selenium WebDriver 4.18.1
- WebDriverManager 5.8.0
- TestNG 7.10.2
- Maven
- IntelliJ IDEA

## Test Application
- https://www.ebay.com

## Project Structure

```
selenium-testng-assignment/
├── src/
│   ├── main/java/
│   └── test/java/
│       ├── question01/
│       │   ├── BrowserCommands.java
│       │   ├── FormInteraction.java
│       │   └── ElementInteraction.java
│       └── question02/
│           ├── TestNGBasicsTest.java
│           ├── TestExecutionControlTest.java
│           ├── AssertionsTest.java
│           ├── DataProviderTest.java
│           └── DependencyTest.java
├── testng.xml
├── pom.xml
├── README.md
```

## Question 01: Selenium WebDriver Core Commands

### BrowserCommands.java
- Browser setup using ChromeDriver
- Navigation to eBay
- Page information extraction (title, URL, source)
- Assertion validation
- Proper browser cleanup

### FormInteraction.java
- Advanced search navigation
- Keyword input validation
- Category selection using Select class
- Price range configuration
- Checkbox interactions
- Search execution and result validation

### ElementInteraction.java
- Locator strategies (ID, Name, Class, CSS, XPath)
- Text input handling
- Dropdown handling
- Checkbox & radio button interactions
- Button clicking
- Text and attribute extraction

## Question 02: TestNG Framework Fundamentals

### TestNGBasicsTest.java
- @BeforeClass, @AfterClass
- @BeforeMethod, @AfterMethod
- Multiple test methods with execution flow logs

### TestExecutionControlTest.java
- Priority-based test execution
- Realistic login flow simulation
- Input validation and submission tests

### AssertionsTest.java
#### Hard Assertions:
- assertEquals()
- assertTrue(), assertFalse()
- Stops execution on failure

#### Soft Assertions:
- Multiple validations in one test
- Uses SoftAssert
- assertAll() to collect failures

### DataProviderTest.java
- Data-driven testing using @DataProvider
- Multiple datasets (valid, category-based, edge cases)
- Same test runs with different inputs

### ParameterizationTest.java
- Parameters passed via testng.xml
- Suite-level: browser, baseURL
- Test-level: username, password
- Multiple test executions using different data

### DependencyTest.java
- dependsOnMethods (single & multiple)
- Demonstrates skipped tests when dependency fails
- alwaysRun = true for cleanup


## How to Run the Project

### Question 1 Tests (Standalone Java classes)
Right-click on each class and select "Run"

### Question 2 Tests (TestNG)
1. Right-click on testng.xml → Run
2. Or run individual test classes

## Important Notes
- Login automation is limited due to eBay security (CAPTCHA)
- WebDriverWait is used instead of Thread.sleep()
- Test failures are intentionally added to demonstrate behavior
- Chromedriver is NOT included in submission zip

## Key Concepts Demonstrated
- Selenium WebDriver automation
- TestNG annotations and execution control
- Assertions (Hard & Soft)
- Data-driven testing
- Parameterization
- Test dependencies

## Author
Student Name: B.M.S. Nethmini
Student ID: AS2022589 

