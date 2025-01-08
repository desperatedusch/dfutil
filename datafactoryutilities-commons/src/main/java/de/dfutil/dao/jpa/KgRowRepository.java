package de.dfutil.dao.jpa;

import de.dfutil.entities.jpa.KgRow;
import de.dfutil.entities.jpa.ids.KgRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KgRowRepository extends JpaRepository<KgRow, KgRowId> {

}
