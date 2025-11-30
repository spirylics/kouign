package fr.spirylics.kouign.domain.model.in;

import fr.spirylics.kouign.domain.model.Model;

import java.util.stream.Stream;

public interface ModelService {
    Stream<Model> find();

    Stream<Model> findById(String id);
}
