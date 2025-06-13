package de.dfutil.dao;

import de.dfutil.entities.ImportResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportResultRepository extends JpaRepository<ImportResultEntity, Long> {

}
