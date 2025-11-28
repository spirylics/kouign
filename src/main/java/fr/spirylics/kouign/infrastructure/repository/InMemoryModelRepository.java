package fr.spirylics.kouign.infrastructure.repository;

import fr.spirylics.kouign.config.ModelsProperties;
import fr.spirylics.kouign.domain.model.Model;
import fr.spirylics.kouign.domain.model.out.ModelRepository;
import java.util.SequencedSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InMemoryModelRepository implements ModelRepository {

    final ModelsProperties modelsProperties;

    @Override
    public SequencedSet<Model> findAll() {
        return modelsProperties.models();
    }
}
