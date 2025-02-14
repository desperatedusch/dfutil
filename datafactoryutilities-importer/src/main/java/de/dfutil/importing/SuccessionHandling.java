package de.dfutil.importing;

import de.dfutil.dao.jpa.ObRowRepository;
import de.dfutil.dao.jpa.OrRowRepository;
import de.dfutil.dao.jpa.SbRowRepository;
import de.dfutil.entities.jpa.ObRow;
import de.dfutil.entities.jpa.OrRow;
import de.dfutil.entities.jpa.SbRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuccessionHandling {

    @Autowired
    private ObRowRepository obRowRepository;
    @Autowired
    private OrRowRepository orRowRepository;
    @Autowired
    private SbRowRepository sbRowRepository;

    public void handleOrOrphans() {
        List<OrRow> processableOrphanedOr = orRowRepository.findProcessableOrphans();
        List<ObRow> processableOrphanedOb = obRowRepository.findProcessableOrphans();
        List<SbRow> processableOrphanedSb = sbRowRepository.findProcessableOrphans();

    }

    public void hasSuccessor() {

    }

    public void hasSuccessors() {

    }

}
