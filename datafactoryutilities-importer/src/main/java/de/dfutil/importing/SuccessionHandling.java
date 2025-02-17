package de.dfutil.importing;

import de.dfutil.dao.jpa.ObRowRepository;
import de.dfutil.dao.jpa.OrRowRepository;
import de.dfutil.dao.jpa.SbRowRepository;
import de.dfutil.entities.jpa.ObRow;
import de.dfutil.entities.jpa.OrRow;
import de.dfutil.entities.jpa.SbRow;
import de.dfutil.entities.jpa.ids.ObRowId;
import de.dfutil.entities.jpa.ids.OrRowId;
import de.dfutil.entities.jpa.ids.SbRowId;
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
        processableOrphanedOrObjects.forEach(processableOr ->
        {
            Optional<OrRow> formerExistingOrOptional = orRowRepository.findById(
                    new OrRowId(
                            processableOr.getOrRowId().ortAlort(),
                            "G"
                    )
            );
            if (formerExistingOrOptional.isPresent()) {
                OrRow formerExistingOr = formerExistingOrOptional.get();
                formerExistingOr.setOutdatedAt(LocalDateTime.now());
                orRowRepository.save(formerExistingOr);
                processableOr.setAlreadyAppliedAt(LocalDateTime.now());
                orRowRepository.save(processableOr);
            }
        });

        List<ObRow> processableOrphanedObObjects =
                obRowRepository.findProcessableOrphans();
        processableOrphanedObObjects.forEach(processableOb ->
        {
            Optional<ObRow> formerExistingObOptional =
                    obRowRepository.findById(
                            new ObRowId(
                                    processableOb.getObRowId().otlAlort(),
                                    processableOb.getObRowId().otlSchl(),
                                    processableOb.getObRowId().otlPlz(),
                                    "G"
                            )
                    );
            if (formerExistingObOptional.isPresent()) {
                ObRow formerExistingOb = formerExistingObOptional.get();
                formerExistingOb.setOutdatedAt(LocalDateTime.now());
                obRowRepository.save(formerExistingOb);
                processableOb.setAlreadyAppliedAt(LocalDateTime.now());
                obRowRepository.save(processableOb);
            }
        });

        List<SbRow> processableOrphanedSbObjects =
                sbRowRepository.findProcessableOrphans();
        processableOrphanedSbObjects.forEach(processableSb ->
        {
            Optional<SbRow> formerExistingSbOptional =
                    sbRowRepository.findById(
                            new SbRowId(
                                    processableSb.getSbRowId().strAlOrt(),
                                    processableSb.getSbRowId().strNamenSchl(),
                                    processableSb.getSbRowId().strBundLfdnr(),
                                    processableSb.getSbRowId().strHnrVon(),
                                    processableSb.getSbRowId().strHnrBis(),
                                    "G",
                                    processableSb.getSbRowId().strHnr1000()
                            )
                    );
            if (formerExistingSbOptional.isPresent()) {
                SbRow formerExistingSb = formerExistingSbOptional.get();
                formerExistingSb.setOutdatedAt(LocalDateTime.now());
                sbRowRepository.save(formerExistingSb);
                processableSb.setAlreadyAppliedAt(LocalDateTime.now());
                sbRowRepository.save(processableSb);
            }
        });
    }

}
