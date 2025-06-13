package de.dfutil.dao;

import de.dfutil.entities.OrRowEntiy;
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
public interface OrRowRepository extends JpaRepository<OrRowEntiy, OrRowId> {

    @Query("Select ort from OrRowEntiy ort where ort.orRowId.ortStatus != 'G'")
    List<OrRowEntiy> findByStatusSuccessionRelevant();

    @Query("Select ort from OrRowEntiy ort where ort.orRowId.ortStatus = 'G' and ort.orRowId = ?1 and ort.outdatedAt is null order by ort.version asc")
    List<OrRowEntiy> findValidById(OrRowId id);

    @Query("Select ort from OrRowEntiy ort where ort.orRowId.ortStatus = 'W' and ort.alreadyAppliedAt is null order by ort.version asc")
    List<OrRowEntiy> findProcessableOrphans();

    @Query("Select ort from OrRowEntiy ort where ort.orRowId.ortStatus IN ('1','2','3','4','5','6','7','8','9') and ort.alreadyAppliedAt is null order by ort.version asc")
    List<OrRowEntiy> findMultipleSuccessorCandidates();

    @Query("Select ort from OrRowEntiy ort where ort.orRowId.ortStatus = 'S' and ort.alreadyAppliedAt is null order by ort.version asc")
    List<OrRowEntiy> findReplacementCandidates();

    @Query
    OrRowEntiy getByUuid(UUID uuid);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update OrRowEntiy ort set ort.outdatedAt = :date where ort.uuid = :uuid")
    void outdate(@Param("uuid") UUID uuid, @Param("date") LocalDateTime date);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update OrRowEntiy ort set ort.orRowId.ortStatus = :ortStatus where ort.uuid = :uuid")
    void changeStatus(@Param("uuid") UUID uuid, @Param("ortStatus") String status);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update OrRowEntiy ort set ort.alreadyAppliedAt = :date where ort.uuid = :uuid")
    void apply(@Param("uuid") UUID uuid, @Param("date") LocalDateTime date);

}
