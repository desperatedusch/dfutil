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
public class SplittingsAndMerges implements Successions {

    private static final Logger log = LoggerFactory.getLogger(SplittingsAndMerges.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public SplittingsAndMerges(OrRowRepository orRowRepository, ObRowRepository obRowRepository, SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleSbObjects() {
        List<SbRowEntity> multipleSuccessions =
                sbRowRepository.findMultipleSuccessions();
        log.info("Processing multiple successor candidates of Sb objects... {} found", multipleSuccessions.size());
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleObObjects() {
        List<ObRowEntity> multipleSuccessions =
                obRowRepository.findMultipleSuccessions();
        log.info("Processing multiple successor candidates of Ob objects... {} found", multipleSuccessions.size());
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void handleOrObjects() {
        List<OrRowEntity> multipleSuccessions =
                orRowRepository.findMultipleSuccessions();
        log.info("Processing multiple successor candidates of Or objects... {} found", multipleSuccessions.size());
    }

}
