package de.dfutil.core;

import de.dfutil.entities.SBRow;
import de.dfutil.dao.jpa.SBDaoUsingJPA;
import de.dfutil.events.CreatedFromSBRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class Importer{

    private static final Logger log = LoggerFactory.getLogger(Importer.class);

    private final SBDaoUsingJPA sbDao;

    public Importer(SBDaoUsingJPA sbDao) {
        this.sbDao = sbDao;
    }

    @EventListener(CreatedFromSBRow.class)
    public void onApplicationEvent(CreatedFromSBRow event) {
        log.trace("Event {} received", event);
        //FIXME Type check and exceptionhandling with rte
        sbDao.save((SBRow) event.getSource());
    }


}
