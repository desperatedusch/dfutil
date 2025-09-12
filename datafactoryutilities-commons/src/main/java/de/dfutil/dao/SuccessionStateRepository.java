package de.dfutil.dao;

import de.dfutil.entities.SuccessionStateLinkElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessionStateRepository extends JpaRepository<SuccessionStateLinkElement, Long> {
}
