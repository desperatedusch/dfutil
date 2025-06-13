package de.dfutil.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity()
@Table(
        name = "successionstate_link")
public class SuccessionStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID uuid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "successionstate_predecessor",
            joinColumns = @JoinColumn(name = "successionstate_link_id"),
            inverseJoinColumns = @JoinColumn(name = "successionstate_link_id"))
    private Set<SuccessionStateEntity> predecessors;

    @ManyToMany(mappedBy = "predecessors", fetch = FetchType.LAZY)
    private Set<SuccessionStateEntity> successors;

    public SuccessionStateEntity() {
    }

    public SuccessionStateEntity(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Set<SuccessionStateEntity> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(Set<SuccessionStateEntity> predecessors) {
        this.predecessors = predecessors;
    }

    public Set<SuccessionStateEntity> getSuccessors() {
        return successors;
    }

    public void setSuccessors(Set<SuccessionStateEntity> successors) {
        this.successors = successors;
    }

    public void addPredecessor(SuccessionStateEntity predecessor) {
        predecessors.add(predecessor);
        predecessor.getSuccessors().add(this);
    }

    public void removePredecessor(SuccessionStateEntity predecessor) {
        predecessors.remove(predecessor);
        predecessor.getSuccessors().remove(this);
    }

    public void addSuccessor(SuccessionStateEntity successor) {
        successors.add(successor);
        successor.getPredecessors().add(this);
    }

    public void removeSuccessor(SuccessionStateEntity successor) {
        successors.remove(successor);
        successor.getPredecessors().remove(this);
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


