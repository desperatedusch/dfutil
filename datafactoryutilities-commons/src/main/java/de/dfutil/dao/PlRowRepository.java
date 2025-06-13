package de.dfutil.dao;

import de.dfutil.entities.PlRowEntity;
import de.dfutil.entities.ids.PlRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlRowRepository extends JpaRepository<PlRowEntity, PlRowId> {

    @Query
    PlRowEntity getByUuid(UUID uuid);

}
