package fr.spirylics.kouign.domain.model;

import fr.spirylics.kouign.domain.model.in.ModelService;
import fr.spirylics.kouign.domain.model.out.ModelRepository;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;

public record ModelServiceImpl(ModelRepository repository) implements ModelService {

    @Override
    public Stream<Model> find()
    {
        return Find.of(repository()).build().execute();
    }

    @Override
    public Stream<Model> findById(final String id)
    {
        return Find.of(repository()).id(id).build().execute();
    }
}

@Slf4j
@Builder
record Find(ModelRepository repository, @Nullable String id) {
    static FindBuilder of(final ModelRepository repository)
    {
        return Find.builder().repository(repository);
    }

    Stream<Model> execute()
    {
        return repository().findAll() //
                .filter(model -> id == null || model.id().equals(id));
    }
}
