package fr.spirylics.kouign.domain.model.out;

import fr.spirylics.kouign.domain.model.Model;

import java.util.stream.Stream;

public interface ModelRepository {
    Stream<Model> findAll();
}
