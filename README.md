# Banking Microservices System - Spring Boot Application

## Overview

The **Banking Microservices System** is a scalable, distributed application designed to manage bank customer accounts,
transactions, and events efficiently. Built using **Spring Boot**, this project follows **clean code principles, SOLID
design, and event-driven architecture (EDA)** to ensure extensibility and maintainability.

The system is composed of multiple microservices, including:

- **Account Service**: Manages bank accounts.
- **Customer Service**: Handles customer data.
- **Event Service**: Listens and processes banking events.
- **Discovery Service**: Eureka Server for service registration and discovery.
- **Gateway Service**: API Gateway for routing and load balancing.

## Features

### 🏦 **Account Service**

- Create, update, and retrieve bank accounts.
- Enforce a limit on the number of accounts per customer.
- Integrated event-driven notifications on account creation.
- **Custom Query Methods using Spring Data JPA**
- **JDBI for optimized SQL queries**

### 👤 **Customer Service**

- Register and manage customer profiles.
- Validate unique legal IDs.
- Retrieve customer details.

### ⚡ **Event Service**

- Uses **Apache Kafka** to handle banking-related events.
- Triggers actions based on customer creation and account initialization.

### 🔍 **Discovery Service (Eureka Server)**

- Acts as a service registry to discover and register microservices dynamically.
- Enables load balancing and resilience within the microservices architecture.

### 🚪 **Gateway Service (Spring Cloud Gateway)**

- Routes API requests to the appropriate microservices.
- Provides a single entry point for all client requests.
- Enhances security, logging, and monitoring.

### 🔐 **Security**

- **Spring Security** for authentication and authorization.
- Supports **JWT-based authentication** (to be implemented).

### 📜 **API Documentation**

- **Swagger UI** integrated for clear API visualization and testing.
- **JavaDocs** are added across all layers to enhance code readability and maintainability.

### 📦 **Data Persistence**

- Uses **H2 Database** for in-memory storage during development and testing.
- **Spring Data JPA** for repository management.

### 🎭 **Exception Handling**

- Centralized exception management with meaningful error responses.
- Custom exceptions such as `AccountNotFoundException` and `CustomerNotFoundException`.

### 📊 **Aspect-Oriented Programming (AOP) for Logging**
- Added **Spring AOP** to log method executions in the service layer.
- Logs method entry, execution time, and method exit.
- Improves debugging and performance monitoring.

### 🧪 **Professional Testing Approach**

- Unit tests are structured professionally using **JUnit 5 & Mockito**.
- Each test class contains **JavaDocs** explaining its purpose and behavior.
- Mocks are used to isolate dependencies and validate method interactions.
- **Behavior-Driven Testing (BDD)** ensures real-world scenario coverage.

---

## 🏗 **Technology Stack**

| Technology        | Description                                 |
|-------------------|---------------------------------------------|
| Java 21           | Latest Java LTS version                     |
| Spring Boot       | Main framework for microservices            |
| Spring Security   | Security for authentication & authorization |
| Spring Data JPA   | ORM for database operations                 |
| JDBI             | Lightweight SQL handling                     |
| Feign Client      | Communication between microservices         |
| Apache Kafka      | Event streaming and message processing      |
| H2 Database       | In-memory database for development          |
| MapStruct         | DTO to entity mapping                       |
| JUnit 5 & Mockito | Unit testing                                |
| Swagger UI        | API documentation                           |
| Spring AOP        | Aspect-Oriented Programming for logging     |

---

## 📂 **Project Structure**

```
├── account-service
│   ├── api
│   │   ├── controller        # API controllers
│   │   ├── dto               # Request & Response DTOs
│   ├── service               # Business logic layer
│   ├── repository            # Data access layer
│   ├── events                # Kafka event consumers & producers
│   ├── infra
│   │   ├── exception         # Custom exceptions
│   │   ├── security          # Security config
│   │   ├── mapper            # DTO to entity mappers
│   │   ├── aop               # Logging aspects
│   ├── AccountServiceApplication.java
│   ├── application.yml       # Configurations
│   └── Dockerfile            # Dockerization
├── customer-service
│   ├── Similar structure as account-service
├── event-service
│   ├── Handles Kafka-based event processing
├── discovery-service
│   ├── Eureka Server Configuration
├── gateway-service
│   ├── Spring Cloud Gateway Configuration
└── README.md
```

---

## 🌍 **API Gateway (Spring Cloud Gateway) and Service Discovery (Eureka)**

### ✅ **Gateway Configuration**

- Routes requests dynamically using **Spring Cloud Gateway**.
- Example routes:
  ```yaml
  spring.cloud.gateway.routes[0].id=customer-service
  spring.cloud.gateway.routes[0].uri=http://localhost:8081
  spring.cloud.gateway.routes[0].predicates[0]=Path=/api/customers/**

  spring.cloud.gateway.routes[1].id=account-service
  spring.cloud.gateway.routes[1].uri=http://localhost:8082
  spring.cloud.gateway.routes[1].predicates[0]=Path=/api/accounts/**
  ```

### ✅ **Eureka Service Discovery**

- Registers all microservices dynamically.
- Configured in each service:
  ```properties
  eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
  ```
---

## 🏗 **Design Patterns & Clean Code Principles**

### 🔹 **Design Patterns Implemented**

| Pattern                             | Purpose                                                                   |
|-------------------------------------|---------------------------------------------------------------------------|
| **Layered Architecture**            | Separates concerns (Controller, Service, Repository)                      |
| **DTO Pattern**                     | Encapsulates request & response objects to avoid exposing database models |
| **Builder Pattern**                 | Used with Lombok `@Builder` for clean object creation                     |
| **Factory Pattern**                 | Centralized account creation logic                                        |
| **Repository Pattern**              | Abstracts data persistence logic via Spring Data JPA                      |
| **Service Locator Pattern**         | Used with **Spring Cloud Feign Clients** to locate services dynamically   |
| **Event-Driven Architecture (EDA)** | Kafka-based events for asynchronous processing                            |
| **Strategy Pattern**                | Handles different types of accounts dynamically                           |
| **Circuit Breaker (Resilience4j)**  | Ensures system resilience by handling failures in Feign Clients           |
| **Aspect-Oriented Programming (AOP)** | Logs method execution in the service layer                              |

---

### 🛠 **Clean Code Practices**

- **SOLID Principles**: Each microservice follows **Single Responsibility** and **Dependency Inversion**.
- **Transactional Consistency**: Ensures data consistency using `@Transactional` in service methods.
- **Proper Logging & Exception Handling**: Centralized exception handling using `@RestControllerAdvice`.
- **Scalability & Extensibility**: Uses **CQRS (Command Query Responsibility Segregation)** for optimized reads &
  writes.

---

## 🚀 **How It Works**

### ✅ **Account Creation Flow**

1. **Client Requests**: A user sends a request to create an account.
2. **Validation**: System validates the customer ID and ensures the account limit isn’t exceeded.
3. **Persistence**: Account details are stored in the database.
4. **Event Triggered**: An account initiation event is published to **Kafka**.
5. **Event Processing**: The **Event Service** listens and processes the event.

### 🔄 **Customer Registration & Event Handling**

1. **New Customer Registered** → Event emitted.
2. **Event Service Receives Event** → Triggers **automatic account creation**.
3. **Account Service Processes Request** → Default account created.

---

## 🌍 **API Endpoints**

### 🏦 Account Service

| HTTP Method | Endpoint             | Description            |
|-------------|----------------------|------------------------|
| `POST`      | `/api/accounts`      | Create a new account   |
| `GET`       | `/api/accounts`      | Retrieve all accounts  |
| `GET`       | `/api/accounts/{id}` | Retrieve account by ID |

### 👤 Customer Service

| HTTP Method | Endpoint              | Description             |
|-------------|-----------------------|-------------------------|
| `POST`      | `/api/customers`      | Register a new customer |
| `GET`       | `/api/customers`      | Retrieve all customers  |
| `GET`       | `/api/customers/{id}` | Get customer by ID      |

### ⚡ Event Service (Kafka Events)

| Topic             | Description                         |
|-------------------|-------------------------------------|
| `customer-events` | Listens to customer creation events |
| `account-events`  | Handles account initiation requests |

---

## 🎭 **Exception Handling**

| Exception                   | Status Code | Description                              |
|-----------------------------|-------------|------------------------------------------|
| `AccountNotFoundException`  | `404`       | Thrown when an account ID doesn’t exist  |
| `CustomerNotFoundException` | `404`       | Triggered when a customer ID isn’t found |
| `IllegalArgumentException`  | `400`       | Invalid input provided                   |
| `RuntimeException`          | `500`       | General server error                     |

---

## 📜 **Swagger Documentation**
Swagger is integrated using **Springdoc OpenAPI** to automatically generate API documentation.
- To access the Swagger UI, navigate to:
```
http://localhost:8081/swagger-ui/index.html // For Customer Serivce
```

```
http://localhost:8082/swagger-ui/index.html // For Account Service
```

---

## 🗄️ **Database Console**
The **H2 Database** Console is available for accessing and managing the in-memory database for each microservice.
- To access the H2 Database Console, navigate to:
```
http://localhost:8081/db-console // For Customer Serivce
```

```
http://localhost:8082/db-console // For Account Service
```

---

## 🛠 **How to Run Locally**

### Prerequisites

- Java 21 installed
- Maven installed
- Kafka running on `localhost:9092`

### Steps to Run

```sh
# Clone the repository
git clone https://github.com/your-repo/banking-microservices.git
cd banking-microservices

# Build the services
mvn clean install

# Start services
cd discovery-service && mvn spring-boot:run
cd gateway-service && mvn spring-boot:run
cd account-service && mvn spring-boot:run
cd customer-service && mvn spring-boot:run
cd event-service && mvn spring-boot:run
```

---

## 📦 **Docker Support**

The project includes **Docker Compose** for containerized deployment of all microservices, along with **Kafka** and **Zookeeper** for event-driven communication.
- Each service contains a **Dockerfile** for containerization.

### 🚀 **Running the Services**

To build and start all services, simply run:
```sh
docker-compose up --build
```
This will:
- Build and start **Customer Service**, **Account Service**, **Event Service**, **Discovery Service**, and **Gateway Service**.
- Start **Kafka** and **Zookeeper** for event streaming.
- Automatically set up the required environment variables.

To stop the services, use:
```sh
docker-compose down
```

---

## 🔬 **Testing**

- Tests follow a **structured approach** with **clear JavaDocs** for each class and method.
- **Mockito** is used to mock dependencies and verify method interactions.
- **Event-driven tests** ensure Kafka-based event flows work correctly.
- Coverage includes **unit tests**, **integration tests**, and **exception handling scenarios**.

Run tests with:

```sh
mvn test
```

---

## 🚀 **Future Enhancements**

- Implement **JWT-based authentication**.
- Integrate **external databases** for persistence.

---

## 👥 **Contributors**

- **[Abdl02]** - Lead Developer
- Contributions are welcome! Submit a pull request.

---