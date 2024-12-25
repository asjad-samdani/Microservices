# Microservices Architecture

## Overview

This repository demonstrates the implementation of a **microservices architecture** using Spring Boot. Microservices break down a large application into smaller, independent services that communicate with each other via APIs.

---

## Key Features

- **Independent Services**: Each microservice operates independently and can be deployed separately.
- **RESTful APIs**: Services communicate through HTTP using REST APIs.
- **Service Discovery**: Enables dynamic discovery of services (e.g., Eureka).
- **Centralized Configuration**: Configuration management using Spring Cloud Config.
- **Load Balancing**: Distributes traffic efficiently (e.g., using Ribbon or Spring Cloud Gateway).
- **Fault Tolerance**: Implements resilience patterns like circuit breakers (Hystrix/Resilience4j).
- **Database Per Service**: Each service has its own database, ensuring data independence.

---

## Services in the Architecture

### 1. **User Service**

- Manages user information.
- CRUD operations for users.
- Connects to the Address Service for additional user-related data.

### 2. **Address Service**

- Handles address data.
- CRUD operations for addresses.
- Exposes APIs consumed by the User Service.

### 3. **API Gateway**

- Acts as a single entry point for all microservices.
- Handles routing, load balancing, and security.

### 4. **Service Registry**

- Manages the discovery of services.
- E.g., Eureka Server for dynamic service registration and discovery.

---

## Prerequisites

- **Java 17+**
- **Spring Boot 3+**
- **Maven or Gradle**
- **Docker** (for containerized deployments)

---

## Architecture Diagram

```plaintext
                    +--------------------+
                    |  API Gateway       |
                    +--------------------+
                             |
          +------------------+------------------+
          |                                     |
+--------------------+               +--------------------+
|   User Service     |               |  Address Service   |
+--------------------+               +--------------------+
          |                                     |
+--------------------+               +--------------------+
| User Database      |               | Address Database   |
+--------------------+               +--------------------+

```
