package de.dfutil.dao;

import de.dfutil.entities.ObRow;
import de.dfutil.entities.ids.ObRowId;
import de.dfutil.entities.ids.OrRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObRowRepository extends JpaRepository<ObRow, ObRowId> {

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus != 'G'")
    List<ObRow> findByStatusSuccessionRelevant();

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus = 'G' and ortsteil.obRowId = ?1 and ortsteil.outdatedAt is null")
    List<ObRow> findValidById(OrRowId id);

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus = 'W' and ortsteil.alreadyAppliedAt is null")
    List<ObRow> findProcessableOrphans();

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus = 'N' and ortsteil.alreadyAppliedAt is null")
    List<ObRow> findAllWithMultipleSuccessors();

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus = 'S' and ortsteil.alreadyAppliedAt is null")
    List<ObRow> findAllWithSingleSuccessor();

}
