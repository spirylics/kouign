package fr.spirylics.kouign.domain.model.out;

import fr.spirylics.kouign.domain.model.Model;
import java.util.List;

public interface ModelRepository {
    List<Model> findAll();
}
