/**
 * Outbound ports defining repository interfaces for the Model domain.
 * <p>
 * These ports abstract data access and external dependencies,
 * allowing the domain to remain independent of infrastructure concerns.
 * They are implemented by adapters in the infrastructure layer.
 * <p>
 * All types are non-null by default unless explicitly annotated with {@code @Nullable}.
 */
@NullMarked
package fr.spirylics.kouign.domain.model.out;

import org.jspecify.annotations.NullMarked;
