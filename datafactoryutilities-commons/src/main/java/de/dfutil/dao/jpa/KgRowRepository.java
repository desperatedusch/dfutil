package de.dfutil.dao.jpa;

import de.dfutil.entities.jpa.KgRow;
import de.dfutil.entities.jpa.ids.KgRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KgRowRepository extends JpaRepository<KgRow, KgRowId> {

    @Query("Select agsObject from KgRow agsObject where 'G' = agsObject.kgRowId.kgSatzart")
    List<KgRow> communities();

    @Query("Select agsObject from KgRow agsObject where 'K' = agsObject.kgRowId.kgSatzart")
    List<KgRow> ruralDistricts();

    @Query("Select agsObject from KgRow agsObject where 'R' = agsObject.kgRowId.kgSatzart")
    List<KgRow> administrativeRegions();

    @Query("Select agsObject from KgRow agsObject where 'L' = agsObject.kgRowId.kgSatzart")
    List<KgRow> federalStates();


}
