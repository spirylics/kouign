package fr.spirylics.kouign.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.spirylics.kouign.domain.model.Model;

/**
 * Data Transfer Object for Model entity.
 * Represents the JSON response format for a single model.
 */
public record ModelDTO(
    String id,
    String object,
    Long created,
    @JsonProperty("owned_by") String ownedBy
) {
    public static ModelDTO fromDomain(Model model) {
        return new ModelDTO(
            model.id(),
            "model",
            model.created() != null ? model.created().getEpochSecond() : null,
            model.ownedBy()
        );
    }
}