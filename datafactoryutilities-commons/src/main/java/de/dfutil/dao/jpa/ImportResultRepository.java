package de.dfutil.dao.jpa;

import de.dfutil.entities.jpa.ImportResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportResultRepository extends JpaRepository<ImportResult, Long> {

    @Query("SELECT importResult FROM ImportResult importResult WHERE importResult.importSuccessful")
    public List<ImportResult> findAllSuccessfulImportResults();

}
