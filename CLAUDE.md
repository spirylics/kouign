# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot application named "kouign" built with Maven. It's a web application using Spring Boot 4.0.0-SNAPSHOT with Java 25. The project includes Spring Boot Actuator for monitoring and management endpoints.

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

This application follows **Hexagonal Architecture** (Ports & Adapters) principles with **Domain-Driven Design** and **Clean Code** practices.

### Architectural Layers

1. **Domain** (`fr.spirylics.kouign.domain`)
   - Pure business logic, independent of frameworks
   - Domain entities, value objects, and aggregates
   - Domain services and business rules
   - No dependencies on outer layers

2. **Application** (`fr.spirylics.kouign.application`)
   - Use cases and application services
   - Orchestrates domain logic
   - Defines input/output ports (interfaces)
   - Transaction boundaries

3. **Infrastructure** (`fr.spirylics.kouign.infrastructure`)
   - Adapters implementing ports
   - Persistence adapters (repositories)
   - External service adapters
   - Framework-specific implementations

4. **Presentation** (`fr.spirylics.kouign.presentation`)
   - REST controllers, DTOs
   - Input adapters (API layer)
   - Request/response mapping

### Design Principles

- **Dependency Rule**: Dependencies point inward (Presentation → Application → Domain)
- **Domain Isolation**: Domain layer has no external dependencies
- **Ports & Adapters**: Application defines interfaces, infrastructure implements them
- **Clean Code**: SOLID principles, meaningful names, small functions
- **DDD Patterns**: Entities, Value Objects, Aggregates, Repositories, Domain Events

## Project Structure

- **Package**: `fr.spirylics.kouign`
- **Source code**: Hexagonal architecture structure
  - `domain/`: Core business logic
  - `application/`: Use cases and ports
  - `infrastructure/`: Adapters and implementations
  - `presentation/`: REST APIs and DTOs
- **Resources**: `src/main/resources/application.yml`
- **Test configuration**: Uses Testcontainers for integration testing

## Key Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Actuator  
- Spring Boot DevTools (runtime)
- Lombok (code generation)
- Testcontainers (testing)
- Spring Boot Configuration Processor

## Notes

- The project uses Spring Boot DevTools for automatic restarts during development
- Lombok is configured for annotation processing
- Docker Compose support is available but no services are currently configured
- The project is set up for both traditional JVM and GraalVM native compilation

## Project Instructions

- Always use context7 when I need code generation, setup or configuration steps, or
  library/API documentation. This means you should automatically use the Context7 MCP
  tools to resolve library id and get library docs without me having to explicitly ask.

- Use the chrome-devtools-mcp MCP tools when working on frontend-related tasks. This includes
  testing, debugging, or inspecting the web interface, taking screenshots, analyzing performance,
  or interacting with the page elements.