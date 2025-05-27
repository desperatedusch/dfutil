package de.dfutil.importing;

import de.dfutil.dao.ObRowRepository;
import de.dfutil.dao.OrRowRepository;
import de.dfutil.dao.SbRowRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Merges {

    private static final Logger log = LoggerFactory.getLogger(Merges.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public Merges(OrRowRepository orRowRepository, ObRowRepository obRowRepository, SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional
    public void process() {
        handleMergedOb();
        handleMergedSb();
        handleMergedOr();
    }

    private void handleMergedOb() {

    }

    private void handleMergedOr() {

    }

    private void handleMergedSb() {

    }

}
