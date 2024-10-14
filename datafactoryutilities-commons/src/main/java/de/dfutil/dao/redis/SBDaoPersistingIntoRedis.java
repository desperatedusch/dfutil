package de.dfutil.dao.redis;

import de.dfutil.entities.SBRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SBDaoPersistingIntoRedis implements CrudRepository<SBRow,Long> {


    @Autowired
    RedisTemplate<String, SBRow> redisTemplate;

    public SBDaoPersistingIntoRedis(RedisTemplate<String, SBRow> template) {
        this.redisTemplate = template;
    }

    @Override
    public <S extends SBRow> S save(S entity) {
        return null;
    }

    @Override
    public <S extends SBRow> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<SBRow> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<SBRow> findAll() {
        return null;
    }

    @Override
    public Iterable<SBRow> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(SBRow entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends SBRow> entities) {

    }

    @Override
    public void deleteAll() {

    }

}