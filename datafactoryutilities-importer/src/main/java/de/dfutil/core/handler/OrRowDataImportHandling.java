package de.dfutil.core.handler;

import de.dfutil.dao.jpa.OrRowRepository;
import de.dfutil.entities.jpa.OrRow;
import de.dfutil.events.RowParsedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class OrRowDataImportHandling implements DataImportHandler {

    private static final Logger log = LoggerFactory.getLogger(OrRowDataImportHandling.class);

    @Autowired
    private OrRowRepository jpaDao;

    public OrRowDataImportHandling(OrRowRepository jpaDao) {
        this.jpaDao = jpaDao;
    }

    @EventListener(condition = "#event.rowType.name().startsWith('OR')")
    public void onApplicationEvent(@NonNull RowParsedEvent event) {
        log.debug("event '{}' of type '{}' received", event.getSource(), event.rowType());
        persistEventContent(event);
    }

    @Override
    public void persistEventContent(RowParsedEvent event) {
        jpaDao.save(new OrRow().parseFrom(event.row()));
    }

}
