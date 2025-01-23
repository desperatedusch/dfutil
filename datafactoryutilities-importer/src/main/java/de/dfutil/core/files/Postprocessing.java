package de.dfutil.core.files;

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

    public void proccessingSuccessfull(Path inputSource) {
        importResultRepository.save(new ImportResult(inputSource.getFileName().toString(), LocalDate.now(), true));
    }

    public void proccessingFailed(Path inputSource) {
        importResultRepository.save(new ImportResult(inputSource.getFileName().toString(), LocalDate.now(), false));
    }

}
