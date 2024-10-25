package de.dfutil.core.listener;

import de.dfutil.dao.jpa.SBDaoUsingJPA;
import de.dfutil.dao.redis.SBDaoPersistingIntoRedis;
import de.dfutil.entities.SBRow;
import de.dfutil.events.RowParsedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class SBDataImportHandler implements ApplicationListener<RowParsedEvent> {

    @Autowired
    private SBDaoUsingJPA jpaDao;

//    @Autowired
//    private SBDaoPersistingIntoRedis redisDao;

    public SBDataImportHandler(SBDaoUsingJPA jpaDao, SBDaoPersistingIntoRedis redisDao) {
        this.jpaDao = jpaDao;
//      this.redisDao = redisDao;
    }

    @Override
    public void onApplicationEvent(RowParsedEvent event) {
        jpaDao.save(new SBRow().parseFrom(((String) event.getSource()).getBytes()));
//        redisDao.save(event.getContent());
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
