# Spring-boot-Angular-9

# JWT Authentication With Spring Boot 2 and Angular 8

# Use Technologies below:

# Backend server

## All of the bellow technologies are used --

Spring Boot 2

Spring Security

Authentication and Authorization using JSON Web Token (JWT) 

Spring Data

Hibernate 5 + JPA 2

H2 Or Mysql Database 

Liquibase

Lombok

Testing frameworks (JUnit, Mockito)

Maven

# Build & run  Maven Spring Boot Applications 

Project name : JavaSpringBootJwtAuthApiServer

goto where pom.xml is keep

`mvn install`

`java -jar target/JavaSpringBootJwtAuthApiServer-0.0.1-SNAPSHOT.jar`

`mvn spring-boot:run`


# Swagger API Docs

When you run our application, specification will be generated. You can check it here:

### http://localhost:8080/v2/api-docs

### http://localhost:8080/swagger-ui.html#/

# Font end server

## Build & run angular 8 Project

Project name : angular 8

# Development server

goto where package.json is keep

### Run `ng serve` for a dev server. Navigate to http://localhost:4200/. The app will automatically reload if you change any of the source files.

# Code scaffolding

Run `ng generate` component component-name to generate a new component. You can also use ng generate directive|pipe|service|class|guard|interface|enum|module.

# Build

Run `ng build` to build the project. The build artifacts will be stored in the dist/ directory. Use the --prod flag for a production build.

# Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

# Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via Protractor.

# Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).


# Architectural Diagram

## Springboot angular o-level diagram
 
![angular Spring](/ScreenshotImage/angularSpring.jpg)

## jwt authentication o-level diagram

![angular Spring](/ScreenshotImage/jwt-architecture-diagram.png)


# Spring Security and JWT Configuration
We will be configuring Spring Security and JWT to perform two operations:

Generating JWT: Expose a POST API with mapping /authenticate. On passing the correct username and password, it will generate a JSON Web Token (JWT).

Validating JWT: If a user tries to access the GET API with mapping /hello, it will allow access only if a request has a valid JSON Web Token (JWT).


# Generating JWT

![angular Spring](/ScreenshotImage/GeneratingJWT.JPG)

![angular Spring](/ScreenshotImage/GeneratingJWT1.JPG)

# Validating JWT
![angular Spring](/ScreenshotImage/ValidatingJWT.JPG)


# project screenshoot

## home page

![angular home](/ScreenshotImage/projecthome.png)
