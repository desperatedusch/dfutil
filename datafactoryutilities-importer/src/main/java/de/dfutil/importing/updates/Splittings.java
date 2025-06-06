package de.dfutil.importing.updates;

import de.dfutil.dao.ObRowRepository;
import de.dfutil.dao.OrRowRepository;
import de.dfutil.dao.SbRowRepository;
import de.dfutil.entities.ObRow;
import de.dfutil.entities.OrRow;
import de.dfutil.entities.SbRow;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Splittings {

    private static final Logger log = LoggerFactory.getLogger(Splittings.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public Splittings(OrRowRepository orRowRepository, ObRowRepository obRowRepository, SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleSb() {
        List<SbRow> multipleSuccessors =
                sbRowRepository.findMultipleSuccessorCandidates();
        log.debug("Processing multiple successor candidates of Sb objects... {} found", multipleSuccessors.size());
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleOb() {
        List<ObRow> multipleSuccessors =
                obRowRepository.findMultipleSuccessorCandidates();
        log.debug("Processing multiple successor candidates of Ob objects... {} found", multipleSuccessors.size());
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleOr() {
        List<OrRow> multipleSuccessors =
                orRowRepository.findMultipleSuccessorCandidates();
        log.debug("Processing multiple successor candidates of Or objects... {} found", multipleSuccessors.size());
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void process() {
        handleOr();
        handleOb();
        handleSb();
    }



}
