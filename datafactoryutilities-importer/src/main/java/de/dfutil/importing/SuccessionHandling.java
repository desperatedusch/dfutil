package de.dfutil.importing;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SuccessionHandling {

    private static final Logger log = LoggerFactory.getLogger(SuccessionHandling.class);

    private final Orphanes orphanes;
    private final Replacements replacements;
    private final Splittings splittings;
    private final Merges merges;

    public SuccessionHandling(Orphanes orphanes, Replacements replacements, Splittings splittings, Merges merges) {
        this.orphanes = orphanes;
        this.replacements = replacements;
        this.splittings = splittings;
        this.merges = merges;
    }

    public void process() {
        var stopwatch = Stopwatch.createStarted();
        orphanes.process();
        splittings.process();
        replacements.process();
        merges.process();
        log.info("Finished successionhandling within {} ms", stopwatch.stop().elapsed().toMillis());
    }

//
//    private void handleSplittedSb() {
//        List<SbRow> multipleSuccessors =
//                sbRowRepository.findMultipleSuccessorCandidates();
//        log.info("Processing multiple successor candidates of Sb objects... {} found", multipleSuccessors.size());
//    }
//
//    private void handleSplittedOb() {
//        List<ObRow> multipleSuccessors =
//                obRowRepository.findMultipleSuccessorCandidates();
//        log.info("Processing multiple successor candidates of Ob objects... {} found", multipleSuccessors.size());
//    }
//
//    private void handleSplittedOr() {
//        List<OrRow> multipleSuccessors =
//                orRowRepository.findMultipleSuccessorCandidates();
//        log.info("Processing multiple successor candidates of Or objects... {} found", multipleSuccessors.size());
//    }
//
//    private void handleMergedOb() {
//
//    }
//
//    private void handleMergedOr() {
//
//    }
//
//    private void handleMergedSb() {
//
//    }
//
//    private void handleReplacementsOb() {
//        List<ObRow> processableSingleSuccessors =
//                obRowRepository.findReplacementCandidates();
//        log.info("Processing replacements of Ob objects... {} found", processableSingleSuccessors.size());
//        processableSingleSuccessors.stream().filter(Objects::nonNull).forEach(processableOb ->
//        {
//            Optional<ObRow> predecessorObOptional =
//                    obRowRepository.findById(
//                            new ObRowId(
//                                    processableOb.getObRowId().getOtlAlort(),
//                                    processableOb.getObRowId().getOtlSchl(),
//                                    processableOb.getObRowId().getOtlPlz(),
//                                    "G"
//                            )
//                    );
//            if (predecessorObOptional.isPresent()
//                    && !Objects.equals(predecessorObOptional.get(), processableOb)) {
//                ObRow formerExistingOb = predecessorObOptional.get();
//                formerExistingOb.setOutdatedAt(LocalDateTime.now());
//                obRowRepository.saveAndFlush(formerExistingOb);
//                processableOb.setAlreadyAppliedAt(LocalDateTime.now());
//                processableOb.getObRowId().setOtlStatus("G");
//                obRowRepository.saveAndFlush(processableOb);
//            }
//        });
//    }
//
//    private void handleReplacementsOr() {
//        List<OrRow> processableSingleSuccessors =
//                orRowRepository.findReplacementCandidates();
//        log.info("Processing replacements of Or objects... {} found", processableSingleSuccessors.size());
//        processableSingleSuccessors.stream().filter(Objects::nonNull).forEach(processableOr ->
//        {
//            Optional<OrRow> predecessorOrOptional =
//                    orRowRepository.findById(
//                            new OrRowId(
//                                    processableOr.getOrRowId().getOrtAlort(),
//                                    "G"
//                            )
//                    );
//            if (predecessorOrOptional.isPresent()
//                    && !Objects.equals(predecessorOrOptional.get(), processableOr)) {
//                OrRow formerExistingOr = predecessorOrOptional.get();
//                formerExistingOr.setOutdatedAt(LocalDateTime.now());
//                orRowRepository.saveAndFlush(formerExistingOr);
//                processableOr.setAlreadyAppliedAt(LocalDateTime.now());
//                processableOr.getOrRowId().setOrtStatus("G");
//                orRowRepository.saveAndFlush(processableOr);
//            }
//        });
//    }
//
//    private void handleReplacementsSb() {
//        List<SbRow> processableSingleSuccessors =
//                sbRowRepository.findReplacementCandidates();
//        log.info("Processing replacements of Sb objects... {} found", processableSingleSuccessors.size());
//        processableSingleSuccessors.stream().filter(Objects::nonNull).forEach(processableSb ->
//        {
//            Optional<SbRow> predecessorSbOptional =
//                    sbRowRepository.findById(
//                            new SbRowId(
//                                    processableSb.getSbRowId().getStrAlOrt(),
//                                    processableSb.getSbRowId().getStrNamenSchl(),
//                                    processableSb.getSbRowId().getStrBundLfdnr(),
//                                    processableSb.getSbRowId().getStrHnrVon(),
//                                    processableSb.getSbRowId().getStrHnrBis(),
//                                    "G",
//                                    processableSb.getSbRowId().getStrHnr1000()
//                            )
//                    );
//            if (predecessorSbOptional.isPresent()
//                    && !Objects.equals(predecessorSbOptional.get(), processableSb)) {
//                SbRow formerExistingSb = predecessorSbOptional.get();
//                sbRowRepository.setOutdated(formerExistingSb.getSbRowId(),LocalDateTime.now());
//                processableSb.getSbRowId().setStrStatus("G");
//                sbRowRepository.setAlreadyApplied(processableSb.getSbRowId(),LocalDateTime.now());
//            }
//        });
//    }

}
