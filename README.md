# API Automation Framework

## Overview
This is a Java-based API automation framework leveraging RestAssured, TestNG, WireMock, and ExtentReports. It supports robust API testing, reporting, configuration management, and mocking for isolated test scenarios.

---

## Features
- **API Testing:** Write and execute automated tests for REST APIs using RestAssured.
- **Mocking:** Use WireMock to stub and mock API endpoints for isolated and reliable tests.
- **Reporting:** Generate rich HTML reports with ExtentReports.
- **Configuration:** Manage environment and test settings using Owner and properties files.
- **Data Modeling:** Use POJOs and builder patterns for request/response payloads.
- **Custom Annotations:** Tag tests with authors and categories for better organization.
- **Parallel Execution:** Supports running tests in parallel for faster feedback.

---

## Project Structure

```
src/
  main/
    java/
      com.apiautomationframework/
        annotations/      # Custom test annotations (e.g., @FrameworkAnnotation)
        config/           # Configuration interfaces and factories
        constants/        # Framework-wide constants
        enums/            # Enums for authors, categories, config properties
        exceptions/       # Custom exception classes
        models/
          pojo/           # POJOs for request/response bodies
          builders/       # Builder classes for requests, responses, tokens, etc.
        reports/          # ExtentReports integration and logging
        utils/            # Utility classes (e.g., FakerUtils, AuthTokenManager)
  test/
    java/
      com.apiautomationframework/
        tests/            # TestNG test classes for various API modules
        wiremock/         # WireMock-based API mocking tests
    resources/
      config/             # Configuration files (config.properties)
```

---

## Getting Started

### Prerequisites
- Java 8 or above
- Maven

### Installation

1. **Clone the repository:**
   ```sh
   git clone <repo-url>
   cd apiautomationframework
   ```

2. **Install dependencies:**
   ```sh
   mvn clean install
   ```

### Configuration

Edit `src/test/resources/config/config.properties` to set environment, credentials, and other settings:

```properties
target = local
browser = chrome
url = https://demo.testfire.net
username = jsmith
password = demo1234
timeout = 3
faker.locale = en-US
headless = false
override_reports = yes
```

### Running Tests

- **All tests:**
  ```sh
  mvn test
  ```

- **Specific Test Class:**
  ```sh
  mvn -Dtest=LoginTest test
  ```

### Mock API Testing

WireMock-based tests are in `src/test/java/com/apiautomationframework/wiremock/APIWireMockTest.java`. These tests demonstrate CRUD operations using mocked endpoints.

---

## Reporting
- HTML reports are generated in the `extent-test-report/` directory.
- Reports are automatically opened after test execution (if supported by your OS).

---

## Key Classes
- **BaseTest:** Handles ExtentReports setup/teardown.
- **LoginTest, AdminTest, AccountTest, UserProfileTest:** Example API test classes.
- **APIWireMockTest:** Demonstrates CRUD API mocking with WireMock.
- **FrameworkAnnotation:** Custom annotation to tag tests with authors and categories.
- **FrameworkConfig:** Centralized configuration using Owner.

---

## Customization
- **Add new API tests:** Create new classes in `src/test/java/com/apiautomationframework/tests/`.
- **Add new endpoints:** Update POJOs/builders in `models/` and add new WireMock stubs as needed.
- **Change configuration:** Edit `config.properties` or extend `FrameworkConfig.java`.

---

## Dependencies
- [RestAssured](https://rest-assured.io/)
- [TestNG](https://testng.org/)
- [WireMock](http://wiremock.org/)
- [ExtentReports](https://extentreports.com/)
- [Owner](https://owner.aeonbits.org/)
- [JavaFaker](https://github.com/DiUS/java-faker)
- [Commons IO](https://commons.apache.org/proper/commons-io/)

---

## Example: Running a Mocked CRUD Test

```java
@Test
public void testCreateResource() {
    Response post = RestAssured.given()
        .header("Content-Type", "application/json")
        .body("{\"name\":\"Created Resource\"}")
        .post("/api/resource");
    System.out.println(post.getBody().asPrettyString());
}
```

---

## Contributing
1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes.
4. Push to the branch.
5. Open a pull request.

---

## License
Specify your license here.

---

**Note:** For more details, refer to the source code and comments in each class.
