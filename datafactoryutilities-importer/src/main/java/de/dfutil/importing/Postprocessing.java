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

    public Postprocessing(ImporterConfigurationProperties importerConfigurationProperties, ImportResultRepository importResultRepository) {
        this.importerConfigurationProperties = importerConfigurationProperties;
        this.importResultRepository = importResultRepository;
    }

    public void parsedSuccessfully(Path inputSource, Long duration) {
        importResultRepository.save(
                new ImportResultEntity(
                        inputSource.getFileName().toString(),
                        LocalDateTime.now(),
                        true,
                        duration)
        );
    }

    public void parsingFailed(Path inputSource, Long duration) {
        importResultRepository.save(
                new ImportResultEntity(
                        inputSource.getFileName().toString(),
                        LocalDateTime.now(),
                        false,
                        null)
        );
    }

    public void deleteProcessedInputSources(Path inputSource) {
        try {
            if (importerConfigurationProperties.isDeleteSourcesAfterSuccessfulProcessing()) {
                Files.deleteIfExists(inputSource);
            }
        } catch (IOException e) {
            log.error("Failed to delete {}\n{}", inputSource, e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
