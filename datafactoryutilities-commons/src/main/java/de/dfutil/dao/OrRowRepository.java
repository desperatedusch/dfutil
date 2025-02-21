package de.dfutil.dao;

import de.dfutil.entities.OrRow;
import de.dfutil.entities.ids.OrRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrRowRepository extends JpaRepository<OrRow, OrRowId> {

    @Query("Select ort from OrRow ort where ort.orRowId.ortStatus != 'G'")
    List<OrRow> findByStatusSuccessionRelevant();

    @Query("Select ort from OrRow ort where ort.orRowId.ortStatus = 'G' and ort.orRowId = ?1 and ort.outdatedAt is null")
    List<OrRow> findValidById(OrRowId id);

    @Query("Select ort from OrRow ort where ort.orRowId.ortStatus = 'W' and ort.alreadyAppliedAt is null")
    List<OrRow> findProcessableOrphans();

    @Query("Select ort from OrRow ort where ort.orRowId.ortStatus = 'N' and ort.alreadyAppliedAt is null")
    List<OrRow> findMultipleSuccessors();

    @Query("Select ort from OrRow ort where ort.orRowId.ortStatus = 'S' and ort.alreadyAppliedAt is null")
    List<OrRow> findSingleSuccessors();

}
