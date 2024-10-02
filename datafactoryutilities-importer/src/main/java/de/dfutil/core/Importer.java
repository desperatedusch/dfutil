package de.dfutil.core;

import de.dfutil.entities.SBRow;
import de.dfutil.dao.jpa.SBDaoUsingJPA;
import de.dfutil.events.CreatedFromSBRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class Importer{

    private static final Logger log = LoggerFactory.getLogger(Importer.class);

    @Autowired
    private SBDaoUsingJPA sbDao;


    public Importer() {

    }

    @EventListener(CreatedFromSBRow.class)
    public void onApplicationEvent(CreatedFromSBRow event) {
        log.trace("Event {} received", event);
        sbDao.save((SBRow) event.getSource());
    }



}
