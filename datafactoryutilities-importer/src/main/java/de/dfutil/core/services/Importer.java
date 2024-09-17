package de.dfutil.core.services;

import de.dfutil.dao.StreetDAO;
import de.dfutil.entities.Street;
import de.dfutil.events.StreetCreatedFromSBRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class Importer{

    private static final Logger log = LoggerFactory.getLogger(Importer.class);

    private final StreetDAO streetDao;

    public Importer(StreetDAO streetDao) {
        this.streetDao = streetDao;
    }

    @EventListener(StreetCreatedFromSBRow.class)
    public void onApplicationEvent(StreetCreatedFromSBRow event) {
        log.trace("Event {} received", event);
        //FIXME Type check and exceptionhandling with rte
        streetDao.save((Street)event.getSource());
    }


}
