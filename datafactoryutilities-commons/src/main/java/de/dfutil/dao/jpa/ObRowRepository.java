package de.dfutil.dao.jpa;

import de.dfutil.entities.jpa.ObRow;
import de.dfutil.entities.jpa.ids.ObRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObRowRepository extends JpaRepository<ObRow, ObRowId> {

    @Query("Select ob from ObRow ob where ob.obRowId.otlStatus != 'G'")
    List<ObRow> findByStatusSuccessionRelevant();

    @Query("Select ob from ObRow ob where ob.obRowId.otlStatus = 'W'  and ob.outdatedAt is null")
    List<ObRow> findProcessableOrphans();

    @Query("Select ob from ObRow ob where ob.obRowId.otlStatus = 'N'")
    List<ObRow> findAllByStatusN();

    @Query("Select ob from ObRow ob where ob.obRowId.otlStatus = 'S'")
    List<ObRow> findAllByStatusS();

}
