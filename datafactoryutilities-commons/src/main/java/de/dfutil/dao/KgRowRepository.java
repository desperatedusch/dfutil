package de.dfutil.dao;

import de.dfutil.entities.KgRow;
import de.dfutil.entities.SbRow;
import de.dfutil.entities.ids.KgRowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KgRowRepository extends JpaRepository<KgRow, KgRowId> {

    @Query("SELECT agsObject FROM KgRow agsObject WHERE 'G' = agsObject.kgRowId.kgSatzart")
    List<KgRow> communities();

    @Query("select agsObject FROM KgRow agsObject WHERE 'K' = agsObject.kgRowId.kgSatzart")
    List<KgRow> ruralDistricts();

    @Query("select agsObject FROM KgRow agsObject WHERE 'R' = agsObject.kgRowId.kgSatzart")
    List<KgRow> administrativeRegions();

    @Query("select agsObject FROM KgRow agsObject WHERE 'L' = agsObject.kgRowId.kgSatzart")
    List<KgRow> federalStates();

    @Query
    SbRow getByUuid(UUID uuid);

}
