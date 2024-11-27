package de.dfutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatafactoryImporter implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DatafactoryImporter.class);

    public static void main(String[] args) {
        log.info("Importer app started...");
        SpringApplication.run(DatafactoryImporter.class, args);
        log.info("Importer app stopped...");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("DatafactoryImporter.run() ...");
    }
}
