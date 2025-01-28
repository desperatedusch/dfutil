package de.dfutil.importing;

import de.dfutil.entities.ArchivablePostalObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SuccessionHandling {

    public void setSuccessorsTo(JpaRepository dao, ArchivablePostalObject postalObject) {
    }

    public void removeObjectWithoutSuccession(JpaRepository dao, ArchivablePostalObject postalObject) {
    }

}
