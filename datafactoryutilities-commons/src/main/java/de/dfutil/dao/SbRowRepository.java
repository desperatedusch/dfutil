package de.dfutil.dao;

import de.dfutil.entities.SbRowEntity;
import de.dfutil.entities.ids.SbRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SbRowRepository extends JpaRepository<SbRowEntity, SbRowId>, SuccessionsApplicableRepository {

    @Query("Select strasse from SbRowEntity strasse where strasse.sbRowId.strStatus != 'G' order by strasse.version asc")
    List<SbRowEntity> findByStatusSuccessionRelevant();

    @Query("Select strasse from SbRowEntity strasse where strasse.sbRowId.strStatus = 'G' and strasse.sbRowId = ?1 and strasse.outdatedAt is null order by strasse.version asc")
    List<SbRowEntity> findValidById(SbRowId id);

    @Query("Select strasse from SbRowEntity strasse where strasse.sbRowId.strStatus = 'W' and strasse.alreadyAppliedAt is null order by strasse.version asc")
    List<SbRowEntity> findProcessableOrphans();

    @Query("Select strasse from SbRowEntity strasse where strasse.sbRowId.strStatus IN ('1','2','3','4','5','6','7','8','9') and strasse.alreadyAppliedAt is null order by strasse.version asc")
    List<SbRowEntity> findMultipleSuccessorCandidates();

    @Query("Select strasse from SbRowEntity strasse where strasse.sbRowId.strStatus = 'S' and strasse.alreadyAppliedAt is null order by strasse.version asc")
    List<SbRowEntity> findReplacementCandidates();

    @Query
    SbRowEntity getByUuid(UUID uuid);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update SbRowEntity strasse set strasse.outdatedAt = :date where strasse.uuid = :uuid")
    void outdate(@Param("uuid") UUID uuid, @Param("date") LocalDateTime date);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update SbRowEntity strasse set strasse.sbRowId.strStatus = :strStatus where strasse.uuid = :uuid")
    void changeStatus(@Param("uuid") UUID uuid, @Param("strStatus") String status);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update SbRowEntity strasse set strasse.alreadyAppliedAt = :date where strasse.uuid = :uuid")
    void apply(@Param("uuid") UUID uuid, @Param("date") LocalDateTime date);

    @Modifying
    @Query("update SbRowEntity strasse set strasse.outdatedAt = null")
    void resetAppliedState();

    @Modifying
    @Query("update SbRowEntity strasse set strasse.outdatedAt = null")
    void resetOutdatedState();

}
