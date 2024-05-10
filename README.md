# Test Automation Framework

This repository contains a robust Test Automation Framework designed to test REST APIs. The framework uses a layered architecture and leverages several powerful tools to provide a scalable and maintainable testing solution.

## Tools Used

- **JUnit 5:** The core framework used for writing and running our tests.
- **RestAssured:** A powerful library for testing REST APIs. It simplifies the process of sending HTTP requests and validating responses.
- **Allure:** A flexible lightweight multi-language test report tool that not only shows a very concise representation of what has been tested in a neat web report form but allows everyone participating in the development process to extract maximum useful information from everyday testing process.
- **Maven:** A build automation tool used primarily for Java projects. It handles project's build, reporting, and documentation from a central piece of information.
- **Docker:** A platform that enables developers to build, package, and distribute applications in containers.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 17
- Maven
- Docker
- Allure Commandline

### Running the tests

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Run the tests using Maven:

```bash
mvn clean test
```

This will run all the tests in the project.

### Generating the Allure report

After running the tests, you can generate the Allure report with the following command:

```bash
allure serve target/allure-results
```

This will generate the report and open it in your default web browser.

### Running the tests in Docker

You can also run the tests in a Docker container. To do this, you need to build a Docker image from the Dockerfile and then run a Docker container from the image.

1. Build the Docker image:

```bash
docker build -t my-test-image .
```

2. Run the Docker container:

```bash
docker run -p 8080:8080 my-test-image
```

The Allure report will be served at `http://localhost:8080`.

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
