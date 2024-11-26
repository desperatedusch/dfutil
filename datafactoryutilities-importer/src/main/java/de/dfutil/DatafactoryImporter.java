package de.dfutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class DatafactoryImporter implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DatafactoryImporter.class);

    public static void main(String[] args) {
        SpringApplication.run(DatafactoryImporter.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Importer app started...");
        log.info("Importer app stopped...");
    }
}
