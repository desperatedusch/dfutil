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
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(ImporterConfigurationProperties.class)
public class DatafactoryImporter implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatafactoryImporter.class);

    private final ImporterConfigurationProperties importerConfigurationProperties;

    private final InputSourceDetection inputSourceDetection;
    private final Parsing parsing;
    private final RelationshipUpdating relationshipUpdating;

    public DatafactoryImporter(final ImporterConfigurationProperties importerConfigurationProperties, final InputSourceDetection inputSourceDetection, final Parsing parsing, final RelationshipUpdating relationshipUpdating) {
        this.importerConfigurationProperties = importerConfigurationProperties;
        this.inputSourceDetection = inputSourceDetection;
        this.parsing = parsing;
        this.relationshipUpdating = relationshipUpdating;
    }

    public static void main(final String[] args) {
        new SpringApplicationBuilder(DatafactoryImporter.class).web(WebApplicationType.NONE).run(args);
    }

    @Override
    public void run(final String... args) throws Exception {
        if (this.importerConfigurationProperties.isParsingActivated()) {
            DatafactoryImporter.log.info("Searching for input sources...");
            final List<Path> files = this.inputSourceDetection.findFiles();
            files.sort(Comparator.comparing(Path::getFileName));
            files.forEach(this.parsing::fromFile);
            DatafactoryImporter.log.info("Parsing of all input sources done...");
        }
        if (this.importerConfigurationProperties
                .isSuccessionHandlingActivated()) {
            this.relationshipUpdating.process();
        }
        if (this.importerConfigurationProperties
                .isResetSuccessionHandlingApplicationStateActivated()) {
            this.relationshipUpdating.resetSuccessionsApplicationState();
        }
        DatafactoryImporter.log.info("Importing process finished...");
    }

}
