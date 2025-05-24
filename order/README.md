# Order app

Microservice for creating orders in climbing shop. 

## Stack
- Java 21
- Spring Boot
- PostgreSQL

## Local run

### Start Postgres container

Refer to ../README.md - root level readme file.

### Start order app

`./mvnw spring-boot:run`

## OpenApi

Application uses [openApi](https://swagger.io/specification/) to describe HTTP API. `springdoc-openapi-maven-plugin` works during integration-tests phase, 
and generate the OpenAPI description. The plugin works in conjunction with spring-boot-maven plugin. Contract file can be found in 
src/main/openapi/openapi.yaml. 

To run manually: 

`./mvnw verify`

Or make sure application is up and running and then execute `./mvnw springdoc-openapi:generate`.

Swagger UI : http://localhost:8080/swagger-ui/index.html

## Formatter
Application uses [spotless](https://github.com/diffplug/spotless/tree/main/plugin-maven) to format code.
During each compilation spotless check that all classes are properly formatted. But it also possible to check it manually using command

`./mvnw spotless:check`

To fix formatting automatically you must execute

`./mvnw spotless:apply`
