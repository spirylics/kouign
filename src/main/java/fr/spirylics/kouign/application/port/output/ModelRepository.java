package fr.spirylics.kouign.application.port.output;

import fr.spirylics.kouign.domain.model.ModelList;

/**
 * Output port for retrieving AI models.
 * This interface defines the contract for model data access.
 */
public interface ModelRepository {

    /**
     * Retrieves all available AI models.
     *
     * @return a ModelList containing all available models
     */
    ModelList findAll();
}