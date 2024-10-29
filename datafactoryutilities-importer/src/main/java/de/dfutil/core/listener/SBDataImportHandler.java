package de.dfutil.core.listener;

import de.dfutil.dao.jpa.SBDaoUsingJPA;
import de.dfutil.dao.redis.SBDaoPersistingIntoRedis;
import de.dfutil.entities.SBRow;
import de.dfutil.events.RowParsedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class SBDataImportHandler implements DataImportHandler {

    private static final Logger log = LoggerFactory.getLogger(SBDataImportHandler.class);

    @Autowired
    private SBDaoUsingJPA jpaDao;

    @Autowired
    private SBDaoPersistingIntoRedis redisDao;

    public SBDataImportHandler(SBDaoUsingJPA jpaDao, SBDaoPersistingIntoRedis redisDao) {
        this.jpaDao = jpaDao;
        this.redisDao = redisDao;
    }

    @EventListener(condition = "#event.rowType.name().contains('SB')")
    public void onApplicationEvent(@NonNull RowParsedEvent event) {
        log.info("event {} of type {} received", event.getSource(), event.rowType());
        persistEventContent2DataSources(event);

    }

    @Override
    public void persistEventContent2DataSources(RowParsedEvent event) {
        event.row();
        SBRow sbRow = jpaDao.save(new SBRow().parseFrom(event.row()));
//        redisDao.save(event.getContent());
    }
}

