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
import java.util.Objects;
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
        log.info("Orphans handling started");
//        handleOrphanedSb();
//        handleOrphanedOb();
//        handleOrphanedOr();
        log.info("Splits handling started");
//        handleSplittedSb();
//        handleSplittedOb();
//        handleSplittedOr();
        log.info("Merges handling started");
//        handleMergedSb();
//        handleMergedOb();
//        handleMergedOr();
        log.info("Replacements handling started");
        handleReplacementsSb();
//        handleReplacementsOb();
//        handleReplacementsOr();
        log.info("Finished successionhandling within {} ms", stopwatch.stop().elapsed().toMillis());
    }

    private void handleOrphanedSb() {
        List<SbRow> processableOrphanedSbObjects =
                sbRowRepository.findProcessableOrphans();
        log.info("Processing orphaned Sb objects... {} found", processableOrphanedSbObjects.size());
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
        log.info("Processing orphaned Ob objects... {} found", processableOrphanedObObjects.size());
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
        log.info("Processing orphaned Or objects... {} found", processableOrphanedOrObjects.size());
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
                formerExistingOr.setOutdatedAt(LocalDateTime.now());
                orRowRepository.save(formerExistingOr);
                processableOr.setAlreadyAppliedAt(LocalDateTime.now());
                orRowRepository.save(processableOr);
            }
        });
    }

    private void handleSplittedSb() {
        List<SbRow> multipleSuccessors =
                sbRowRepository.findMultipleSuccessorCandidates();
        log.info("Processing multiple successor candidates of Sb objects... {} found", multipleSuccessors.size());
    }

    private void handleSplittedOb() {
        List<ObRow> multipleSuccessors =
                obRowRepository.findMultipleSuccessorCandidates();
        log.info("Processing multiple successor candidates of Ob objects... {} found", multipleSuccessors.size());
    }

    private void handleSplittedOr() {
        List<OrRow> multipleSuccessors =
                orRowRepository.findMultipleSuccessorCandidates();
        log.info("Processing multiple successor candidates of Or objects... {} found", multipleSuccessors.size());
    }

    private void handleMergedOb() {

    }

    private void handleMergedOr() {

    }

    private void handleMergedSb() {

    }

    private void handleReplacementsOb() {
        List<ObRow> processableSingleSuccessors =
                obRowRepository.findReplacementCandidates();
        log.info("Processing replacements of Ob objects... {} found", processableSingleSuccessors.size());
        processableSingleSuccessors.stream().filter(Objects::nonNull).forEach(processableOb ->
        {
            Optional<ObRow> predecessorObOptional =
                    obRowRepository.findById(
                            new ObRowId(
                                    processableOb.getObRowId().getOtlAlort(),
                                    processableOb.getObRowId().getOtlSchl(),
                                    processableOb.getObRowId().getOtlPlz(),
                                    "G"
                            )
                    );
            if (predecessorObOptional.isPresent()) {
                ObRow formerExistingOb = predecessorObOptional.get();
                formerExistingOb.setOutdatedAt(LocalDateTime.now());
                obRowRepository.save(formerExistingOb);
                processableOb.setAlreadyAppliedAt(LocalDateTime.now());
                processableOb.getObRowId().setOtlStatus("G");
                obRowRepository.save(processableOb);
            }
        });
    }

    private void handleReplacementsOr() {
        List<OrRow> processableSingleSuccessors =
                orRowRepository.findReplacementCandidates();
        log.info("Processing replacements of Or objects... {} found", processableSingleSuccessors.size());
        processableSingleSuccessors.stream().filter(Objects::nonNull).forEach(processableOr ->
        {
            Optional<OrRow> predecessorOrOptional =
                    orRowRepository.findById(
                            new OrRowId(
                                    processableOr.getOrRowId().getOrtAlort(),
                                    "G"
                            )
                    );
            if (predecessorOrOptional.isPresent()) {
                OrRow formerExistingOr = predecessorOrOptional.get();
                formerExistingOr.setOutdatedAt(LocalDateTime.now());
                orRowRepository.save(formerExistingOr);
                processableOr.setAlreadyAppliedAt(LocalDateTime.now());
                processableOr.getOrRowId().setOrtStatus("G");
                orRowRepository.save(processableOr);
            }
        });
    }

    private void handleReplacementsSb() {
        List<SbRow> processableSingleSuccessors =
                sbRowRepository.findReplacementCandidates();
        log.info("Processing replacements of Sb objects... {} found", processableSingleSuccessors.size());
        processableSingleSuccessors.stream().filter(Objects::nonNull).forEach(processableSb ->
        {
            Optional<SbRow> predecessorSbOptional =
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
            if (predecessorSbOptional.isPresent()) {
                SbRow formerExistingSb = predecessorSbOptional.get();
                formerExistingSb.setOutdatedAt(LocalDateTime.now());
                sbRowRepository.save(formerExistingSb);
                processableSb.setAlreadyAppliedAt(LocalDateTime.now());
                processableSb.getSbRowId().setStrStatus("G");
                sbRowRepository.save(processableSb);
            }
        });
    }

}
