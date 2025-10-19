package fr.spirylics.kouign.domain.model;

import java.time.Instant;
import java.util.List;

/**
 * Domain entity representing an AI Language Model.
 * Based on OpenAPI LLM specification for model objects.
 */
public record Model(
    String id,
    String name,
    Instant created,
    String ownedBy
) {
    public Model {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Model id cannot be null or blank");
        }
    }
}