package de.dfutil.dao;

import de.dfutil.entities.PlRow;
import de.dfutil.entities.SbRow;
import de.dfutil.entities.ids.PlRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlRowRepository extends JpaRepository<PlRow, PlRowId> {

    @Query
    SbRow getByUuid(UUID uuid);

}
