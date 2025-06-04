package de.dfutil.dao;

import de.dfutil.entities.SbRow;
import de.dfutil.entities.ids.SbRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SbRowRepository extends JpaRepository<SbRow, SbRowId> {

    @Query("Select strasse from SbRow strasse where strasse.sbRowId.strStatus != 'G' order by strasse.version asc")
    List<SbRow> findByStatusSuccessionRelevant();

    @Query("Select strasse from SbRow strasse where strasse.sbRowId.strStatus = 'G' and strasse.sbRowId = ?1 and strasse.outdatedAt is null order by strasse.version asc")
    List<SbRow> findValidById(SbRowId id);

    @Query("Select strasse from SbRow strasse where strasse.sbRowId.strStatus = 'W' and strasse.alreadyAppliedAt is null order by strasse.version asc")
    List<SbRow> findProcessableOrphans();

    @Query("Select strasse from SbRow strasse where strasse.sbRowId.strStatus IN ('1','2','3','4','5','6','7','8','9') and strasse.alreadyAppliedAt is null order by strasse.version asc")
    List<SbRow> findMultipleSuccessorCandidates();

    @Query("Select strasse from SbRow strasse where strasse.sbRowId.strStatus = 'S' and strasse.alreadyAppliedAt is null order by strasse.version asc")
    List<SbRow> findReplacementCandidates();

    @Query
    Optional<SbRow> findDistinctTopByUuid(UUID uuid);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update SbRow strasse set strasse.outdatedAt = :date where strasse.sbRowId = :strId")
    void outdate(@Param("strId") SbRowId strId, @Param("date") LocalDateTime date);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update SbRow strasse set strasse.alreadyAppliedAt = :date where strasse.sbRowId = :strId")
    void apply(@Param("strÎd") SbRowId strId, @Param("date") LocalDateTime date);

}
