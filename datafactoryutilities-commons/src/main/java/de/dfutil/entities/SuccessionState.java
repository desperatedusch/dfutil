package de.dfutil.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
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

    public SuccessionState(final UUID uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }

    public Set<SuccessionState> getPredecessors() {
        return this.predecessors;
    }

    public void setPredecessors(final Set<SuccessionState> predecessors) {
        this.predecessors = predecessors;
    }

    public Set<SuccessionState> getSuccessors() {
        return this.successors;
    }

    public void setSuccessors(final Set<SuccessionState> successors) {
        this.successors = successors;
    }

    public void addPredecessor(final SuccessionState predecessor) {
        this.predecessors.add(predecessor);
        predecessor.successors.add(this);
    }

    public void removePredecessor(final SuccessionState predecessor) {
        this.predecessors.remove(predecessor);
        predecessor.successors.remove(this);
    }

    public void addSuccessor(final SuccessionState successor) {
        this.successors.add(successor);
        successor.predecessors.add(this);
    }

    public void removeSuccessor(final SuccessionState successor) {
        this.successors.remove(successor);
        successor.predecessors.remove(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (null == o || this.getClass() != o.getClass()) return false;
        final SuccessionState that = (SuccessionState) o;
        return Objects.equals(this.id, that.id) && Objects.equals(this.uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.uuid);
    }

}


