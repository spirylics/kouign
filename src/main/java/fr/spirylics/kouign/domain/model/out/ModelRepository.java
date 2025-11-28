package fr.spirylics.kouign.domain.model.out;

import fr.spirylics.kouign.domain.model.Model;

import java.util.List;
import java.util.SequencedSet;

public interface ModelRepository {
    SequencedSet<Model> findAll();
}
