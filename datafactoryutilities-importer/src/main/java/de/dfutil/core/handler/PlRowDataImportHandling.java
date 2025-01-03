package de.dfutil.core.handler;

import de.dfutil.dao.jpa.PlRowRepository;
import de.dfutil.entities.jpa.PlRow;
import de.dfutil.events.RowParsedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class PlRowDataImportHandling implements DataImportHandler {

    private static final Logger log = LoggerFactory.getLogger(PlRowDataImportHandling.class);

    @Autowired
    private PlRowRepository jpaDao;

    public PlRowDataImportHandling(PlRowRepository jpaDao) {
        this.jpaDao = jpaDao;
    }

    @EventListener(condition = "#event.rowType.name().contains('PL')")
    public void onApplicationEvent(@NonNull RowParsedEvent event) {
        log.debug("event '{}' of type '{}' received", event.getSource(), event.rowType());
        persistEventContent2DataSources(event);
    }

    @Override
    public void persistEventContent2DataSources(RowParsedEvent event) {
        jpaDao.save(new PlRow().parseFrom(event.row()));
    }

}
