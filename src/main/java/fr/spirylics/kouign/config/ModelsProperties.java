package fr.spirylics.kouign.config;

import fr.spirylics.kouign.domain.model.Model;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.SequencedSet;

@ConfigurationProperties(prefix = "kouign")
public record ModelsProperties(SequencedSet<Model> models) {
}
