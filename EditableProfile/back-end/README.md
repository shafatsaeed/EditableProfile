# springBoot 
 - this is the back end layer of the app using spring boot 

## What's inside 
This project is based on the [Spring Boot](http://projects.spring.io/spring-boot/) project and uses these packages :
- Maven
- Spring Core
- Spring Data (jpa/Hibernate & MySQL)
- Spring security
- Spring MVC (Tomcat)
- Junit4

## Installation 
Make sure you have jdk1.8 or higher is installed. Also you have Netbeans or Eclipse IDE installed.
The project is created with Maven, so you just need to import it to your IDE(Eclipse or Netbeans IDE) and build the project to resolve the dependencies

## Run
Simply right click the project in Netbeans IDE and select Run.

## Database configuration 
Create a MySQL database with the name `spring_auth`and add the credentials to `/resources/application.properties`.  
The default ones are :

```
spring.datasource.driverClassName = com.mysql.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost/spring_auth
spring.datasource.username = root
spring.datasource.password = 
```
