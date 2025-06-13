package de.dfutil.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(
        name = "SUCCESSIONSTATE")
public class SuccessionStateEntity {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private UUID uuid;

    @ManyToMany(mappedBy = "predecessors", fetch = FetchType.LAZY)
    private Set<SuccessionStateEntity> predecessors;

    @ManyToMany(mappedBy = "successors", fetch = FetchType.LAZY)
    private Set<SuccessionStateEntity> successors;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<SuccessionStateEntity> getSuccessors() {
        return successors;
    }

    public void setSuccessors(Set<SuccessionStateEntity> successors) {
        this.successors = successors;
    }

    public Set<SuccessionStateEntity> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(Set<SuccessionStateEntity> predecessors) {
        this.predecessors = predecessors;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SuccessionStateEntity that = (SuccessionStateEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid);
    }

}


