package de.dfutil.dao.jpa;

import de.dfutil.entities.jpa.OrRow;
import de.dfutil.entities.jpa.ids.OrRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrRowRepository extends JpaRepository<OrRow, OrRowId> {

    @Query("Select ort from OrRow ort where ort.orRowId.ortStatus != 'G'")
    List<OrRow> findByStatusSuccessionRelevant();

    @Query("Select ort from OrRow ort where ort.orRowId.ortStatus = 'G' and ort.orRowId = ?1")
    List<OrRow> findValidById(OrRowId id);

    @Query("Select ort from OrRow ort where ort.orRowId.ortStatus = 'W' and ort.outdatedAt is null ")
    List<OrRow> findProcessableOrphans();

    @Query("Select ort from OrRow ort where ort.orRowId.ortStatus = 'N'")
    List<OrRow> findAllWithMultipleSuccessors();

    @Query("Select ort from OrRow ort where ort.orRowId.ortStatus = 'S'")
    List<OrRow> findAllfindAllWithSingleSuccessor();

}
