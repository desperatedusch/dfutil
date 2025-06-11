package de.dfutil.dao;

import de.dfutil.entities.SuccessionState;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SuccessionStateRepository extends Neo4jRepository<SuccessionState, Long> {

}
