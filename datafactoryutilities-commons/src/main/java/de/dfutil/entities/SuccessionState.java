package de.dfutil.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity()
@Table(
        name = "succession_state")
public class SuccessionState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID uuid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "successionstate_predecessor",
            joinColumns = @JoinColumn(name = "successionstate_id"),
            inverseJoinColumns = @JoinColumn(name = "predecessor_id"))
    private Set<SuccessionState> predecessors;

    @ManyToMany(mappedBy = "predecessors", fetch = FetchType.LAZY)
    private Set<SuccessionState> successors;

    public SuccessionState() {
    }

    public SuccessionState(UUID uuid) {
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

    public Set<SuccessionState> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(Set<SuccessionState> predecessors) {
        this.predecessors = predecessors;
    }

    public Set<SuccessionState> getSuccessors() {
        return successors;
    }

    public void setSuccessors(Set<SuccessionState> successors) {
        this.successors = successors;
    }

    public void addPredecessor(SuccessionState predecessor) {
        predecessors.add(predecessor);
        predecessor.getSuccessors().add(this);
    }

    public void removePredecessor(SuccessionState predecessor) {
        predecessors.remove(predecessor);
        predecessor.getSuccessors().remove(this);
    }

    public void addSuccessor(SuccessionState successor) {
        successors.add(successor);
        successor.getPredecessors().add(this);
    }

    public void removeSuccessor(SuccessionState successor) {
        successors.remove(successor);
        successor.getPredecessors().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SuccessionState that = (SuccessionState) o;
        return Objects.equals(id, that.id) && Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid);
    }

}


