# TestValidation

Small mock up app for testing  
 **ConstraintViolationException** and **WebApplicationException**
 
  * see **EmployeeResource** for WebApplicationException -> getById()  
 
  * use postman for ConstraintViolationException post request  
 example:
  //missing name -> @NotNull and email is to long -> @Size(max=1)
  {
      "id": 6,
      "email": "too_long@email.com"
  }


How to start the TestValidation application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/test.validation-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
