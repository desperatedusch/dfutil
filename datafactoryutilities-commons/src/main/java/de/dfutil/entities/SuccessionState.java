package de.dfutil.entities;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;
import java.util.UUID;

@Node
public class SuccessionState {

    @Relationship(type = "Predecessor")
    public SuccessionState predecessor;
    @Relationship(type = "Successor")
    public Set<SuccessionState> successors;
    @Id
    @GeneratedValue
    private Long id;
    private UUID uuid;

}


