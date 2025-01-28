package de.dfutil.importer.files;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;


@TestConfiguration
class TestConfig {

    @Bean
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }

}

