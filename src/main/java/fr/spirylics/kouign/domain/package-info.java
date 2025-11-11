/**
 * Domain layer containing pure business logic.
 * <p>
 * This layer follows Hexagonal Architecture principles and is independent
 * of frameworks and external dependencies. It defines:
 * <ul>
 *   <li>Domain entities and value objects (as Java records)</li>
 *   <li>Inbound ports (service interfaces in {@code .in} packages)</li>
 *   <li>Outbound ports (repository interfaces in {@code .out} packages)</li>
 *   <li>Domain services implementing business rules</li>
 * </ul>
 * <p>
 * All types are non-null by default unless explicitly annotated with {@code @Nullable}.
 */
@NullMarked
package fr.spirylics.kouign.domain;

import org.jspecify.annotations.NullMarked;
