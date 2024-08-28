package de.dfutil.dao;

import de.dfutil.entities.Strasse;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class StrassenDAO implements Dao<Strasse>{

    private EntityManager entityManager;

    @Override
    public Optional<Strasse> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Strasse> getAll() {
        return List.of();
    }

    @Override
    public void save(Strasse strasse) {

    }

    @Override
    public void update(Strasse strasse, String[] params) {

    }

    @Override
    public void delete(Strasse strasse) {

    }
}
