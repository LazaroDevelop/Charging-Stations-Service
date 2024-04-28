# Read Me First

The following was discovered as part of building this project:

* The JVM level was changed from '1.8' to '17', review
  the [JDK Version Range](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Versions#jdk-version-range)
  on the wiki for more details.

# Getting Started

Charging Stations Service

### My Recommendations

- Use Java 8
- Use Maven to run Spring Boot applications
- Remember: Spring Boot 2+ works with Java 8+

### Reference Documentation

Tecnologies
* Spring
* Docker
* Redis
* Mysql
* Mockito
* JSON
* Swagger Doc

## Dependencies

* Spring Boot - 2.7.14
* Spring Data Jpa 
* Mysql
* Lombok
* JUnit4
* Redis Cache server
* Swagger
  
### Running backend app

To run the backend application I recommend using the Maven(CLI) to start the Spring Tomcat server:
* Execute the command ``` mvn dependency:resolve ``` to search application dependencies.
* Execute the command ``` mvn clean install ``` to generate the resources, run tests and build the application.
* Execute the command ``` mvn test ``` to run only the unitary tests.
* Execute the command ``` mvn spring-boot:run ``` to set up the application and then you can consume the REST API in the default port 8080.
* To see the documentation -> http://your_host:your_port/swagger-ui/index.html
* To pull a docker image of this service you can access to [Docker Hub Repository](https://hub.docker.com/r/dokcerlngm/lazaro-developer) and make  ``` docker pull dokcerlngm/lazaro-developer  ```

### Environments variables
* For Mysql
  - MYSQL_HOST:localhost
  - MYSQL_PORT:3366
  - MYSQL_DB:charge_station_db
  - MYSQL_USER:root
  - MYSQL_PASSWORD:1234
* For Redis Cache Server
  - REDIS_SERVER_HOST:localhost
  - REDIS_SERVER_PORT:6379
* For expose a specific port in the service
  - SERVICE_PORT:8081

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Dockerizing a Spring Boot Application](https://www.baeldung.com/dockerizing-spring-boot-application)
