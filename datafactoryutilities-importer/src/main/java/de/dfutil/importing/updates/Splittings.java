package de.dfutil.importing.updates;

import de.dfutil.dao.ObRowRepository;
import de.dfutil.dao.OrRowRepository;
import de.dfutil.dao.SbRowRepository;
import de.dfutil.entities.ObRowEntity;
import de.dfutil.entities.OrRowEntity;
import de.dfutil.entities.SbRowEntity;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Splittings implements Successions {

    private static final Logger log = LoggerFactory.getLogger(Splittings.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public Splittings(final OrRowRepository orRowRepository, final ObRowRepository obRowRepository, final SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleSbObjects() {
        final List<SbRowEntity> multipleSuccessors =
                this.sbRowRepository.findMultipleSuccessorCandidates();
        Splittings.log.debug("Processing multiple successor candidates of Sb objects... {} found", multipleSuccessors.size());
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleObObjects() {
        final List<ObRowEntity> multipleSuccessors =
                this.obRowRepository.findMultipleSuccessorCandidates();
        Splittings.log.debug("Processing multiple successor candidates of Ob objects... {} found", multipleSuccessors.size());
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleOrObjects() {
        final List<OrRowEntity> multipleSuccessors =
                this.orRowRepository.findMultipleSuccessorCandidates();
        Splittings.log.debug("Processing multiple successor candidates of Or objects... {} found", multipleSuccessors.size());
    }

}
