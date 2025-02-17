package de.dfutil.importing;

import de.dfutil.dao.jpa.ObRowRepository;
import de.dfutil.dao.jpa.OrRowRepository;
import de.dfutil.dao.jpa.SbRowRepository;
import de.dfutil.entities.ArchivingState;
import de.dfutil.entities.jpa.OrRow;
import de.dfutil.entities.jpa.ids.OrRowId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SuccessionHandling {

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;

    public SuccessionHandling(ObRowRepository obRowRepository, OrRowRepository orRowRepository, SbRowRepository sbRowRepository) {
        this.obRowRepository = obRowRepository;
        this.orRowRepository = orRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional
    public void handleOrOrphans() {
        List<OrRow> processableOrphanedOrObjects =
                orRowRepository.findProcessableOrphans();
        processableOrphanedOrObjects.forEach(or ->
        {
            Optional<OrRow> byId = orRowRepository.findById(new OrRowId(or.getOrRowId().ortAlort(), ArchivingState.G.toString()));
            if (byId.isPresent()) {
                OrRow orRow = byId.get();
                orRow.setOutdatedAt(LocalDateTime.now());
            }
        });

//        List<ObRow> processableOrphanedObObjects =
//                obRowRepository.findProcessableOrphans();
//
//        List<SbRow> processableOrphanedSbObjects =
//                sbRowRepository.findProcessableOrphans();

    }

}
