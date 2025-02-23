# Banking Microservices System - Spring Boot Application

## Overview
The **Banking Microservices System** is a scalable, distributed application designed to manage bank customer accounts, transactions, and events efficiently. Built using **Spring Boot**, this project follows **clean code principles, SOLID design, and event-driven architecture (EDA)** to ensure extensibility and maintainability.

The system is composed of multiple microservices, including:
- **Account Service**: Manages bank accounts.
- **Customer Service**: Handles customer data.
- **Event Service**: Listens and processes banking events.

## Features
### 🏦 **Account Service**
- Create, update, and retrieve bank accounts.
- Enforce a limit on the number of accounts per customer.
- Integrated event-driven notifications on account creation.

### 👤 **Customer Service**
- Register and manage customer profiles.
- Validate unique legal IDs.
- Retrieve customer details.

### ⚡ **Event Service**
- Uses **Apache Kafka** to handle banking-related events.
- Triggers actions based on customer creation and account initialization.

### 🔐 **Security**
- **Spring Security** for authentication and authorization.
- Supports **JWT-based authentication** (to be implemented).

### 📜 **API Documentation**
- **Swagger UI** integrated for clear API visualization and testing.

### 📦 **Data Persistence**
- Uses **H2 Database** for in-memory storage during development and testing.
- **Spring Data JPA** for repository management.

### 🎭 **Exception Handling**
- Centralized exception management with meaningful error responses.
- Custom exceptions such as `AccountNotFoundException` and `CustomerNotFoundException`.

---

## 🏗 **Technology Stack**
| Technology      | Description  |
|----------------|-------------|
| Java 21       | Latest Java LTS version |
| Spring Boot   | Main framework for microservices |
| Spring Security | Security for authentication & authorization |
| Spring Data JPA | ORM for database operations |
| Feign Client | Communication between microservices |
| Apache Kafka  | Event streaming and message processing |
| H2 Database  | In-memory database for development |
| MapStruct    | DTO to entity mapping |
| JUnit 5 & Mockito | Unit testing |
| Swagger UI   | API documentation |

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
│   ├── AccountServiceApplication.java
│   ├── application.yml       # Configurations
│   └── Dockerfile            # Dockerization
├── customer-service
│   ├── Similar structure as account-service
├── event-service
│   ├── Handles Kafka-based event processing
└── README.md
```

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
| HTTP Method | Endpoint | Description |
|------------|---------|-------------|
| `POST` | `/api/accounts` | Create a new account |
| `GET` | `/api/accounts` | Retrieve all accounts |
| `GET` | `/api/accounts/{id}` | Retrieve account by ID |

### 👤 Customer Service
| HTTP Method | Endpoint | Description |
|------------|---------|-------------|
| `POST` | `/api/customers` | Register a new customer |
| `GET` | `/api/customers` | Retrieve all customers |
| `GET` | `/api/customers/{id}` | Get customer by ID |

### ⚡ Event Service (Kafka Events)
| Topic | Description |
|--------|-------------|
| `customer-events` | Listens to customer creation events |
| `account-events` | Handles account initiation requests |

---

## 🎭 **Exception Handling**
| Exception | Status Code | Description |
|-----------|------------|-------------|
| `AccountNotFoundException` | `404` | Thrown when an account ID doesn’t exist |
| `CustomerNotFoundException` | `404` | Triggered when a customer ID isn’t found |
| `IllegalArgumentException` | `400` | Invalid input provided |
| `RuntimeException` | `500` | General server error |

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
cd account-service && mvn spring-boot:run
cd customer-service && mvn spring-boot:run
cd event-service && mvn spring-boot:run
```

---

## 📦 **Docker Support**
Each service contains a **Dockerfile** for containerization:
```sh
# Build and run the Account Service container
docker build -t account-service ./account-service
docker run -p 8082:8082 account-service

# Build and run the Customer Service container
docker build -t customer-service ./customer-service
docker run -p 8081:8081 customer-service
```

---

## 🔬 **Testing**
Unit tests are implemented using **JUnit 5 & Mockito**.
Run tests with:
```sh
mvn test
```

---

## 🚀 **Future Enhancements**
- Implement **JWT-based authentication**.
- Integrate **external databases** for persistence.
- Expand event-driven architecture for **real-time notifications**.

---

## 👥 **Contributors**
- **[Your Name]** - Lead Developer
- Contributions are welcome! Submit a pull request.

---

## 📜 **License**
Licensed under the **Apache 2.0 License**. See `LICENSE` for details.

