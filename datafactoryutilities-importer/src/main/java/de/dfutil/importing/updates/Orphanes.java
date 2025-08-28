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

@Service
public class Orphanes implements Successions {

    private static final Logger log = LoggerFactory.getLogger(Orphanes.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public Orphanes(final OrRowRepository orRowRepository, final ObRowRepository obRowRepository, final SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleOrObjects() {
        final List<OrRowEntity> processableOrphanedOrObjects =
                this.orRowRepository.findProcessableOrphans();
        Orphanes.log.debug("Processing orphaned Or objects... {} found", processableOrphanedOrObjects.size());
        processableOrphanedOrObjects.stream().filter(Objects::nonNull).forEach(processableOr ->
        {
            final Optional<OrRowEntity> formerExistingOrOptional = this.orRowRepository.findById(
                    new OrRowId(
                            processableOr.getOrRowId().getOrtAlort(),
                            "G"
                    )
            );
            if (formerExistingOrOptional.isPresent()) {
                final OrRowEntity formerExistingOr = formerExistingOrOptional.get();
                this.orRowRepository.outdate(formerExistingOr.getUuid(), LocalDateTime.now());
                this.orRowRepository.apply(processableOr.getUuid(), LocalDateTime.now());
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleObObjects() {
        final List<ObRowEntity> processableOrphanedObObjects =
                this.obRowRepository.findProcessableOrphans();
        Orphanes.log.debug("Processing orphaned Ob objects... {} found", processableOrphanedObObjects.size());
        processableOrphanedObObjects.stream().filter(Objects::nonNull).forEach(processableOb ->
        {
            final Optional<ObRowEntity> formerExistingObOptional =
                    this.obRowRepository.findById(
                            new ObRowId(
                                    processableOb.getObRowId().getOtlAlort(),
                                    processableOb.getObRowId().getOtlSchl(),
                                    processableOb.getObRowId().getOtlPlz(),
                                    "G"
                            )
                    );
            if (formerExistingObOptional.isPresent()) {
                final ObRowEntity formerExistingOb = formerExistingObOptional.get();
                this.obRowRepository.outdate(formerExistingOb.getUuid(), LocalDateTime.now());
                this.obRowRepository.apply(processableOb.getUuid(), LocalDateTime.now());
            }
        });
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void handleSbObjects() {
        final List<SbRowEntity> processableOrphanedSbObjects =
                this.sbRowRepository.findProcessableOrphans();
        Orphanes.log.debug("Processing orphaned Sb objects... {} found", processableOrphanedSbObjects.size());
        processableOrphanedSbObjects.stream().filter(Objects::nonNull).forEach(processableSb ->
        {
            final Optional<SbRowEntity> formerExistingSbOptional =
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
            if (formerExistingSbOptional.isPresent()) {
                final SbRowEntity formerExistingSb = formerExistingSbOptional.get();
                this.sbRowRepository.outdate(formerExistingSb.getUuid(), LocalDateTime.now());
                this.sbRowRepository.apply(processableSb.getUuid(), LocalDateTime.now());
            }
        });
    }

}
