package fr.spirylics.kouign.application.usecase;

import fr.spirylics.kouign.application.port.output.ModelRepository;
import fr.spirylics.kouign.domain.model.ModelList;
import org.springframework.stereotype.Service;

/**
 * Use case for listing available AI models.
 * Implements the business logic for retrieving models.
 */
@Service
public class ListModelsUseCase {

    private final ModelRepository modelRepository;

    public ListModelsUseCase(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    /**
     * Executes the use case to retrieve all available models.
     *
     * @return a ModelList containing all available models
     */
    public ModelList execute() {
        return modelRepository.findAll();
    }
}