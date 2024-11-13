package de.dfutil.dao.jpa;

import de.dfutil.entities.SBRow;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class SBDaoUsingJPA  implements CrudRepository<SBRow, Long> {

    @Autowired
    private final EntityManager entityManager;

    public SBDaoUsingJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <S extends SBRow> S save(S entity) {
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
    public Optional<SBRow> findById(Long aLong) {
        return Optional.ofNullable(entityManager.find(SBRow.class, aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        return entityManager.find(SBRow.class, aLong) != null;
    }

    @Override
    public Iterable<SBRow> findAll() {
        return entityManager.createQuery("SELECT b FROM SBRow b").getResultList();
    }

    @Override
    public Iterable<SBRow> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException("Not supported yet. Use findById(Long aLong) or findAll() instead...");
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