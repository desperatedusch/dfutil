package de.dfutil.dao.jpa;

import de.dfutil.entities.jpa.OrRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrRowRepository extends JpaRepository<OrRow, Long> {

}
