/**
 * Model aggregate containing domain entities, ports, and service implementations.
 * <p>
 * This package follows Domain-Driven Design principles:
 * <ul>
 *   <li>Domain entities as immutable Java records</li>
 *   <li>Inbound ports in {@code .in} subpackage</li>
 *   <li>Outbound ports in {@code .out} subpackage</li>
 *   <li>Service implementations with internal use case records</li>
 * </ul>
 * <p>
 * All types are non-null by default unless explicitly annotated with {@code @Nullable}.
 */
@NullMarked
package fr.spirylics.kouign.domain.model;

import org.jspecify.annotations.NullMarked;
