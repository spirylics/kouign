# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot application named "kouign" built with Maven. It's a web application using Spring Boot 4.0.0-SNAPSHOT with Java 25. The project includes Spring Boot Actuator for monitoring and management endpoints.

### Application Objective

The goal of this application is to implement the OpenAPI REST API for conversing with Large Language Models (LLMs). This enables standardized, REST-based interactions with various LLM providers through a unified API interface.

Another objective is to leverage the latest features of Java 25 and Spring Boot 4, showcasing modern Java development practices and the newest framework capabilities.

## Development Commands

### Build and Run
- **Build the project**: `mvn clean compile`
- **Run the application**: `mvn spring-boot:run`
- **Package the application**: `mvn clean package`

### Testing
- **Run tests**: `mvn test`
- **Run tests in native image**: `mvn test -PnativeTest`

### Native Compilation (GraalVM)
- **Build native executable**: `mvn native:compile -Pnative`
- **Build native container image**: `mvn spring-boot:build-image -Pnative`
- **Run native executable**: `target/kouign`

## Application Configuration

- **Server port**: 9090 (configured in `src/main/resources/application.yml`)
- **Application name**: kouign
- **Main class**: `fr.spirylics.kouign.KouignApplication`

## Architecture

This application follows **Hexagonal Architecture** (Ports & Adapters) principles with **Domain-Driven Design** and **Clean Code** practices, with a simplified and pragmatic approach.

### Architectural Layers

1. **Domain** (`fr.spirylics.kouign.domain`)
   - Pure business logic, independent of frameworks
   - Domain entities as Java records
   - Domain services implementing business rules
   - **Ports defined within domain**:
     - `domain.model.in`: Inbound ports (service interfaces)
     - `domain.model.out`: Outbound ports (repository interfaces)
   - Service implementations using internal record-based use cases
   - No dependencies on outer layers

2. **Application** (`fr.spirylics.kouign.application`)
   - REST controllers exposing domain services
   - Direct exposure of domain entities (no DTOs)
   - Dependency injection of domain services
   - Acts as the entry point for external requests

3. **Infrastructure** (`fr.spirylics.kouign.infrastructure`)
   - Adapters implementing outbound ports
   - Repository implementations
   - External service adapters
   - Framework-specific implementations

4. **Config** (`fr.spirylics.kouign.config`)
   - Spring Bean configuration
   - Wiring of domain services and repositories

### Design Principles

- **Dependency Rule**: Dependencies point inward (Application → Domain ← Infrastructure)
- **Domain Isolation**: Domain layer has no external dependencies
- **Ports in Domain**: Ports (interfaces) are part of the domain, not the application layer
- **No DTOs**: Domain entities are directly exposed via REST APIs (using Java records)
- **Record-based Use Cases**: Business logic encapsulated in internal records within service implementations
- **Clean Code**: SOLID principles, meaningful names, small functions
- **DDD Patterns**: Entities as records, Repositories, Domain Services

### Key Architectural Decisions

- **Simplified layering**: Merged presentation into application layer for pragmatic simplicity
- **Direct entity exposure**: Domain records serve as both domain model and API contract
- **Ports co-located with domain**: Inbound (`in`) and outbound (`out`) ports are subpackages of domain aggregates
- **Record-based pattern**: Use cases implemented as records within service implementations for conciseness

## Project Structure

- **Package**: `fr.spirylics.kouign`
- **Source code**: Simplified hexagonal architecture
  - `domain/`: Core business logic and ports
    - `domain.model/`: Model entity (record)
    - `domain.model.in/`: Inbound ports (service interfaces)
    - `domain.model.out/`: Outbound ports (repository interfaces)
    - `domain.model/ModelServiceImpl`: Service implementation with internal use case records
  - `application/`: REST controllers
  - `infrastructure/`: Repository implementations
  - `config/`: Spring configuration
- **Resources**: `src/main/resources/application.yml`, `src/main/resources/lombok.config`

## Key Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Actuator  
- Spring Boot DevTools (runtime)
- Lombok (code generation)
- Testcontainers (testing)
- Spring Boot Configuration Processor

## Notes

- The project uses Spring Boot DevTools for automatic restarts during development
- Lombok is configured with fluent and chained accessors (`lombok.config`)
- Docker Compose support is available but no services are currently configured
- The project is set up for both traditional JVM and GraalVM native compilation
- REST APIs directly expose domain records without intermediate DTOs

## Project Instructions

- **NEVER start the application yourself**. The application is already running and uses Spring Boot DevTools which automatically reloads when code changes are detected. Do not use `mvn spring-boot:run` or any command to start the application.

- **Avoid code comments**. Only add comments when absolutely necessary for very complex cases. The code should be self-explanatory through clear naming and structure.

- Always use context7 when I need code generation, setup or configuration steps, or
  library/API documentation. This means you should automatically use the Context7 MCP
  tools to resolve library id and get library docs without me having to explicitly ask.

- Use the chrome-devtools-mcp MCP tools when working on frontend-related tasks. This includes
  testing, debugging, or inspecting the web interface, taking screenshots, analyzing performance,
  or interacting with the page elements.

- Use the intellij MCP tools for IDE-related operations. This includes searching for files,
  exploring the codebase structure, analyzing code symbols, refactoring, and inspecting project
  configuration. Prefer IntelliJ tools over command-line alternatives for better integration.