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

@Service
public class Orphanes {

    private static final Logger log = LoggerFactory.getLogger(Orphanes.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public Orphanes(OrRowRepository orRowRepository, ObRowRepository obRowRepository, SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleOr() {
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
                orRowRepository.outdate(formerExistingOr.getOrRowId(), LocalDateTime.now());
                processableOr.getOrRowId().setOrtStatus("G");
                orRowRepository.apply(processableOr.getOrRowId(), LocalDateTime.now());
                orRowRepository.saveAndFlush(processableOr);
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleOb() {
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
                obRowRepository.outdate(formerExistingOb.getObRowId(), LocalDateTime.now());
                processableOb.getObRowId().setOtlStatus("G");
                obRowRepository.apply(processableOb.getObRowId(), LocalDateTime.now());
                obRowRepository.saveAndFlush(processableOb);
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleSb() {
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
                sbRowRepository.outdate(formerExistingSb.getSbRowId(), LocalDateTime.now());
                processableSb.getSbRowId().setStrStatus("G");
                sbRowRepository.apply(processableSb.getSbRowId(), LocalDateTime.now());
                sbRowRepository.saveAndFlush(processableSb);
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void process() {
        handleOr();
        handleOb();
        handleSb();
    }


}
