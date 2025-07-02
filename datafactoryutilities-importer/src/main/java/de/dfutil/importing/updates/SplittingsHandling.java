package de.dfutil.importing.updates;

import de.dfutil.dao.ObRowRepository;
import de.dfutil.dao.OrRowRepository;
import de.dfutil.dao.SbRowRepository;
import de.dfutil.entities.ObRowEntity;
import de.dfutil.entities.OrRowEntiy;
import de.dfutil.entities.SbRowEntity;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplittingsHandling implements SuccessionsHandling {

    private static final Logger log = LoggerFactory.getLogger(SplittingsHandling.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public SplittingsHandling(OrRowRepository orRowRepository, ObRowRepository obRowRepository, SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleSbObjects() {
        List<SbRowEntity> multipleSuccessors =
                sbRowRepository.findMultipleSuccessorCandidates();
        log.debug("Processing multiple successor candidates of Sb objects... {} found", multipleSuccessors.size());
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleObObjects() {
        List<ObRowEntity> multipleSuccessors =
                obRowRepository.findMultipleSuccessorCandidates();
        log.debug("Processing multiple successor candidates of Ob objects... {} found", multipleSuccessors.size());
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleOrObjects() {
        List<OrRowEntiy> multipleSuccessors =
                orRowRepository.findMultipleSuccessorCandidates();
        log.debug("Processing multiple successor candidates of Or objects... {} found", multipleSuccessors.size());
    }

}
