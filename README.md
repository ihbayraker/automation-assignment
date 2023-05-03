# Getting Started

- This assignment uses Selenium with BDD Framework to automate web and api testing
- To execute the tests in default configuration locally run the command below through command console.
```sh
$ mvn clean test exec:java
```
- After the execution the report of the results should be fired up automatically, but they can be also found in **target/cucumber-html-report** folder.

# Configuration
- Several parameters can be altered via maven commands.
- By default, the tests will be run on **Chrome** browser **headless** and a report wil be generated however all these can be altered with commands shown below.

| Command | Parameters | Description |
| ------ | ------ | ------ |
| mvn clean test | N/A | Mandatory, runs the tests. |
| exec:java | N/A | Optional, Fires up the reports automatically, should  only used in a local environment. |
| Dcucumber.filter.tags=@all | @all @web @demoqa @bookstore  | Optional, specify the feature tags to manipulate test scope, By default **@all** is run.  |
| Dcucumber.reporting.skip=false | true,false | Optional, determines if the report generation step is skipped or not, by default its **false**. |
| Dbrowser=chrome | chrome, firefox, opera, edge | Optional, determines the browser  test will be run at, by default its **chrome**. |
| Dheadless=true | true, false | Optional, determines if the browser will be run headless or not, by default its **true**. |

- Based on the information given above if we wanted to execute bookstore tests in a CI/CD pipeline using the firefox browser we should use a command like this.
```sh
$ mvn clean test -Dcucumber.reporting.skip=false -Dcucumber.filter.tags=@bookstore -Dbrowser=firefox
```
# Framework
- The test automation framework in this project is designed to be fully modular as such new scenarios or features can be added or included ones be ignored based on what the user wants to automate.
- Executions are controlled with maven, In test step **TestRunner.java** class is run. This class uses properties defined in **cucumber.properties**. In exec:java step **Main.java** is run which only include a short command to fire up locally generated test report.
- Resources folder holds all the feature files and properties files. **demoqa.properties** holds all element paths used in demoqa while **bookstore.properties** holds all apis used in api tests.
- **com.testautomation.utils** package holds the static methods that are called and reused in entire project.
- **com.testautomation.stepdef** package holds the step definitions of all feature files. Any new step definitions should be put in this package.
- **com.testautomation.pojo** package holds the plain objects that are used in api tests.
- **com.testautomation.pageobjects** package holds the page object model for web tests. Each tested module has their own class and interface that holds every method that's called in step definitions.
- **com.testautomation.drivers** package holds the drivers. The **DriverFactory.java** determines the driver based on the configuration put by the user and **DriverManager.java** manages it. All drivers have the same functionality.
- **com.testautomation.api** holds request classes for each api tested in api tests. Each api has their own class and interface that holds every method that is called in step definitions.


[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ismail-hakan-bayraker/) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![Selenium](https://img.shields.io/badge/-selenium-%43B02A?style=for-the-badge&logo=selenium&logoColor=white)