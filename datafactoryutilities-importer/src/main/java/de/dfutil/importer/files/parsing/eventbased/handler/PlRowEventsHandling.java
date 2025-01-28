package de.dfutil.importer.files.parsing.eventbased.handler;

import de.dfutil.dao.jpa.PlRowRepository;
import de.dfutil.entities.jpa.PlRow;
import de.dfutil.events.RowParsedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@Profile({"eventbased-importing", "!procedural-importing "})
public class PlRowEventsHandling implements EventDrivenImportHandling {

    private static final Logger log = LoggerFactory.getLogger(PlRowEventsHandling.class);

    @Autowired
    private PlRowRepository jpaDao;

    public PlRowEventsHandling() {
    }

    @EventListener(condition = "#event.rowType.name().startsWith('PL')")
    public void onApplicationEvent(@NonNull RowParsedEvent event) {
        log.debug("event '{}' of type '{}' received", event.row(), event.rowType());
        persistEventContent(event);
    }

    @Override
    public void persistEventContent(RowParsedEvent event) {
        PlRow entity = PlRow.parseFrom(event.row());
        if (jpaDao.findById(entity.getPlRowId()).isEmpty())
            jpaDao.save(entity);
        else
            log.info("Entity already exists: {}", entity);
    }

}
