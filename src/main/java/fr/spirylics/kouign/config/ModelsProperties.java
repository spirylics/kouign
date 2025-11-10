package fr.spirylics.kouign.config;

import fr.spirylics.kouign.domain.model.Model;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "kouign")
public record ModelsProperties(List<Model> models) {
}
