# Order and product app

Two microservices order and product for climbing shop.

Project for learning purpose, huge thanks to viniciustoni.

## Stack
- Java 21
- Spring Boot
- Quarkus
- PostgreSQL
- Docker

## Run locally 

### Start Postgres database containers

From root directory:

`cd docker && docker-compose up`

Command starts two docker containers with a Postgres. One is DB for order app, second for product app. There is also init migration in docker/config/postgres.

### Start order app

Refer to order/README.md file.

### Start product app 

Refer to product/README.md file.

## Manual test

For manual testing purpose repository contain [bruno](https://www.usebruno.com/) directory with collection of http calls. 

![img.png](doc/img.png)

## Local config: execute spotless automatically before commit
NOTE: does not work for this repo, probably because of the need of separate spotless for each service (Spring and Quarkus) 

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
