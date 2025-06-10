package de.dfutil.importing.updates;

import de.dfutil.dao.ObRowRepository;
import de.dfutil.dao.OrRowRepository;
import de.dfutil.dao.SbRowRepository;
import de.dfutil.entities.ObRow;
import de.dfutil.entities.OrRow;
import de.dfutil.entities.SbRow;
import de.dfutil.entities.ids.ObRowId;
import de.dfutil.entities.ids.OrRowId;
import de.dfutil.entities.ids.SbRowId;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static de.dfutil.entities.ArchivingState.INVALID;
import static de.dfutil.entities.ArchivingState.VALID;

@Service
public class OrphaneHandling {

    private static final Logger log = LoggerFactory.getLogger(OrphaneHandling.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public OrphaneHandling(OrRowRepository orRowRepository, ObRowRepository obRowRepository, SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleOrObjects() {
        List<OrRow> processableOrphanedOrObjects =
                orRowRepository.findProcessableOrphans();
        log.debug("Processing orphaned Or objects... {} found", processableOrphanedOrObjects.size());
        processableOrphanedOrObjects.stream().filter(Objects::nonNull).forEach(processableOr ->
        {
            Optional<OrRow> formerExistingOrOptional = orRowRepository.findById(
                    new OrRowId(
                            processableOr.getOrRowId().getOrtAlort(),
                            "G"
                    )
            );
            if (formerExistingOrOptional.isPresent()) {
                OrRow formerExistingOr = formerExistingOrOptional.get();
                orRowRepository.changeStatus(formerExistingOr.getUuid(), INVALID.symbol());
                orRowRepository.outdate(formerExistingOr.getUuid(), LocalDateTime.now());
                orRowRepository.changeStatus(processableOr.getUuid(), VALID.symbol());
                orRowRepository.apply(processableOr.getUuid(), LocalDateTime.now());
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleObObjects() {
        List<ObRow> processableOrphanedObObjects =
                obRowRepository.findProcessableOrphans();
        log.debug("Processing orphaned Ob objects... {} found", processableOrphanedObObjects.size());
        processableOrphanedObObjects.stream().filter(Objects::nonNull).forEach(processableOb ->
        {
            Optional<ObRow> formerExistingObOptional =
                    obRowRepository.findById(
                            new ObRowId(
                                    processableOb.getObRowId().getOtlAlort(),
                                    processableOb.getObRowId().getOtlSchl(),
                                    processableOb.getObRowId().getOtlPlz(),
                                    "G"
                            )
                    );
            if (formerExistingObOptional.isPresent()) {
                ObRow formerExistingOb = formerExistingObOptional.get();
                sbRowRepository.changeStatus(formerExistingOb.getUuid(), INVALID.symbol());
                obRowRepository.outdate(formerExistingOb.getUuid(), LocalDateTime.now());
                sbRowRepository.changeStatus(processableOb.getUuid(), VALID.symbol());
                obRowRepository.apply(processableOb.getUuid(), LocalDateTime.now());
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleSbObjects() {
        List<SbRow> processableOrphanedSbObjects =
                sbRowRepository.findProcessableOrphans();
        log.debug("Processing orphaned Sb objects... {} found", processableOrphanedSbObjects.size());
        processableOrphanedSbObjects.stream().filter(Objects::nonNull).forEach(processableSb ->
        {
            Optional<SbRow> formerExistingSbOptional =
                    sbRowRepository.findById(
                            new SbRowId(
                                    processableSb.getSbRowId().getStrAlOrt(),
                                    processableSb.getSbRowId().getStrNamenSchl(),
                                    processableSb.getSbRowId().getStrBundLfdnr(),
                                    processableSb.getSbRowId().getStrHnrVon(),
                                    processableSb.getSbRowId().getStrHnrBis(),
                                    "G",
                                    processableSb.getSbRowId().getStrHnr1000()
                            )
                    );
            if (formerExistingSbOptional.isPresent()) {
                SbRow formerExistingSb = formerExistingSbOptional.get();
                sbRowRepository.changeStatus(formerExistingSb.getUuid(), INVALID.symbol());
                sbRowRepository.outdate(formerExistingSb.getUuid(), LocalDateTime.now());
                sbRowRepository.changeStatus(processableSb.getUuid(), VALID.symbol());
                sbRowRepository.apply(processableSb.getUuid(), LocalDateTime.now());
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void process() {
        handleOrObjects();
        handleObObjects();
        handleSbObjects();
    }


}
