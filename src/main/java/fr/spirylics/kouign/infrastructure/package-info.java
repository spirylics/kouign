/**
 * Infrastructure layer containing adapters implementing outbound ports.
 * <p>
 * This layer provides concrete implementations of repository interfaces
 * and external service adapters. It contains framework-specific code
 * and handles communication with external systems.
 * <p>
 * Adapters depend on domain ports but the domain has no knowledge
 * of these implementations (dependency inversion).
 * <p>
 * All types are non-null by default unless explicitly annotated with {@code @Nullable}.
 */
@NullMarked
package fr.spirylics.kouign.infrastructure;

import org.jspecify.annotations.NullMarked;
