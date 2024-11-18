package de.dfutil.dao;

import de.dfutil.entities.SBRow;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface SbDao {

    <S extends SBRow> S save(@NonNull S entity);

    <S extends SBRow> Iterable<S> saveAll(Iterable<S> entities);

    Optional<SBRow> findById(@NonNull Long aLong);

    Iterable<SBRow> findAll();

    void deleteById(@NonNull Long aLong);

    void delete(SBRow entity);

    void deleteAll();
}
