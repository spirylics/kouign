/**
 * Configuration layer for Spring Bean configuration.
 * <p>
 * This layer handles dependency injection and wiring of domain services, repositories, and other application
 * components. It bridges the domain and infrastructure layers through Spring's IoC container.
 * <p>
 * Configuration classes instantiate domain services with their required dependencies (repositories, other services).
 * <p>
 * All types are non-null by default unless explicitly annotated with {@code @Nullable}.
 */
@NullMarked
package fr.spirylics.kouign.config;

import org.jspecify.annotations.NullMarked;
