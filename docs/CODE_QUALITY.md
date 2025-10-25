# Code Quality Tools

JavaChatX uses several code quality tools to ensure high-quality, maintainable code.

## Tools Overview

### 1. Checkstyle
Checkstyle is a development tool to help programmers write Java code that adheres to a coding standard.

- **Configuration**: [checkstyle.xml](../checkstyle.xml)
- **Execution**: Runs automatically during the build process
- **Rules**: Based on standard Java conventions with some customizations

### 2. JaCoCo (Java Code Coverage)
JaCoCo measures code coverage of Java programs.

- **Reports**: Generated in `target/site/jacoco/` after running tests
- **Integration**: Integrated with CI/CD pipeline and Codecov
- **Goals**: Ensure comprehensive test coverage

### 3. SonarCloud
SonarCloud provides continuous code quality analysis.

- **Dashboard**: [TechCognita-Community/JavaChatX](https://sonarcloud.io/dashboard?id=TechCognita-Community_JavaChatX)
- **Analysis**: Runs on every push to main branch and pull requests
- **Metrics**: Code smells, bugs, vulnerabilities, code coverage, and more

### 4. Codecov
Codecov provides code coverage reporting and monitoring.

- **Integration**: Automatically uploads coverage reports from CI
- **Badge**: [![codecov](https://codecov.io/gh/TechCognita-Community/JavaChatX/branch/main/graph/badge.svg)](https://codecov.io/gh/TechCognita-Community/JavaChatX)

## Running Code Quality Checks Locally

To run all code quality checks locally:

```bash
mvn verify
```

This will execute:
1. Checkstyle validation
2. Unit tests
3. JaCoCo code coverage analysis

## CI/CD Integration

All code quality checks are integrated into the CI/CD pipeline:

1. **GitHub Actions**: Runs on every push and pull request
2. **SonarCloud Analysis**: Continuous static analysis
3. **Codecov Reporting**: Code coverage tracking
4. **Automated Feedback**: Issues are reported directly in pull requests

## Configuration Files

- [checkstyle.xml](../checkstyle.xml) - Checkstyle rules configuration
- [.github/workflows/java-ci.yml](../.github/workflows/java-ci.yml) - CI workflow with quality checks
- [.github/workflows/release.yml](../.github/workflows/release.yml) - Release workflow

## Best Practices

1. **Run checks before committing**: Always run `mvn verify` before pushing code
2. **Fix issues early**: Address code quality issues as soon as they're identified
3. **Maintain high coverage**: Strive for comprehensive test coverage
4. **Follow standards**: Adhere to Checkstyle rules for consistent code formatting
5. **Monitor dashboards**: Regularly check SonarCloud and Codecov dashboards for insights

## Setting Up Locally

To set up code quality tools in your IDE:

### IntelliJ IDEA
1. Install the Checkstyle plugin
2. Configure the plugin to use [checkstyle.xml](../checkstyle.xml)
3. Install the SonarLint plugin for real-time feedback

### Eclipse
1. Install the Checkstyle plugin (Eclipse-CS)
2. Configure the project to use [checkstyle.xml](../checkstyle.xml)
3. Install the SonarLint plugin for real-time feedback

## Future Improvements

Planned enhancements to the code quality setup:

1. **Custom Rules**: Add project-specific Checkstyle rules
2. **PMD Integration**: Add static code analysis with PMD
3. **SpotBugs**: Integrate SpotBugs for bug detection
4. **Automated Formatting**: Add automated code formatting with Google Java Format