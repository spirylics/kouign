package fr.spirylics.kouign.presentation.controller;

import fr.spirylics.kouign.application.usecase.ListModelsUseCase;
import fr.spirylics.kouign.domain.model.ModelList;
import fr.spirylics.kouign.presentation.dto.ModelListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing AI models.
 * Implements the OpenAPI-compatible models endpoint.
 */
@RestController
@RequestMapping("/v1/models")
public class ModelsController {

    private final ListModelsUseCase listModelsUseCase;

    public ModelsController(ListModelsUseCase listModelsUseCase) {
        this.listModelsUseCase = listModelsUseCase;
    }

    /**
     * Lists all available AI models.
     *
     * GET /v1/models
     *
     * @return ResponseEntity containing the list of available models
     */
    @GetMapping
    public ResponseEntity<ModelListDTO> listModels() {
        ModelList modelList = listModelsUseCase.execute();
        ModelListDTO response = ModelListDTO.fromDomain(modelList);
        return ResponseEntity.ok(response);
    }
}