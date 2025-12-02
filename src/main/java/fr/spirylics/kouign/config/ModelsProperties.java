package fr.spirylics.kouign.config;

import fr.spirylics.kouign.domain.model.Model;
import java.util.SequencedSet;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kouign")
public record ModelsProperties(SequencedSet<Model> models) {
}
