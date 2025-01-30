package de.dfutil.files.parsing.eventbased.handler;

import de.dfutil.dao.jpa.KgRowRepository;
import de.dfutil.entities.jpa.KgRow;
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
public class KgRowEventsHandling implements EventDrivenImportHandling {

    private static final Logger log = LoggerFactory.getLogger(KgRowEventsHandling.class);

    @Autowired
    private KgRowRepository jpaDao;

    public KgRowEventsHandling() {
    }

    @EventListener(condition = "#event.rowType.name().startsWith('KG')")
    public void onApplicationEvent(@NonNull RowParsedEvent event) {
        log.debug("event '{}' of type '{}' received", event.row(), event.rowType());
        persistEventContent(event);
    }

    @Override
    public void persistEventContent(RowParsedEvent event) {
        KgRow entity = KgRow.parseFrom(event.row());
        if (jpaDao.findById(entity.getKgRowId()).isEmpty())
            jpaDao.save(entity);
        else
            log.info("Entity already exists: {}", entity);
    }

}
