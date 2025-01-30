package de.dfutil.dao.jpa;

import de.dfutil.entities.jpa.SbRow;
import de.dfutil.entities.jpa.ids.SbRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SbRowRepository extends JpaRepository<SbRow, SbRowId> {

    @Query("Select sb from SbRow sb where sb.sbRowId.strStatus != 'G'")
    List<SbRow> findByStatusSuccessionRelevant();

    @Query("Select sb from SbRow sb where sb.sbRowId.strStatus = 'W' and sb.outdatedAt is null ")
    List<SbRow> findProcessableOrphans();

    @Query("Select sb from SbRow sb where sb.sbRowId.strStatus = 'N'")
    List<SbRow> findAllByStatusN();

    @Query("Select sb from SbRow sb where sb.sbRowId.strStatus = 'S'")
    List<SbRow> findAllByStatusS();


}
