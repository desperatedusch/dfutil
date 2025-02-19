package de.dfutil.dao;

import de.dfutil.entities.ImportResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportResultRepository extends JpaRepository<ImportResult, Long> {

}
