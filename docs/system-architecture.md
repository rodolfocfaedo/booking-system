# System Architecture

This document describes the initial architecture of the Booking System project.

---

## Architectural Style

The project follows a layered architecture, which is common in backend systems built with Spring Boot.

Layers:

- Controller
- Service
- Repository
- Domain

This structure helps separate responsibilities and keeps the codebase easier to maintain and evolve.

---

## Layer Responsibilities

### Controller

Responsible for handling HTTP requests and returning HTTP responses.

### Service

Responsible for business logic and application rules.

### Repository

Responsible for database access.

### Domain

Represents the core business entities of the system.

Main entities:

- User
- Resource
- Booking

---

## Initial Flow

Client  
→ Controller  
→ Service  
→ Repository  
→ PostgreSQL

---

## Future Evolution

The architecture may evolve to include:

- Spring Security and JWT
- Docker and containerized environments
- CI/CD pipelines
- Microservices
- Event-driven communication
