package de.dfutil.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(
        name = "succession_state")
public class SuccessionStateLinkElement {

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
    private Set<SuccessionStateLinkElement> predecessors;

    @ManyToMany(mappedBy = "predecessors", fetch = FetchType.LAZY)
    private Set<SuccessionStateLinkElement> successors;

    public SuccessionStateLinkElement() {
    }

    public SuccessionStateLinkElement(UUID uuid) {
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

    public Set<SuccessionStateLinkElement> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(Set<SuccessionStateLinkElement> predecessors) {
        this.predecessors = predecessors;
    }

    public Set<SuccessionStateLinkElement> getSuccessors() {
        return successors;
    }

    public void setSuccessors(Set<SuccessionStateLinkElement> successors) {
        this.successors = successors;
    }

    public void addPredecessor(SuccessionStateLinkElement predecessor) {
        predecessors.add(predecessor);
        predecessor.successors.add(this);
    }

    public void removePredecessor(SuccessionStateLinkElement predecessor) {
        predecessors.remove(predecessor);
        predecessor.successors.remove(this);
    }

    public void addSuccessor(SuccessionStateLinkElement successor) {
        successors.add(successor);
        successor.predecessors.add(this);
    }

    public void removeSuccessor(SuccessionStateLinkElement successor) {
        successors.remove(successor);
        successor.predecessors.remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o || getClass() != o.getClass()) return false;
        SuccessionStateLinkElement that = (SuccessionStateLinkElement) o;
        return Objects.equals(id, that.id) && Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid);
    }

}


