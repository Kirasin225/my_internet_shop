###The technology stack used in this project
- Java 21
- SpringBoot
- Postgres + Liquibase
- Maven
- Thymeleaf

##Prerequisites
- Java 21

##Start the server:
For local run, you can start Postgres DB in docker container. Don't forget to execute `docker rm <continer id>`
after stopping application server.

For running server locally, execute the following commands:
```
./mvnw clean install -Dmaven.test.skip=true 
docker run --name p1 -p 5433:5432 -e POSTGRES_PASSWORD=1234 -d postgres
java -jar target/my_internet_shop_pj-0.1.jar
```