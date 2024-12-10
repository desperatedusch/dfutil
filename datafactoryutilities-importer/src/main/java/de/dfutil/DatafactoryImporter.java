package de.dfutil;

import de.dfutil.core.files.InputSourceDetection;
import de.dfutil.core.files.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
        new SpringApplicationBuilder(DatafactoryImporter.class).web(WebApplicationType.NONE).run(args);
        log.debug("Importer app stopped...");
    }

    @Override
    public void run(String... args) throws Exception {
        inputSourceDetection.findFiles().forEach(parser::fromFile);
    }
}
