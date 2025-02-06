package de.dfutil.importing;

import de.dfutil.dao.jpa.ImportResultRepository;
import de.dfutil.entities.jpa.ImportResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
public class Postprocessing {

    private static final Logger log = LoggerFactory.getLogger(Postprocessing.class);

    @Autowired
    private ImportResultRepository importResultRepository;

    public Postprocessing() {
    }

    public void parsedSuccessfully(Path inputSource, Long duration) {
        importResultRepository.save(
                new ImportResult(
                        inputSource.getFileName().toString(),
                        LocalDateTime.now(),
                        true,
                        duration)
        );
    }

    public void parsingFailed(Path inputSource, Long duration) {
        importResultRepository.save(
                new ImportResult(
                        inputSource.getFileName().toString(),
                        LocalDateTime.now(),
                        false,
                        null)
        );
    }

    public void deleteProcessedInputSources(Path inputSource) {
        try {
            Files.deleteIfExists(inputSource);
        } catch (IOException e) {
            log.error("Failed to delete input source: {}", inputSource, e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
