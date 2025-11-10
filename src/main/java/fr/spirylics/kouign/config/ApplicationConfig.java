package fr.spirylics.kouign.config;

import fr.spirylics.kouign.domain.model.ModelServiceImpl;
import fr.spirylics.kouign.domain.model.in.ModelService;
import fr.spirylics.kouign.domain.model.out.ModelRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    ModelService modelService(final ModelRepository modelRepository) {
        return new ModelServiceImpl(modelRepository);
    }
}
