package de.dfutil;

import com.google.common.base.Stopwatch;
import de.dfutil.core.files.InputSourceDetection;
import de.dfutil.core.files.parsing.Parser;
import de.dfutil.core.files.parsing.eventbased.EmittingParser;
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

    public DatafactoryImporter(InputSourceDetection inputSourceDetection, EmittingParser parsing) {
        this.inputSourceDetection = inputSourceDetection;
        this.parsing = parsing;
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new SpringApplicationBuilder(DatafactoryImporter.class).web(WebApplicationType.NONE).run(args);
        stopwatch.stop();
        log.info("----------> Importer app finished after {} ms ....", stopwatch.elapsed().toMillis());
    }

    @Override
    public void run(String... args) throws Exception {
        List<Path> files = inputSourceDetection.findFiles();
        files.sort(Comparator.comparing(Path::getFileName));
        files.forEach(parsing::fromFile);
    }

}
