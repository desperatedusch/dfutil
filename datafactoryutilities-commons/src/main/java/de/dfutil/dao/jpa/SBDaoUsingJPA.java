package de.dfutil.dao.jpa;

import de.dfutil.dao.Dao;
import de.dfutil.entities.SBRow;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SBDaoUsingJPA implements Dao<SBRow> {

    private final EntityManager entityManager;

    public SBDaoUsingJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<SBRow> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<SBRow> getAll() {
        return List.of();
    }

    @Override
    public void save(SBRow sbRow) {
        entityManager.persist(sbRow);
    }

    @Override
    public void update(SBRow street, String[] params) {}

    @Override
    public void delete(SBRow street) {}

}