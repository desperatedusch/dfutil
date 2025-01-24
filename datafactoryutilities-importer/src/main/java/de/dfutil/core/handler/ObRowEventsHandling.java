package de.dfutil.core.handler;

import de.dfutil.dao.jpa.ObRowRepository;
import de.dfutil.entities.jpa.ObRow;
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
public class ObRowEventsHandling implements EventDrivenImportHandling {

    private static final Logger log = LoggerFactory.getLogger(ObRowEventsHandling.class);

    @Autowired
    private ObRowRepository jpaDao;

    public ObRowEventsHandling() {
    }

    @EventListener(condition = "#event.rowType.name().startsWith('OB')")
    public void onApplicationEvent(@NonNull RowParsedEvent event) {
        log.debug("event '{}' of type '{}' received", event.row(), event.rowType());
        persistEventContent(event);
    }

    @Override
    public void persistEventContent(RowParsedEvent event) {
        ObRow entity = ObRow.parseFrom(event.row());
        if (jpaDao.findById(entity.getObRowId()).isEmpty())
            jpaDao.save(entity);
        else
            log.info("Entity already exists: {}", entity);
    }

}
