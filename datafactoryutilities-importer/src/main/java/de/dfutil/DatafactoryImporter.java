package de.dfutil;

import de.dfutil.importing.ImporterConfigurationProperties;
import de.dfutil.importing.InputSourceDetection;
import de.dfutil.importing.Parsing;
import de.dfutil.importing.RelationshipUpdating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.nio.file.Path;
import java.util.Comparator;

@SpringBootApplication
@EnableConfigurationProperties(ImporterConfigurationProperties.class)
public class DatafactoryImporter implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatafactoryImporter.class);

    private final ImporterConfigurationProperties importerConfigurationProperties;

    private final InputSourceDetection inputSourceDetection;
    private final Parsing parsing;
    private final RelationshipUpdating relationshipUpdating;

    public DatafactoryImporter(ImporterConfigurationProperties importerConfigurationProperties, InputSourceDetection inputSourceDetection, Parsing parsing, RelationshipUpdating relationshipUpdating) {
        this.importerConfigurationProperties = importerConfigurationProperties;
        this.inputSourceDetection = inputSourceDetection;
        this.parsing = parsing;
        this.relationshipUpdating = relationshipUpdating;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(DatafactoryImporter.class).web(WebApplicationType.NONE).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (importerConfigurationProperties.isParsingActivated()) {
            log.info("Searching for input sources...");
            var files = inputSourceDetection.findFiles();
            files.sort(Comparator.comparing(Path::getFileName));
            files.forEach(parsing::fromFile);
            log.info("Parsing of all input sources done...");
        }
        if (importerConfigurationProperties
                .isSuccessionHandlingActivated()) {
            relationshipUpdating.process();
        }
        if (importerConfigurationProperties
                .isResetSuccessionHandlingApplicationState()) {
            relationshipUpdating.resetSuccessionsApplicationState();
        }
        log.info("Importing process finished...");
    }

}
