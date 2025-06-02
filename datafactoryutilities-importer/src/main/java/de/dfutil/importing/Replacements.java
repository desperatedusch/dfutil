package de.dfutil.importing;

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
public class Replacements {

    private static final Logger log = LoggerFactory.getLogger(Replacements.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public Replacements(OrRowRepository orRowRepository, ObRowRepository obRowRepository, SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleOb() {
        List<ObRow> processableSingleSuccessors =
                obRowRepository.findReplacementCandidates();
        log.debug("Processing replacements of Ob objects... {} found", processableSingleSuccessors.size());
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
            if (predecessorObOptional.isPresent()
                    && !Objects.equals(predecessorObOptional.get(), processableOb)) {
                ObRow formerExistingOb = predecessorObOptional.get();
                formerExistingOb.setOutdatedAt(LocalDateTime.now());
                obRowRepository.saveAndFlush(formerExistingOb);
                processableOb.setAlreadyAppliedAt(LocalDateTime.now());
                processableOb.getObRowId().setOtlStatus("G");
                obRowRepository.saveAndFlush(processableOb);
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleOr() {
        List<OrRow> processableSingleSuccessors =
                orRowRepository.findReplacementCandidates();
        log.debug("Processing replacements of Or objects... {} found", processableSingleSuccessors.size());
        processableSingleSuccessors.stream().filter(Objects::nonNull).forEach(processableOr ->
        {
            Optional<OrRow> predecessorOrOptional =
                    orRowRepository.findById(
                            new OrRowId(
                                    processableOr.getOrRowId().getOrtAlort(),
                                    "G"
                            )
                    );
            if (predecessorOrOptional.isPresent()
                    && !Objects.equals(predecessorOrOptional.get(), processableOr)) {
                OrRow formerExistingOr = predecessorOrOptional.get();
                formerExistingOr.setOutdatedAt(LocalDateTime.now());
                orRowRepository.saveAndFlush(formerExistingOr);
                processableOr.setAlreadyAppliedAt(LocalDateTime.now());
                processableOr.getOrRowId().setOrtStatus("G");
                orRowRepository.saveAndFlush(processableOr);
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleSb() {
        List<SbRow> processableSingleSuccessors =
                sbRowRepository.findReplacementCandidates();
        log.debug("Processing replacements of Sb objects... {} found", processableSingleSuccessors.size());
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
            if (predecessorSbOptional.isPresent()
                    && !Objects.equals(predecessorSbOptional.get(), processableSb)) {
                SbRow formerExistingSb = predecessorSbOptional.get();
                sbRowRepository.setOutdated(formerExistingSb.getSbRowId(), LocalDateTime.now());
                processableSb.getSbRowId().setStrStatus("G");
                sbRowRepository.setAlreadyApplied(processableSb.getSbRowId(), LocalDateTime.now());
            }
        });
    }


}
