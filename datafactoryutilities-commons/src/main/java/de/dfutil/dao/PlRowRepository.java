package de.dfutil.dao;

import de.dfutil.entities.PlRow;
import de.dfutil.entities.ids.PlRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlRowRepository extends JpaRepository<PlRow, PlRowId> {

}
