## Testing Document (WIP)

### Unit tests
Unit testing is done with JUnit, with reports from Jacoco. Interfaces are used for dependency injection testing, needed to cover methods that utilise random numbers, as well as with few void methods that mainly call other methods.

Tests can be run with command 
```
mvn test jacoco:report
```
and the report is found at target/site/jacoco/index.html.

### Plans for system testing
- A crawler that goes through all the available squares, making sure they're all accessible.
- Measuring how time use is affected by the parameters. Presentation with graphs.

![Test coverage on week 4](https://github.com/Granigan/dungeongenerator/blob/master/documents/images/tests_week4.png)

*Test coverage on week four.*


![Test coverage on week 3](https://github.com/Granigan/dungeongenerator/blob/master/documents/images/tests_week3.png)

*Test coverage on week three.*


![Test coverage on week 2](https://github.com/Granigan/dungeongenerator/blob/master/documents/images/tests_week2.png)

*Test coverage on week two.*
