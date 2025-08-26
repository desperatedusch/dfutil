package de.dfutil.importing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImporterConfiguration {

    @Bean
    public ImporterEnvironmentPostProcessor importerEnvironmentPostProcessor() {
        return new ImporterEnvironmentPostProcessor();
    }

}
