package de.dfutil;

import de.dfutil.importing.InputSourceDetection;
import de.dfutil.importing.Parsing;
import de.dfutil.importing.RelationsUpdating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
    private final Parsing parsing;
    private final RelationsUpdating relationsUpdating;

    @Value("${app.importer.inputsource.parsing-activated}")
    private boolean parsingActivated;
    @Value("${app.importer.inputsource.successionhandling-activated}")
    private boolean successionHandlingActivated;

    public DatafactoryImporter(InputSourceDetection inputSourceDetection, Parsing parsing, RelationsUpdating relationsUpdating) {
        this.inputSourceDetection = inputSourceDetection;
        this.parsing = parsing;
        this.relationsUpdating = relationsUpdating;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(DatafactoryImporter.class).web(WebApplicationType.NONE).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Importing started");
        if (parsingActivated) {
            log.info("Parsing started");
            List<Path> files = inputSourceDetection.findFiles();
            files.sort(Comparator.comparing(Path::getFileName));
            files.forEach(parsing::fromFile);
            log.info("Parsing finished");
        }
        if (successionHandlingActivated) {
            log.info("Succession handling started");
            relationsUpdating.process();
            log.info("Succession handling finished");
        }
        log.info("Importing finished");
    }

}
