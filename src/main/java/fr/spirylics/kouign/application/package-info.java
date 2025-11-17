/**
 * Application layer containing REST controllers.
 * <p>
 * This layer acts as the entry point for external requests and exposes domain services through REST endpoints. It
 * directly exposes domain entities without DTOs, following the pragmatic hexagonal architecture approach.
 * <p>
 * Controllers inject and delegate to domain services (inbound ports).
 * <p>
 * All types are non-null by default unless explicitly annotated with {@code @Nullable}.
 */
@NullMarked
package fr.spirylics.kouign.application;

import org.jspecify.annotations.NullMarked;
