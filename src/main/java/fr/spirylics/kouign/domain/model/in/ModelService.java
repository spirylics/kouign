package fr.spirylics.kouign.domain.model.in;

import fr.spirylics.kouign.domain.model.Model;

import java.util.List;
import java.util.SequencedSet;

public interface ModelService {
    SequencedSet<Model> find();
}
