package de.dfutil.dao.jpa;

import com.google.common.collect.Iterables;
import de.dfutil.dao.SbDao;
import de.dfutil.entities.SBRow;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class SBDaoUsingJPA implements CrudRepository<SBRow, Long>, SbDao {

    @Autowired
    private final EntityManager entityManager;

    public SBDaoUsingJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <S extends SBRow> S save(@NonNull S entity) {
        return entityManager.merge(entity);
    }

    @Override
    public <S extends SBRow> Iterable<S> saveAll(Iterable<S> entities) {
        if (Objects.nonNull(entities)) {
            for (S entity : entities) {
                entityManager.merge(entity);
            }
        }
        return entities;
    }

    @Override
    public Optional<SBRow> findById(@NonNull Long aLong) {
        return Optional.ofNullable(entityManager.find(SBRow.class, aLong));
    }

    @Override
    public boolean existsById(@NonNull Long aLong) {
        return entityManager.find(SBRow.class, aLong) != null;
    }

    @Override
    public Iterable<SBRow> findAll() {
        return entityManager.createQuery("SELECT b FROM SBRow b").getResultList();
    }

    @Override
    public Iterable<SBRow> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException("Not supported yet. Use findById(Long aLong) or findAll() instead.");
    }

    @Override
    public long count() {
        return Iterables.size(findAll());
    }

    @Override
    public void deleteById(@NonNull Long aLong) {
        entityManager.remove(findById(aLong).orElseThrow(() -> new RuntimeException(String.format("No sbrow entity to delete found for given id: {} ", aLong))));
    }

    @Override
    public void delete(SBRow entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException("Not supported yet. Use deleteById(Long aLong) or deleteAll() instead.");
    }

    @Override
    public void deleteAll(Iterable<? extends SBRow> entities) {
        throw new UnsupportedOperationException("Not supported yet. Use deleteById(Long aLong) or deleteAll() instead.");
    }

    @Override
    public void deleteAll() {
        entityManager.clear();
        entityManager.flush();
        Query query = entityManager.createQuery("DELETE SBRow sb");
        query.executeUpdate();
    }
}