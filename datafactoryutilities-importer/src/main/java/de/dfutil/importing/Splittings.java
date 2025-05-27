package de.dfutil.importing;

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

    @Transactional
    public void process() {
        handleSplittedOb();
        handleSplittedSb();
        handleSplittedOr();
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


}
