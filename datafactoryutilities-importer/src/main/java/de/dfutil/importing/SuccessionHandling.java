package de.dfutil.importing;

import com.google.common.base.Stopwatch;
import de.dfutil.dao.ObRowRepository;
import de.dfutil.dao.OrRowRepository;
import de.dfutil.dao.SbRowRepository;
import de.dfutil.entities.ObRow;
import de.dfutil.entities.OrRow;
import de.dfutil.entities.SbRow;
import de.dfutil.entities.ids.ObRowId;
import de.dfutil.entities.ids.OrRowId;
import de.dfutil.entities.ids.SbRowId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SuccessionHandling {

    private static final Logger log = LoggerFactory.getLogger(SuccessionHandling.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;

    public SuccessionHandling(ObRowRepository obRowRepository, OrRowRepository orRowRepository, SbRowRepository sbRowRepository) {
        this.obRowRepository = obRowRepository;
        this.orRowRepository = orRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    public void process() {
        var stopwatch = Stopwatch.createStarted();
        log.info("Succession handling started at {}", LocalDateTime.now());
        handleOrphanedOr();
        handleOrphanedOb();
        handleOrphanedSb();
        log.info("Orphans handling completed");
        handleSplittedOr();
        handleSplittedOb();
        handleSplittedSb();
        log.info("Splits handling completed");
        handleMergedOr();
        handleMergedOb();
        handleMergedSb();
        log.info("Merges handling completed");
        handleReplacementsOr();
        handleReplacementsOb();
        handleReplacementsSb();
        log.info("Replacements handling completed");
        stopwatch.stop();
        log.info("Finished successions at {} within {} ms", LocalDateTime.now(), stopwatch.elapsed().toMillis());
    }

    private void handleOrphanedSb() {
        List<SbRow> processableOrphanedSbObjects =
                sbRowRepository.findProcessableOrphans();
        log.info("Processing orphaned Sb objects {} found", processableOrphanedSbObjects.size());
        processableOrphanedSbObjects.forEach(processableSb ->
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
                formerExistingSb.setOutdatedAt(LocalDateTime.now());
                sbRowRepository.save(formerExistingSb);
                processableSb.setAlreadyAppliedAt(LocalDateTime.now());
                sbRowRepository.save(processableSb);
            }
        });
    }

    private void handleOrphanedOb() {
        List<ObRow> processableOrphanedObObjects =
                obRowRepository.findProcessableOrphans();
        log.info("Processing orphaned Ob objects {} found", processableOrphanedObObjects.size());
        processableOrphanedObObjects.forEach(processableOb ->
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
                formerExistingOb.setOutdatedAt(LocalDateTime.now());
                obRowRepository.save(formerExistingOb);
                processableOb.setAlreadyAppliedAt(LocalDateTime.now());
                obRowRepository.save(processableOb);
            }
        });
    }

    private void handleOrphanedOr() {
        List<OrRow> processableOrphanedOrObjects =
                orRowRepository.findProcessableOrphans();
        log.info("Processing orphaned Or objects {} found", processableOrphanedOrObjects.size());
        processableOrphanedOrObjects.forEach(processableOr ->
        {
            Optional<OrRow> formerExistingOrOptional = orRowRepository.findById(
                    new OrRowId(
                            processableOr.getOrRowId().getOrtAlort(),
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
    }

    private void handleSplittedOr() {
        List<OrRow> multipleSuccessors = orRowRepository.findMultipleSuccessors();
    }

    private void handleSplittedOb() {

    }

    private void handleSplittedSb() {

    }

    private void handleMergedOb() {

    }

    private void handleMergedOr() {

    }

    private void handleMergedSb() {

    }

    private void handleReplacementsOb() {

    }

    private void handleReplacementsOr() {

    }

    private void handleReplacementsSb() {

    }

}
