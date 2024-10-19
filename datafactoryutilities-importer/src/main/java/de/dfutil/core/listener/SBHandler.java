package de.dfutil.core.listener;

import de.dfutil.dao.jpa.SBDaoUsingJPA;
import de.dfutil.dao.redis.SBDaoPersistingIntoRedis;
import de.dfutil.entities.SBRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SBHandler {

    @Autowired
    private SBDaoUsingJPA jpaDao;

    @Autowired
    private SBDaoPersistingIntoRedis redisDao;

    public SBHandler() {    }


    public void updateOrInsert(SBRow content) {
        jpaDao.save(content);
        redisDao.save(content);
    }
}
