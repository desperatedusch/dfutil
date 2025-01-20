package de.dfutil.core.handler;

import de.dfutil.dao.jpa.SbRowRepository;
import de.dfutil.entities.jpa.SbRow;
import de.dfutil.events.RowParsedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@Profile({"eventbased-persisting", "!procedural-persisting "})
public class SbRowEventsHandling implements DataImportHandling {

    private static final Logger log = LoggerFactory.getLogger(SbRowEventsHandling.class);

    @Autowired
    private SbRowRepository jpaDao;

    public SbRowEventsHandling(SbRowRepository jpaDao) {
        this.jpaDao = jpaDao;
    }

    @EventListener(condition = "#event.rowType.name().startsWith('SB')")
    public void onApplicationEvent(@NonNull RowParsedEvent event) {
        log.debug("event '{}' of type '{}' received", event.getSource(), event.rowType());
        persistEventContent(event);
    }

    @Override
    public void persistEventContent(RowParsedEvent event) {
        SbRow entity = SbRow.parseFrom(event.row());
        if (jpaDao.findById(entity.getSbRowId()).isEmpty())
            jpaDao.save(entity);
        else
            log.info("Entity already exists: {}", entity);
    }

}
