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

## Project Structure

- **Package**: `fr.spirylics.kouign`
- **Source code**: Standard Maven structure (`src/main/java`, `src/test/java`)
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