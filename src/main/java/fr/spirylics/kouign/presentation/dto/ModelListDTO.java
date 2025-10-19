package fr.spirylics.kouign.presentation.dto;

import fr.spirylics.kouign.domain.model.ModelList;

import java.util.List;

/**
 * Data Transfer Object for ModelList entity.
 * Represents the JSON response format for the models list endpoint.
 */
public record ModelListDTO(
    String object,
    List<ModelDTO> data
) {
    public static ModelListDTO fromDomain(ModelList modelList) {
        List<ModelDTO> dtoList = modelList.data().stream()
            .map(ModelDTO::fromDomain)
            .toList();

        return new ModelListDTO(
            modelList.object(),
            dtoList
        );
    }
}