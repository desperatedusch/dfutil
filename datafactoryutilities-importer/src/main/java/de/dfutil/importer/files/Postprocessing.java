package de.dfutil.importer.files;

import de.dfutil.dao.jpa.ImportResultRepository;
import de.dfutil.entities.jpa.ImportResult;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.time.LocalDate;

@Service
public class Postprocessing {

    private final ImportResultRepository importResultRepository;

    public Postprocessing(ImportResultRepository importResultRepository) {
        this.importResultRepository = importResultRepository;
    }

    public void parsedSuccessfully(Path inputSource, Long duration) {
        importResultRepository.save(new ImportResult(inputSource.getFileName().toString(), LocalDate.now(), true, duration));
    }

    public void parsingFailed(Path inputSource, Long duration) {
        importResultRepository.save(new ImportResult(inputSource.getFileName().toString(), LocalDate.now(), false, duration));
    }

}
