package de.dfutil.core.handler;

import de.dfutil.dao.jpa.ObRowRepository;
import de.dfutil.entities.jpa.ObRow;
import de.dfutil.events.RowParsedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ObRowDataImportHandling implements DataImportHandler {

    private static final Logger log = LoggerFactory.getLogger(ObRowDataImportHandling.class);

    @Autowired
    private ObRowRepository jpaDao;

    public ObRowDataImportHandling(ObRowRepository jpaDao) {
        this.jpaDao = jpaDao;
    }

    @EventListener(condition = "#event.rowType.name().contains('OB')")
    public void onApplicationEvent(@NonNull RowParsedEvent event) {
        log.debug("event '{}' of type '{}' received", event.getSource(), event.rowType());
        persistEventContent2DataSources(event);
    }

    @Override
    public void persistEventContent2DataSources(RowParsedEvent event) {
        jpaDao.save(new ObRow().parseFrom(event.row()));
    }

}
