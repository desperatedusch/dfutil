package de.dfutil.dao.redis;

import de.dfutil.entities.redis.SbRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Profile({"redis", "!jpa"})
@Repository
public class SbRowRepository implements CrudRepository<SbRow, Long> {

    @Autowired
    RedisTemplate<String, SbRow> redisTemplate;

    public SbRowRepository(RedisTemplate<String, SbRow> template) {
        this.redisTemplate = template;
    }

    @Override
    public <S extends SbRow> S save(S entity) {
        return null;
    }

    @Override
    public <S extends SbRow> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<SbRow> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<SbRow> findAll() {
        return null;
    }

    @Override
    public Iterable<SbRow> findAllById(Iterable<Long> longs) {
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
    public void delete(SbRow entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends SbRow> entities) {

    }

    @Override
    public void deleteAll() {

    }

}