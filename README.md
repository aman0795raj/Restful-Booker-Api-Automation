# API Automation Project with Rest Assured, JUnit, Cucumber, and Extent Report

This project demonstrates API automation testing using Rest Assured, JUnit, Cucumber, and Extent Report. It's specifically designed to test the Restful Booker API.

## Features

- **Rest Assured**: Java library for API testing.
- **JUnit**: Unit testing framework for Java.
- **Cucumber**: BDD framework for writing feature files.
- **Extent Report**: Reporting library to generate interactive HTML reports.

## Pre-requisites

- Java JDK 8 or later installed
- Maven installed
- IDE (Eclipse, IntelliJ IDEA, etc.) with Maven support

## Getting Started

1. **Clone the repository:**

    ```bash
    git clone https://github.com/aman0795raj/Restful-Booker-Api-Automation.git
    ```
2. **Move to the root folder.**
     ```bash
     maven eclipse:eclipse
     ```
3. **Import the project into Eclipse IDE.**

4. **Install dependencies:**

    ```bash
    mvn clean install
    ```

4. **Run the tests:**

    ```bash
    mvn test
    ```

5. **View the Extent Report:**

    After running the tests, navigate to `target/reports/*.html` to view the Extent Report.

## Project Structure

- `src/main/java`: Contains Java source code for utilities, configurations, and helper classes.
- `src/test/java`: Contains test cases written using JUnit.
- `src/test/java/features`: Contains feature files written in Gherkin syntax for Cucumber.
- `target/reports`: Contains Extent Reports generated after test execution.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvement, please open an issue or create a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

Special thanks to Restful Booker for providing the API for testing.

