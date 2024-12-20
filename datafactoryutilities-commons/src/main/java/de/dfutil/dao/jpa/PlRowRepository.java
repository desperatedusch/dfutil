package de.dfutil.dao.jpa;

import de.dfutil.entities.jpa.PlRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlRowRepository extends JpaRepository<PlRow, Long> {

}
