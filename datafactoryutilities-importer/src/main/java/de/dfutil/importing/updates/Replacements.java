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


    public Replacements(final OrRowRepository orRowRepository, final ObRowRepository obRowRepository, final SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleObObjects() {
        final List<ObRowEntity> processableSingleSuccessors =
                this.obRowRepository.findReplacementCandidates();
        Replacements.log.debug("Processing replacements of Ob objects... {} found", processableSingleSuccessors.size());
        processableSingleSuccessors.stream().filter(Objects::nonNull).forEach(processableOb ->
        {
            final Optional<ObRowEntity> predecessorObOptional =
                    this.obRowRepository.findById(
                            new ObRowId(
                                    processableOb.getObRowId().getOtlAlort(),
                                    processableOb.getObRowId().getOtlSchl(),
                                    processableOb.getObRowId().getOtlPlz(),
                                    "G"
                            )
                    );
            if (predecessorObOptional.isPresent()
                    && !Objects.equals(predecessorObOptional.get(), processableOb)) {
                final ObRowEntity formerExistingOb = predecessorObOptional.get();
                this.orRowRepository.changeStatus(formerExistingOb.getUuid(), INVALID.symbol());
                this.obRowRepository.outdate(formerExistingOb.getUuid(), LocalDateTime.now());
                this.orRowRepository.changeStatus(processableOb.getUuid(), VALID.symbol());
                this.obRowRepository.apply(processableOb.getUuid(), LocalDateTime.now());
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleOrObjects() {
        final List<OrRowEntity> processableSingleSuccessors =
                this.orRowRepository.findReplacementCandidates();
        Replacements.log.debug("Processing replacements of Or objects... {} found", processableSingleSuccessors.size());
        processableSingleSuccessors.stream().filter(Objects::nonNull).forEach(processableOr ->
        {
            final Optional<OrRowEntity> predecessorOrOptional =
                    this.orRowRepository.findById(
                            new OrRowId(
                                    processableOr.getOrRowId().getOrtAlort(),
                                    "G"
                            )
                    );
            if (predecessorOrOptional.isPresent()
                    && !Objects.equals(predecessorOrOptional.get(), processableOr)) {
                final OrRowEntity formerExistingOr = predecessorOrOptional.get();
                this.orRowRepository.changeStatus(formerExistingOr.getUuid(), INVALID.symbol());
                this.orRowRepository.outdate(formerExistingOr.getUuid(), LocalDateTime.now());
                this.orRowRepository.changeStatus(processableOr.getUuid(), VALID.symbol());
                this.orRowRepository.apply(processableOr.getUuid(), LocalDateTime.now());
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleSbObjects() {
        final List<SbRowEntity> processableSingleSuccessors =
                this.sbRowRepository.findReplacementCandidates();
        Replacements.log.debug("Processing replacements of Sb objects... {} found", processableSingleSuccessors.size());
        processableSingleSuccessors.stream().filter(Objects::nonNull).forEach(processableSb ->
        {
            final Optional<SbRowEntity> predecessorSbOptional =
                    this.sbRowRepository.findById(
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
                final SbRowEntity formerExistingSb = predecessorSbOptional.get();
                this.sbRowRepository.changeStatus(formerExistingSb.getUuid(), INVALID.symbol());
                this.sbRowRepository.outdate(formerExistingSb.getUuid(), LocalDateTime.now());
                this.sbRowRepository.changeStatus(processableSb.getUuid(), VALID.symbol());
                this.sbRowRepository.apply(processableSb.getUuid(), LocalDateTime.now());
            }
        });
    }

}
