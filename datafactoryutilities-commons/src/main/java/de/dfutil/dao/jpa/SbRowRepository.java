package de.dfutil.dao.jpa;

import de.dfutil.entities.jpa.SbRow;
import de.dfutil.entities.jpa.ids.SbRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SbRowRepository extends JpaRepository<SbRow, SbRowId> {

    @Query("Select strasse from SbRow strasse where strasse.sbRowId.strStatus != 'G'")
    List<SbRow> findByStatusSuccessionRelevant();

    @Query("Select strasse from SbRow strasse where strasse.sbRowId.strStatus = 'G' and strasse.sbRowId = ?1 and strasse.outdatedAt is null")
    List<SbRow> findValidById(SbRowId id);

    @Query("Select strasse from SbRow strasse where strasse.sbRowId.strStatus = 'W' and strasse.alreadyAppliedAt is null")
    List<SbRow> findProcessableOrphans();

    @Query("Select strasse from SbRow strasse where strasse.sbRowId.strStatus = 'N' and strasse.alreadyAppliedAt is null")
    List<SbRow> findAllWithMultipleSuccessors();

    @Query("Select strasse from SbRow strasse where strasse.sbRowId.strStatus = 'S' and strasse.alreadyAppliedAt is null")
    List<SbRow> findAllWithSingleSuccessor();

}
