package de.dfutil.entities.ids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class OrRowId implements Serializable {

    private String ortAlort;
    private String ortStatus;
    private LocalDateTime outdatedAt;
    private LocalDateTime alreadyAppliedAt;

    public OrRowId() {
    }

    public OrRowId(String ortAlort, String ortStatus) {
        this.ortAlort = ortAlort;
        this.ortStatus = ortStatus;
        outdatedAt = null;
        alreadyAppliedAt = null;
    }

    public OrRowId(String ortAlort, String ortStatus, LocalDateTime outdatedAt, LocalDateTime alreadyAppliedAt) {
        this.ortAlort = ortAlort;
        this.ortStatus = ortStatus;
        this.outdatedAt = outdatedAt;
        this.alreadyAppliedAt = alreadyAppliedAt;
    }

    public String getOrtAlort() {
        return ortAlort;
    }

    public void setOrtAlort(String ortAlort) {
        this.ortAlort = ortAlort;
    }

    public String getOrtStatus() {
        return ortStatus;
    }

    public void setOrtStatus(String ortStatus) {
        this.ortStatus = ortStatus;
    }

    public LocalDateTime getOutdatedAt() {
        return outdatedAt;
    }

    public void setOutdatedAt(LocalDateTime outdatedAt) {
        this.outdatedAt = outdatedAt;
    }

    public LocalDateTime getAlreadyAppliedAt() {
        return alreadyAppliedAt;
    }

    public void setAlreadyAppliedAt(LocalDateTime alreadyAppliedAt) {
        this.alreadyAppliedAt = alreadyAppliedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrRowId orRowId = (OrRowId) o;
        return Objects.equals(ortAlort, orRowId.ortAlort) && Objects.equals(ortStatus, orRowId.ortStatus) && Objects.equals(outdatedAt, orRowId.outdatedAt) && Objects.equals(alreadyAppliedAt, orRowId.alreadyAppliedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ortAlort, ortStatus, outdatedAt, alreadyAppliedAt);
    }
}
