package de.dfutil.importing;

import de.dfutil.dao.jpa.ImportResultRepository;
import de.dfutil.entities.jpa.ImportResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
public class Postprocessing {

    @Autowired
    private ImportResultRepository importResultRepository;

    public Postprocessing() {
    }

    public void parsedSuccessfully(Path inputSource, Long duration) {
        importResultRepository.save(new ImportResult(inputSource.getFileName().toString(), LocalDateTime.now(), true, duration));
    }

    public void parsingFailed(Path inputSource, Long duration) {
        importResultRepository.save(new ImportResult(inputSource.getFileName().toString(), LocalDateTime.now(), false, null));
    }

}
