package de.dfutil.dao.jpa;

import de.dfutil.entities.jpa.SbRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SbRowRepository extends JpaRepository<SbRow, Long> {

}
