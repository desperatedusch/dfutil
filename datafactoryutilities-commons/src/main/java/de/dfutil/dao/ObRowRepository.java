package de.dfutil.dao;

import de.dfutil.entities.ObRow;
import de.dfutil.entities.ids.ObRowId;
import de.dfutil.entities.ids.OrRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ObRowRepository extends JpaRepository<ObRow, ObRowId> {

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus != 'G'")
    List<ObRow> findByStatusSuccessionRelevant();

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus = 'G' and ortsteil.obRowId = ?1 and ortsteil.outdatedAt is null order by ortsteil.version asc")
    List<ObRow> findValidById(OrRowId id);

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus = 'W' and ortsteil.alreadyAppliedAt is null order by ortsteil.version asc")
    List<ObRow> findProcessableOrphans();

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus IN ('1','2','3','4','5','6','7','8','9') and ortsteil.alreadyAppliedAt is null order by ortsteil.version asc")
    List<ObRow> findMultipleSuccessorCandidates();

    @Query("Select ortsteil from ObRow ortsteil where ortsteil.obRowId.otlStatus = 'S' and ortsteil.alreadyAppliedAt is null order by ortsteil.version asc")
    List<ObRow> findReplacementCandidates();

    @Query
    ObRow getByUuid(UUID uuid);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update ObRow ortsteil set ortsteil.outdatedAt = :date where ortsteil.uuid = :uuid")
    void outdate(@Param("uuid") UUID uuid, @Param("date") LocalDateTime date);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update ObRow ortsteil set ortsteil.obRowId.otlStatus = :otlStatus where ortsteil.uuid = :uuid")
    void changeStatus(@Param("uuid") UUID uuid, @Param("otlStatus") String status);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update ObRow ortsteil set ortsteil.alreadyAppliedAt = :date where ortsteil.uuid = :uuid")
    void apply(@Param("uuid") UUID uuid, @Param("date") LocalDateTime date);

}
