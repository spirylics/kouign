package fr.spirylics.kouign.domain.model;

import java.util.List;

/**
 * Domain entity representing a list of available AI models.
 * Based on OpenAPI LLM specification for models list response.
 */
public record ModelList(
    String object,
    List<Model> data
) {
    public ModelList {
        if (data == null) {
            data = List.of();
        }
    }

    public static ModelList empty() {
        return new ModelList("list", List.of());
    }
}