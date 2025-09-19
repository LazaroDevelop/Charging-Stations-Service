# Read Me First

The following was discovered as part of building this project:

* The JVM level was changed from '1.8' to '17', review
  the [JDK Version Range](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Versions#jdk-version-range)
  on the wiki for more details.

# Getting Started

# Charging Stations Service

A Spring Boot microservice for managing charging stations.  
This project demonstrates RESTful APIs with persistence, caching, and containerization support.

### Reference Documentation

---

## 🚀 Tech Stack

- **Java**: 8  
- **Spring Boot**: 2.7.14  
- **Persistence**: Spring Data JPA, MySQL  
- **Caching**: Redis  
- **Testing**: JUnit 4, Mockito  
- **Documentation**: Swagger / OpenAPI  
- **Containerization**: Docker  
- **Build Tool**: Maven  

---
  
## ⚙️ Setup & Installation

To run the backend application I recommend using the Maven(CLI) to start the Spring Tomcat server:
* Execute the command ``` mvn dependency:resolve ``` to search application dependencies.
* Execute the command ``` mvn clean install ``` to generate the resources, run tests and build the application.
* Execute the command ``` mvn test ``` to run only the unitary tests.
* Execute the command ``` mvn spring-boot:run ``` to set up the application and then you can consume the REST API in the default port 8080.
* To see the documentation -> http://your_host:your_port/swagger-ui/index.html

---

## 🐳 Docker Image

You can also run the service using Docker:
```docker pull dokcerlngm/lazaro-developer```

---

### 🔑 Environment Variables

| Service | Variable            | Default Value       |
| ------- | ------------------- | ------------------- |
| MySQL   | `MYSQL_HOST`        | localhost           |
|         | `MYSQL_PORT`        | 3366                |
|         | `MYSQL_DB`          | charge\_station\_db |
|         | `MYSQL_USER`        | root                |
|         | `MYSQL_PASSWORD`    | 1234                |
| Redis   | `REDIS_SERVER_HOST` | localhost           |
|         | `REDIS_SERVER_PORT` | 6379                |
| Service | `SERVICE_PORT`      | 8081                |

---

### Guides

---

## 📚 Useful References

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Dockerizing a Spring Boot Application](https://www.baeldung.com/dockerizing-spring-boot-application)
