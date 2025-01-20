package de.dfutil.core.handler;

import de.dfutil.dao.jpa.OrRowRepository;
import de.dfutil.entities.jpa.OrRow;
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
public class OrRowEventsHandling implements DataImportHandling {

    private static final Logger log = LoggerFactory.getLogger(OrRowEventsHandling.class);

    @Autowired
    private OrRowRepository jpaDao;

    public OrRowEventsHandling(OrRowRepository jpaDao) {
        this.jpaDao = jpaDao;
    }

    @EventListener(condition = "#event.rowType.name().startsWith('OR')")
    public void onApplicationEvent(@NonNull RowParsedEvent event) {
        log.debug("event '{}' of type '{}' received", event.getSource(), event.rowType());
        persistEventContent(event);
    }

    @Override
    public void persistEventContent(RowParsedEvent event) {
        OrRow entity = OrRow.parseFrom(event.row());
        if (jpaDao.findById(entity.getOrRowId()).isEmpty())
            jpaDao.save(entity);
        else
            log.info("Entity already exists: {}", entity);
    }

}
