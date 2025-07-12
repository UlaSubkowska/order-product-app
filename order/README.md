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

## Contract tests

Application uses `spring-cloud-starter-contract-verifier` to tests contract. Tests are generated base on test/java/.../order/controller/ControllerBase.java 
and contracts keep in test/resources/contracts/Controller directory as a yaml files.   
To run tests locally:  
`./mvnw verify`  
To see tests generated in target/generated-test-sources package is enough:  
`./mvnw package`

## Formatter
Application uses [spotless](https://github.com/diffplug/spotless/tree/main/plugin-maven) to format code.
During each compilation spotless check that all classes are properly formatted. But it also possible to check it manually using command

`./mvnw spotless:check`

To fix formatting automatically you must execute

`./mvnw spotless:apply`

### Local config: execute spotless automatically before commit

- navigate to .git/hooks dir
- add pre-commit file with such content: 
```
  #!/bin/sh
  mvn spotless:check
  if [ $? -ne 0 ]; then
  echo "Spotless checks failed. Execute apply and add everything"
  mvn spotless:apply
  git add .
  exit 1
  fi
```
- make pre-commit file executable: `chmod +x pre-commit`

