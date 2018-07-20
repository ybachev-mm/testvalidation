# TestValidation

Small mock up app for testing  
 **ConstraintViolationException** and **WebApplicationException**
 
  * see **EmployeeResource** for WebApplicationException -> getById()  
 
  * use postman for ConstraintViolationException post request  
 example:
  ```java
  //missing name -> @NotNull and email is to long -> @Size(max=1)
 
  {
      "id": 6,
      "email": "too_long@email.com"
  }
  // response
Parameter email size must be between 0 and 1 (was too_long@email.com), for class test.validation.db.model.Employee
Parameter name may not be null (was null), for class test.validation.db.model.Employee
```

How to start the TestValidation application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/test.validation-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
