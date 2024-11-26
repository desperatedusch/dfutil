package de.dfutil.dao.jpa;

import de.dfutil.entities.jpa.SbRow;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile({"jpa"})
@Repository
public interface SbRowRepository extends JpaRepository<SbRow, Long> {

}
