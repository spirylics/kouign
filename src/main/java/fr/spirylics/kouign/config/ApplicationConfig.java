package fr.spirylics.kouign.config;

import fr.spirylics.kouign.domain.model.ModelServiceImpl;
import fr.spirylics.kouign.domain.model.in.ModelService;
import fr.spirylics.kouign.domain.model.out.ModelRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ModelsProperties.class)
public class ApplicationConfig {
    @Bean
    ModelService modelService(final ModelRepository modelRepository) {
        return new ModelServiceImpl(modelRepository);
    }
}
