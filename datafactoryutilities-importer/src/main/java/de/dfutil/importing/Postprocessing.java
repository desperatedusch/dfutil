package de.dfutil.importing;

import de.dfutil.dao.ImportResultRepository;
import de.dfutil.entities.ImportResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
@EnableConfigurationProperties(ImporterConfigurationProperties.class)
public class Postprocessing {

    private static final Logger log = LoggerFactory.getLogger(Postprocessing.class);

    private final ImporterConfigurationProperties importerConfigurationProperties;
    private final ImportResultRepository importResultRepository;

    public Postprocessing(final ImporterConfigurationProperties importerConfigurationProperties, final ImportResultRepository importResultRepository) {
        this.importerConfigurationProperties = importerConfigurationProperties;
        this.importResultRepository = importResultRepository;
    }

    public void parsedSuccessfully(final Path inputSource, final long duration) {
        this.importResultRepository.save(
                new ImportResultEntity(
                        inputSource.getFileName().toString(),
                        LocalDateTime.now(),
                        true,
                        duration)
        );
    }

    public void parsingFailed(final Path inputSource, final Long duration) {
        this.importResultRepository.save(
                new ImportResultEntity(
                        inputSource.getFileName().toString(),
                        LocalDateTime.now(),
                        false,
                        null)
        );
    }

    public void deleteProcessedInputSources(final Path inputSource) {
        try {
            if (this.importerConfigurationProperties.isDeleteSourcesAfterSuccessfulProcessing()) {
                Files.deleteIfExists(inputSource);
            }
        } catch (final IOException e) {
            Postprocessing.log.error("Failed to delete {}\n{}", inputSource, e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
