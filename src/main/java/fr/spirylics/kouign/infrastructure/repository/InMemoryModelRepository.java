package fr.spirylics.kouign.infrastructure.repository;

import fr.spirylics.kouign.application.port.output.ModelRepository;
import fr.spirylics.kouign.domain.model.Model;
import fr.spirylics.kouign.domain.model.ModelList;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * In-memory implementation of ModelRepository.
 * Provides a static list of available AI models for demonstration purposes.
 */
@Repository
public class InMemoryModelRepository implements ModelRepository {

    private static final List<Model> AVAILABLE_MODELS = List.of(
        new Model(
            "gpt-4o",
            "GPT-4 Optimized",
            Instant.parse("2024-05-13T00:00:00Z"),
            "openai"
        ),
        new Model(
            "gpt-4o-mini",
            "GPT-4 Optimized Mini",
            Instant.parse("2024-07-18T00:00:00Z"),
            "openai"
        ),
        new Model(
            "claude-3-5-sonnet-20241022",
            "Claude 3.5 Sonnet",
            Instant.parse("2024-10-22T00:00:00Z"),
            "anthropic"
        ),
        new Model(
            "claude-3-5-haiku-20241022",
            "Claude 3.5 Haiku",
            Instant.parse("2024-11-01T00:00:00Z"),
            "anthropic"
        ),
        new Model(
            "gemini-2.0-flash-exp",
            "Gemini 2.0 Flash Experimental",
            Instant.parse("2024-12-11T00:00:00Z"),
            "google"
        )
    );

    @Override
    public ModelList findAll() {
        return new ModelList("list", AVAILABLE_MODELS);
    }
}