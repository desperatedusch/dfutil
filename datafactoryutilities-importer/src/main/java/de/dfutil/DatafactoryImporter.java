package de.dfutil;

import de.dfutil.core.files.InputSourceDetection;
import de.dfutil.core.files.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatafactoryImporter implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatafactoryImporter.class);

    private final InputSourceDetection inputSourceDetection;

    private final Parser parser;

    public DatafactoryImporter(InputSourceDetection inputSourceDetection, Parser parser) {
        this.inputSourceDetection = inputSourceDetection;
        this.parser = parser;
    }

    public static void main(String[] args) {
        log.debug("Importer app started...");
        SpringApplication.run(DatafactoryImporter.class, args);
        log.debug("Importer app stopped...");
    }

    @Override
    public void run(String... args) throws Exception {
        inputSourceDetection.findFiles().forEach(parser::fromFile);
    }
}
