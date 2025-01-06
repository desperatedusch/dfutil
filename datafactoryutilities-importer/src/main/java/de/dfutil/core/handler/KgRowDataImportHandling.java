package de.dfutil.core.handler;

import de.dfutil.dao.jpa.KgRowRepository;
import de.dfutil.entities.jpa.KgRow;
import de.dfutil.events.RowParsedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class KgRowDataImportHandling implements DataImportHandler {

    private static final Logger log = LoggerFactory.getLogger(KgRowDataImportHandling.class);

    @Autowired
    private KgRowRepository jpaDao;

    public KgRowDataImportHandling(KgRowRepository jpaDao) {
        this.jpaDao = jpaDao;
    }

    @EventListener(condition = "#event.rowType.name().startsWith('KG')")
    public void onApplicationEvent(@NonNull RowParsedEvent event) {
        log.debug("event '{}' of type '{}' received", event.getSource(), event.rowType());
        persistEventContent(event);
    }

    @Override
    public void persistEventContent(RowParsedEvent event) {
        jpaDao.save(new KgRow().parseFrom(event.row()));
    }

}
