package de.dfutil.importing;

import de.dfutil.dao.jpa.ObRowRepository;
import de.dfutil.dao.jpa.OrRowRepository;
import de.dfutil.dao.jpa.SbRowRepository;
import de.dfutil.entities.jpa.ObRow;
import de.dfutil.entities.jpa.OrRow;
import de.dfutil.entities.jpa.SbRow;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuccessionHandling {

    private final ObRowRepository obRowRepository;
    private final OrRowRepository orRowRepository;
    private final SbRowRepository sbRowRepository;

    public SuccessionHandling(ObRowRepository obRowRepository, OrRowRepository orRowRepository, SbRowRepository sbRowRepository) {
        this.obRowRepository = obRowRepository;
        this.orRowRepository = orRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    public void handleOrOrphans() {
        List<OrRow> processableOrphanedOr = orRowRepository.findProcessableOrphans();
        List<ObRow> processableOrphanedOb = obRowRepository.findProcessableOrphans();
        List<SbRow> processableOrphanedSb = sbRowRepository.findProcessableOrphans();

    }

    public void hasSuccessor() {

    }

    public void hasSuccessors() {

    }

}
