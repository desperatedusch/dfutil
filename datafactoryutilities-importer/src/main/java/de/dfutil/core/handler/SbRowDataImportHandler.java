package de.dfutil.core.handler;

import de.dfutil.dao.jpa.SbRowRepository;
import de.dfutil.entities.jpa.SbRow;
import de.dfutil.events.RowParsedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class SbRowDataImportHandler implements DataImportHandler {

    private static final Logger log = LoggerFactory.getLogger(SbRowDataImportHandler.class);

    @Autowired
    private SbRowRepository jpaDao;

    public SbRowDataImportHandler(SbRowRepository jpaDao) {
        this.jpaDao = jpaDao;
    }

    @EventListener(condition = "#event.rowType.name().contains('SB')")
    public void onApplicationEvent(@NonNull RowParsedEvent event) {
        log.info("event {} of type {} received", event.getSource(), event.rowType());
        persistEventContent2DataSources(event);
    }

    @Override
    public void persistEventContent2DataSources(RowParsedEvent event) {
        jpaDao.save(new SbRow().parseFrom(event.row()));
    }
}

