# ⚡ Charging Stations Service

A **Spring Boot REST API** for managing **electric vehicle charging stations**: register stations, look them up by location, and check whether a charger is **available** or **in use**. Station data is stored in **MySQL** and frequently read data is cached in **Redis**.

## 📚 Table of contents
- [What it does](#-what-it-does)
- [Tech stack](#-tech-stack)
- [Run it](#-run-it)
- [API tour](#-api-tour)
- [Configuration](#%EF%B8%8F-configuration)
- [Testing](#-testing)
- [Roadmap](#%EF%B8%8F-roadmap)
- [Author](#-author)

## ✨ What it does
- 🔌 Full **CRUD** for charging stations
- 📍 Find a station by its **location** (address, latitude, longitude)
- 🚦 Check the **status** of a station: `AVAILABLE` or `IN_USE`
- ⚡ Supports two **charger types**: `AC` and `DC_FAST_CHARGE`
- 🧠 **Redis caching** to avoid hitting the database on repeated reads
- 📄 **Swagger UI** documentation generated with Springfox

## 🛠️ Tech stack
| Layer | Technology |
|-------|------------|
| Language | Java 8 |
| Framework | Spring Boot 2.7 (Web, Data JPA, Cache) |
| Database | MySQL |
| Cache | Redis |
| Docs | Springfox (Swagger) |
| Testing | JUnit 5, Mockito, MockMvc |
| Container | Docker |

## 🚀 Run it

### Prerequisites
- Java 8+ and Maven
- A running **MySQL** and **Redis** instance (local or Docker)

### Start MySQL and Redis with Docker
~~~bash
docker run -d --name charging-mysql -p 3366:3306 \
  -e MYSQL_ROOT_PASSWORD=change-me -e MYSQL_DATABASE=charge_station_db mysql:8

docker run -d --name charging-redis -p 6379:6379 redis:7
~~~

### Run the service
~~~bash
git clone https://github.com/LazaroDevelop/Charging-Stations-Service.git
cd Charging-Stations-Service
mvn spring-boot:run
~~~

The API starts on **http://localhost:8081**.
Swagger UI: **http://localhost:8081/swagger-ui/index.html**

### Run with Docker
~~~bash
mvn clean package -DskipTests
docker build -t charging-stations-service .
docker run -p 8081:8081 charging-stations-service
~~~

## 📡 API tour
Base path: `/v1/api/charging-stations`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/all` | List all charging stations |
| `POST` | `/create` | Create a new charging station |
| `GET` | `/find-by-id/{id}` | Get a station by ID |
| `PUT` | `/update/{id}` | Update a station |
| `DELETE` | `/delete/{id}` | Delete a station |
| `POST` | `/find-by-location` | Find a station by location (request body) |
| `GET` | `/check-status/{id}` | Get the current status of a station |

### Example: create a station
~~~bash
curl -X POST http://localhost:8081/v1/api/charging-stations/create \
  -H "Content-Type: application/json" \
  -d '{
    "address": "Av. 18 de Julio 1234, Montevideo",
    "latitude": -34.9055,
    "longitude": -56.1913,
    "chargerType": "DC_FAST_CHARGE",
    "numberOfChargingPoints": 4,
    "status": "AVAILABLE"
  }'
~~~

## ⚙️ Configuration
All settings live in `src/main/resources/application.properties` and can be overridden with environment variables:

| Variable | Default | Description |
|----------|---------|-------------|
| `SERVICE_PORT` | `8081` | HTTP port |
| `MYSQL_HOST` | `localhost` | MySQL host |
| `MYSQL_PORT` | `3366` | MySQL port |
| `MYSQL_DB` | `charge_station_db` | Database name |
| `REDIS_SERVER_HOST` | `localhost` | Redis host |
| `REDIS_SERVER_PORT` | `6379` | Redis port |

## ✅ Testing
The project includes a **MockMvc** test for the controller and a **Mockito** unit test for the service layer.
~~~bash
mvn test
~~~

## 🗺️ Roadmap
- [ ] Upgrade to Java 21 and Spring Boot 3
- [ ] Replace Springfox with springdoc-openapi
- [ ] Add a `docker-compose.yml` with MySQL and Redis
- [ ] Add a GitHub Actions build

## 👤 Author

**Lazaro Noel Guerra Medina**, Java Backend Engineer

> *"Make it work, make it right, make it fast."* ☕
