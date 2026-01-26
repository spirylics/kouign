package fr.spirylics.kouign.infrastructure.repository;

import fr.spirylics.kouign.config.KouignProperties;
import fr.spirylics.kouign.domain.model.Model;
import fr.spirylics.kouign.domain.model.out.ModelRepository;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InMemoryModelRepository implements ModelRepository {

    final KouignProperties kouignProperties;

    @Override
    public Stream<Model> findAll()
    {
        return kouignProperties.models().sequencedValues().stream();
    }
}
