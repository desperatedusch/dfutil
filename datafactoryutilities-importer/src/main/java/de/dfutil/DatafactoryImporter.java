package de.dfutil;

import de.dfutil.core.files.InputSourceDetection;
import de.dfutil.core.files.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;
import java.util.List;

@SpringBootApplication
public class DatafactoryImporter implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DatafactoryImporter.class);

    private final InputSourceDetection inputSourceDetection;

    private final Parser parser;

    public DatafactoryImporter(InputSourceDetection inputSourceDetection, Parser parser) {
        this.inputSourceDetection = inputSourceDetection;
        this.parser = parser;
    }

    public static void main(String[] args) {
        log.info("Importer app started...");
        SpringApplication.run(DatafactoryImporter.class, args);
        log.info("Importer app stopped...");
    }

    @Override
    public void run(ApplicationArguments args) {
        List<Path> paths = inputSourceDetection.inputSourceFolders();
        for (Path path : paths) {
            log.info("Scanning input folder {} for files to import", path);
            List<Path> inputFiles = inputSourceDetection.findInputFiles(path);
            for (Path inputFile : inputFiles) {
                log.info("Importing input file {}", inputFile);
                parser.parseInputFile(path);
            }
        }
    }

}
