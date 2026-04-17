# Product app

Microservice for managing products in climbing shop.

## Stack
- Java 21
- Quarkus
- PostgreSQL
- GraalVM

## Local run

### Start Postgres container

Refer to ../README.md - root level readme file.

### Start product app

`./mvnw quarkus:dev`

Quarkus Dev UI: <http://localhost:8080/q/dev/>

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```
Requirement: GraalVM

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## OpenApi

Application uses [openApi](https://swagger.io/specification/) to describe HTTP API.`quarkus-smallrye-openapi` works during build phase,
and generate the OpenAPI description. Contract file can be found in src/main/openapi/openapi.yaml. Refer to path defined in 
application.properties: ```quarkus.smallrye-openapi.store-schema-directory=src/main/openapi```.

To run manually:

`./mvnw package`

Or make sure application is up and running - refer to [Start product app](#start-product-app).

Swagger UI : http://localhost:8080/q/swagger-ui/

## Formatter
Application uses [spotless](https://github.com/diffplug/spotless/tree/main/plugin-maven) to format code.
During each compilation spotless check that all classes are properly formatted. But it also possible to check it manually using command

`./mvnw spotless:check`

To fix formatting automatically you must execute

`./mvnw spotless:apply`