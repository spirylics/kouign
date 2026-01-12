# GEMINI.md

This file provides guidance to the Gemini CLI when working with code in this repository.

## Project Overview

This is a Spring Boot application named "kouign" built with Maven. It's a web application using Spring Boot 4.0.0-SNAPSHOT with Java 25. The project includes Spring Boot Actuator for monitoring and management endpoints, and Spring AI for interacting with Large Language Models.

### Application Objective

The goal of this application is to implement the OpenAPI REST API for conversing with Large Language Models (LLMs). This enables standardized, REST-based interactions with various LLM providers through a unified API interface.

The project also aims to leverage the latest features of Java 25 and Spring Boot 4.

## Architecture

This application follows **Hexagonal Architecture** (Ports & Adapters) principles with **Domain-Driven Design** and **Clean Code** practices.

- **Domain (`fr.spirylics.kouign.domain`)**: Contains the core business logic, entities, and ports (interfaces for inbound and outbound communication). It is framework-independent.
- **Application (`fr.spirylics.kouign.application`)**: Exposes the domain services via REST controllers. It acts as the primary entry point for external requests.
- **Infrastructure (`fr.spirylics.kouign.infrastructure`)**: Implements the outbound ports defined in the domain, such as repositories or clients for external services (e.g., OpenAI).
- **Config (`fr.spirylics.kouign.config`)**: Handles Spring Bean configuration and dependency injection.

## Development Commands

### Prerequisites

- Java 25
- Maven

### Build

To build the project and package it, run:
```bash
mvn package
```

### Run

To run the application locally:
```bash
mvn spring-boot:run
```
The application will be available on port 9090.

The `README.md` file contains many other ways to build and run the application, including Docker and GraalVM native images.

### Testing

The project has different types of tests:

- **Unit Tests**: Run with `mvn test`.
- **Integration Tests**: Run with `mvn verify`. These tests have the `*ITest` suffix.
- **Performance Tests**: Run with Gatling using `mvn gatling:test`.

## Configuration

The main configuration file is `src/main/resources/application.yml`.
Key properties:
- `server.port`: 9090
- `spring.application.name`: kouign
- `spring.ai.openai.base-url`: Configured for a local Ollama instance.
- `kouign.models.*`: Definition of custom models.

## API

The application exposes a REST API. You can find the OpenAPI specification in `src/main/resources/openapi.documented.yml`.

Additionally, there are `.http` files in `src/test/resources/fr/spirylics/kouign/` that contain example requests. You can use these with a compatible client or `curl`.

## Development Guidelines

- **Code Style**: Follow the existing code style. The project uses Checkstyle to enforce it.
- **No unnecessary comments**: Write self-explanatory code.
- **Hexagonal Architecture**: Respect the boundaries between layers. The domain should not depend on infrastructure or application layers.
- **Dependencies**: Add new dependencies to `pom.xml` and respect the existing dependency management.
