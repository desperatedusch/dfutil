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

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus = 'G' and ortsteil.obRowId = ?1 and ortsteil.obRowId.outdatedAt is null order by ortsteil.version asc")
    List<ObRow> findValidById(OrRowId id);

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus = 'W' and ortsteil.obRowId.alreadyAppliedAt is null order by ortsteil.version asc")
    List<ObRow> findProcessableOrphans();

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus IN ('1','2','3','4','5','6','7','8','9') and ortsteil.obRowId.alreadyAppliedAt is null order by ortsteil.version asc")
    List<ObRow> findMultipleSuccessorCandidates();

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus = 'S' and ortsteil.obRowId.alreadyAppliedAt is null order by ortsteil.version asc")
    List<ObRow> findReplacementCandidates();

}
