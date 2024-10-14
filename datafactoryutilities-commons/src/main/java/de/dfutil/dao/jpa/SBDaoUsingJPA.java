package de.dfutil.dao.jpa;

import de.dfutil.entities.SBRow;
import jakarta.persistence.EntityManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SBDaoUsingJPA  implements CrudRepository<SBRow, Long> {

    private final EntityManager entityManager;

    public SBDaoUsingJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
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