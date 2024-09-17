package de.dfutil.dao;

import de.dfutil.entities.Street;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StreetDAO implements Dao<Street> {

    private EntityManager entityManager;

    @Override
    public Optional<Street> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Street> getAll() {
        return List.of();
    }

    @Override
    public void save(Street street) {
        entityManager.persist(street);

    }

    @Override
    public void update(Street street, String[] params) {

    }

    @Override
    public void delete(Street street) {

    }

}