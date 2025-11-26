package de.dfutil.importing.updates;

import de.dfutil.dao.ObRowRepository;
import de.dfutil.dao.OrRowRepository;
import de.dfutil.dao.SbRowRepository;
import de.dfutil.entities.ObRowEntity;
import de.dfutil.entities.OrRowEntity;
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
        List<OrRowEntity> processableOrphanedOrObjects =
                orRowRepository.findProcessableOrphans();
        log.info("Processing orphaned Or objects... {} found", processableOrphanedOrObjects.size());
        processableOrphanedOrObjects.stream().filter(Objects::nonNull).forEach(processableOr ->
        {
            OrRowEntity formerExistingOr =
                    orRowRepository.getByOrRowId(
                            new OrRowId(
                                    processableOr.getOrRowId().getOrtAlort(),
                                    "G"
                            )
                    );
            if (Objects.nonNull(formerExistingOr)) {
                orRowRepository.outdate(formerExistingOr.getUuid(), LocalDateTime.now());
                orRowRepository.apply(processableOr.getUuid(), LocalDateTime.now());
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleObObjects() {
        List<ObRowEntity> processableOrphanedObObjects =
                obRowRepository.findProcessableOrphans();
        log.info("Processing orphaned Ob objects... {} found", processableOrphanedObObjects.size());
        processableOrphanedObObjects.stream().filter(Objects::nonNull).forEach(processableOb ->
        {
            ObRowEntity formerExistingOb =
                    obRowRepository.getByObRowId(
                            new ObRowId(
                                    processableOb.getObRowId().getOtlAlort(),
                                    processableOb.getObRowId().getOtlSchl(),
                                    processableOb.getObRowId().getOtlPlz(),
                                    "G"
                            )
                    );
            if (Objects.nonNull(formerExistingOb)) {
                obRowRepository.outdate(formerExistingOb.getUuid(), LocalDateTime.now());
                obRowRepository.apply(processableOb.getUuid(), LocalDateTime.now());
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleSbObjects() {
        var processableOrphanedSbObjects =
                sbRowRepository.findProcessableOrphans();
        log.info("Processing orphaned Sb objects... {} found", processableOrphanedSbObjects.size());
        processableOrphanedSbObjects.stream().filter(Objects::nonNull).forEach(processableSb ->
        {
            SbRowEntity formerExistingSb =
                    sbRowRepository.getBySbRowId(
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
            if (Objects.nonNull(formerExistingSb)) {
                sbRowRepository.outdate(formerExistingSb.getUuid(), LocalDateTime.now());
                sbRowRepository.apply(processableSb.getUuid(), LocalDateTime.now());
            }
        });
    }

}
