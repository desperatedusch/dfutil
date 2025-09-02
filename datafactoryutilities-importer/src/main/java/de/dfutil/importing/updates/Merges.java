package de.dfutil.importing.updates;

import de.dfutil.dao.ObRowRepository;
import de.dfutil.dao.OrRowRepository;
import de.dfutil.dao.SbRowRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Merges implements Successions {

    private static final Logger log = LoggerFactory.getLogger(Merges.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public Merges(OrRowRepository orRowRepository, ObRowRepository obRowRepository, SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleOrObjects() {
        var multipleSuccessors =
                orRowRepository.findMultipleSuccessorCandidates();
        log.info("Processing multiple successor candidates of Or objects... {} found", multipleSuccessors.size());
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleObObjects() {
        var multipleSuccessors =
                obRowRepository.findMultipleSuccessorCandidates();
        log.info("Processing multiple successor candidates of Ob objects... {} found", multipleSuccessors.size());
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleSbObjects() {
        var multipleSuccessors =
                sbRowRepository.findMultipleSuccessorCandidates();
        log.info("Processing multiple successor candidates of Sb objects... {} found", multipleSuccessors.size());
    }

}
