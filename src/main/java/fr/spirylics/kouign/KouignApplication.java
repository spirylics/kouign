package fr.spirylics.kouign;

import fr.spirylics.kouign.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(ApplicationConfig.class)
@SpringBootApplication
public class KouignApplication {

    static void main(String[] args)
    {
        SpringApplication.run(KouignApplication.class, args);
    }

}
