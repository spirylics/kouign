package fr.spirylics.kouign.config;

import fr.spirylics.kouign.domain.model.Model;

import java.time.Duration;
import java.util.SequencedSet;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kouign")
public record KouignProperties(SequencedSet<Model> models, Duration randomPause) {
}
