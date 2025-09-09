package de.dfutil.dao;

import de.dfutil.entities.KgRowEntity;
import de.dfutil.entities.ids.KgRowId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KgRowRepository extends JpaRepository<KgRowEntity, KgRowId> {

    @Query("SELECT agsObject FROM KgRowEntity agsObject WHERE 'G' = agsObject.kgRowId.kgSatzart")
    List<KgRowEntity> communities();

    @Query("select agsObject FROM KgRowEntity agsObject WHERE 'K' = agsObject.kgRowId.kgSatzart")
    List<KgRowEntity> ruralDistricts();

    @Query("select agsObject FROM KgRowEntity agsObject WHERE 'R' = agsObject.kgRowId.kgSatzart")
    List<KgRowEntity> administrativeRegions();

    @Query("select agsObject FROM KgRowEntity agsObject WHERE 'L' = agsObject.kgRowId.kgSatzart")
    List<KgRowEntity> federalStates();

    @Query
    KgRowEntity getByUuid(UUID uuid);

    @Override
    @Cacheable(cacheNames = {"kgCache"})
    boolean existsById(KgRowId kgRowId);
}
