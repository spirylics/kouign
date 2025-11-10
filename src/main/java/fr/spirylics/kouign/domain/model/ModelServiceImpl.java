package fr.spirylics.kouign.domain.model;

import fr.spirylics.kouign.domain.model.in.ModelService;
import fr.spirylics.kouign.domain.model.out.ModelRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    @Getter(AccessLevel.PRIVATE)
    final ModelRepository repository;

    @Override
    public List<Model> find() {
        return new Find(getRepository()).execute();
    }
}

record Find(ModelRepository repository) {
    List<Model> execute() {
        return repository().findAll();
    }
}