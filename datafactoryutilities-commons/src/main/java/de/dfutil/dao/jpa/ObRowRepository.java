package de.dfutil.dao.jpa;

import de.dfutil.entities.jpa.ObRow;
import de.dfutil.entities.jpa.ids.ObRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObRowRepository extends JpaRepository<ObRow, ObRowId> {

}
