package de.dfutil;

import de.dfutil.importing.InputSourceDetection;
import de.dfutil.importing.Parser;
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

    private final Parser parsing;

    public DatafactoryImporter(InputSourceDetection inputSourceDetection, Parser parsing) {
        this.inputSourceDetection = inputSourceDetection;
        this.parsing = parsing;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(DatafactoryImporter.class).web(WebApplicationType.NONE).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Path> files = inputSourceDetection.findFiles();
        files.sort(Comparator.comparing(Path::getFileName));
        files.forEach(parsing::fromFile);
    }

}
