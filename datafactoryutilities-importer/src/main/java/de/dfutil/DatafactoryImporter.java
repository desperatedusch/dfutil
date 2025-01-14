package de.dfutil;

import de.dfutil.core.files.InputSourceDetection;
import de.dfutil.core.files.Parsing;
import de.dfutil.core.files.Postprocessing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class DatafactoryImporter implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatafactoryImporter.class);

    private final InputSourceDetection inputSourceDetection;
    private final Parsing parser;
    private final Postprocessing postprocessing;

    public DatafactoryImporter(InputSourceDetection inputSourceDetection, Parsing parser, Postprocessing postprocessing) {
        this.inputSourceDetection = inputSourceDetection;
        this.parser = parser;
        this.postprocessing = postprocessing;
    }

    public static void main(String[] args) {
        log.debug("Importer app started...");
        new SpringApplicationBuilder(DatafactoryImporter.class).web(WebApplicationType.NONE).run(args);
        log.debug("Importer app stopped...");
    }

    @Override
    public void run(String... args) throws Exception {
        List<Path> files = inputSourceDetection.findFiles();
        files.sort(Comparator.comparing(Path::getFileName));
        for (Path file : files) {
            parser.fromFile(file);
            postprocessing.proccessingSuccessfull(file);
        }
    }

}
