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
import java.util.Optional;

import static de.dfutil.entities.ArchivingState.INVALID;
import static de.dfutil.entities.ArchivingState.VALID;

@Service
public class Replacements implements Successions {

    private static final Logger log = LoggerFactory.getLogger(Replacements.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public Replacements(OrRowRepository orRowRepository, ObRowRepository obRowRepository, SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleObObjects() {
        List<ObRowEntity> processableSingleSuccessors =
                obRowRepository.findReplacementCandidates();
        log.debug("Processing replacements of Ob objects... {} found", processableSingleSuccessors.size());
        processableSingleSuccessors.stream().filter(Objects::nonNull).forEach(processableOb ->
        {
            Optional<ObRowEntity> predecessorObOptional =
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
                ObRowEntity formerExistingOb = predecessorObOptional.get();
                orRowRepository.changeStatus(formerExistingOb.getUuid(), INVALID.symbol());
                obRowRepository.outdate(formerExistingOb.getUuid(), LocalDateTime.now());
                orRowRepository.changeStatus(processableOb.getUuid(), VALID.symbol());
                obRowRepository.apply(processableOb.getUuid(), LocalDateTime.now());
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleOrObjects() {
        List<OrRowEntity> processableSingleSuccessors =
                orRowRepository.findReplacementCandidates();
        log.debug("Processing replacements of Or objects... {} found", processableSingleSuccessors.size());
        processableSingleSuccessors.stream().filter(Objects::nonNull).forEach(processableOr ->
        {
            Optional<OrRowEntity> predecessorOrOptional =
                    orRowRepository.findById(
                            new OrRowId(
                                    processableOr.getOrRowId().getOrtAlort(),
                                    "G"
                            )
                    );
            if (predecessorOrOptional.isPresent()
                    && !Objects.equals(predecessorOrOptional.get(), processableOr)) {
                OrRowEntity formerExistingOr = predecessorOrOptional.get();
                orRowRepository.changeStatus(formerExistingOr.getUuid(), INVALID.symbol());
                orRowRepository.outdate(formerExistingOr.getUuid(), LocalDateTime.now());
                orRowRepository.changeStatus(processableOr.getUuid(), VALID.symbol());
                orRowRepository.apply(processableOr.getUuid(), LocalDateTime.now());
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleSbObjects() {
        List<SbRowEntity> processableSingleSuccessors =
                sbRowRepository.findReplacementCandidates();
        log.debug("Processing replacements of Sb objects... {} found", processableSingleSuccessors.size());
        processableSingleSuccessors.stream().filter(Objects::nonNull).forEach(processableSb ->
        {
            Optional<SbRowEntity> predecessorSbOptional =
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
                SbRowEntity formerExistingSb = predecessorSbOptional.get();
                sbRowRepository.changeStatus(formerExistingSb.getUuid(), INVALID.symbol());
                sbRowRepository.outdate(formerExistingSb.getUuid(), LocalDateTime.now());
                sbRowRepository.changeStatus(processableSb.getUuid(), VALID.symbol());
                sbRowRepository.apply(processableSb.getUuid(), LocalDateTime.now());
            }
        });
    }

}
