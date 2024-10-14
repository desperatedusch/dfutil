package de.dfutil.dao.redis;

import de.dfutil.dao.Dao;
import de.dfutil.entities.SBRow;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SBDaoPersistingIntoRedis implements Dao<SBRow> {


    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    ValueOperations<String, Object> valueOperations = null;

    public SBDaoPersistingIntoRedis(RedisTemplate<String, Object> template) {
        this.redisTemplate = template;
    }

    @Override
    public Optional<SBRow> get(long id) { throw new UnsupportedOperationException("not implemented");   }

    @Override
    public List<SBRow> getAll() { throw new UnsupportedOperationException("not implemented");}

    @Override
    public void save(SBRow sbRow) { throw new UnsupportedOperationException("not implemented");}

    @Override
    public void update(SBRow sbRow, String[] params) { throw new UnsupportedOperationException("not implemented");}

    @Override
    public void delete(SBRow sbRow) { throw new UnsupportedOperationException("not implemented");}

    public void mergeIfExists(SBRow sbRow) { throw new UnsupportedOperationException("not implemented");}

}