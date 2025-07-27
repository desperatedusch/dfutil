package de.dfutil.importing.updates;

import de.dfutil.dao.ObRowRepository;
import de.dfutil.dao.OrRowRepository;
import de.dfutil.dao.SbRowRepository;
import de.dfutil.entities.ObRowEntity;
import de.dfutil.entities.OrRowEntiy;
import de.dfutil.entities.SbRowEntity;
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
public class Orphanes implements Successions {

    private static final Logger log = LoggerFactory.getLogger(Orphanes.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public Orphanes(OrRowRepository orRowRepository, ObRowRepository obRowRepository, SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleOrObjects() {
        List<OrRowEntiy> processableOrphanedOrObjects =
                orRowRepository.findProcessableOrphans();
        log.debug("Processing orphaned Or objects... {} found", processableOrphanedOrObjects.size());
        processableOrphanedOrObjects.stream().filter(Objects::nonNull).forEach(processableOr ->
        {
            Optional<OrRowEntiy> formerExistingOrOptional = orRowRepository.findById(
                    new OrRowId(
                            processableOr.getOrRowId().getOrtAlort(),
                            "G"
                    )
            );
            if (formerExistingOrOptional.isPresent()) {
                OrRowEntiy formerExistingOr = formerExistingOrOptional.get();
                orRowRepository.changeStatus(formerExistingOr.getUuid(), INVALID.symbol());
                orRowRepository.outdate(formerExistingOr.getUuid(), LocalDateTime.now());
                orRowRepository.changeStatus(processableOr.getUuid(), VALID.symbol());
                orRowRepository.apply(processableOr.getUuid(), LocalDateTime.now());
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleObObjects() {
        List<ObRowEntity> processableOrphanedObObjects =
                obRowRepository.findProcessableOrphans();
        log.debug("Processing orphaned Ob objects... {} found", processableOrphanedObObjects.size());
        processableOrphanedObObjects.stream().filter(Objects::nonNull).forEach(processableOb ->
        {
            Optional<ObRowEntity> formerExistingObOptional =
                    obRowRepository.findById(
                            new ObRowId(
                                    processableOb.getObRowId().getOtlAlort(),
                                    processableOb.getObRowId().getOtlSchl(),
                                    processableOb.getObRowId().getOtlPlz(),
                                    "G"
                            )
                    );
            if (formerExistingObOptional.isPresent()) {
                ObRowEntity formerExistingOb = formerExistingObOptional.get();
//                sbRowRepository.changeStatus(formerExistingOb.getUuid(), INVALID.symbol());
                obRowRepository.outdate(formerExistingOb.getUuid(), LocalDateTime.now());
//                sbRowRepository.changeStatus(processableOb.getUuid(), VALID.symbol());
                obRowRepository.apply(processableOb.getUuid(), LocalDateTime.now());
                obRowRepository.outdate(processableOb.getUuid(), LocalDateTime.now());
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleSbObjects() {
        List<SbRowEntity> processableOrphanedSbObjects =
                sbRowRepository.findProcessableOrphans();
        log.debug("Processing orphaned Sb objects... {} found", processableOrphanedSbObjects.size());
        processableOrphanedSbObjects.stream().filter(Objects::nonNull).forEach(processableSb ->
        {
            Optional<SbRowEntity> formerExistingSbOptional =
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
                SbRowEntity formerExistingSb = formerExistingSbOptional.get();
                sbRowRepository.changeStatus(formerExistingSb.getUuid(), INVALID.symbol());
                sbRowRepository.outdate(formerExistingSb.getUuid(), LocalDateTime.now());
                sbRowRepository.changeStatus(processableSb.getUuid(), VALID.symbol());
                sbRowRepository.apply(processableSb.getUuid(), LocalDateTime.now());
            }
        });
    }

}
