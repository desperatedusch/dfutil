package de.dfutil.entities.ids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class ObRowId implements Serializable {

    private String otlAlort;
    private String otlSchl;
    private String otlPlz;
    private String otlStatus;
    private LocalDateTime outdatedAt;
    private LocalDateTime alreadyAppliedAt;

    public ObRowId() {
    }

    public ObRowId(String otlAlort, String otlSchl, String otlPlz, String otlStatus) {
        this.otlAlort = otlAlort;
        this.otlSchl = otlSchl;
        this.otlPlz = otlPlz;
        this.otlStatus = otlStatus;
        this.outdatedAt = null;
        this.alreadyAppliedAt = null;
    }

    public ObRowId(String otlAlort, String otlSchl, String otlPlz, String otlStatus, LocalDateTime outdatedAt, LocalDateTime alreadyAppliedAt) {
        this.otlAlort = otlAlort;
        this.otlSchl = otlSchl;
        this.otlPlz = otlPlz;
        this.otlStatus = otlStatus;
        this.outdatedAt = outdatedAt;
        this.alreadyAppliedAt = alreadyAppliedAt;
    }

    public String getOtlAlort() {
        return otlAlort;
    }

    public void setOtlAlort(String otlAlort) {
        this.otlAlort = otlAlort;
    }

    public String getOtlSchl() {
        return otlSchl;
    }

    public void setOtlSchl(String otlSchl) {
        this.otlSchl = otlSchl;
    }

    public String getOtlPlz() {
        return otlPlz;
    }

    public void setOtlPlz(String otlPlz) {
        this.otlPlz = otlPlz;
    }

    public String getOtlStatus() {
        return otlStatus;
    }

    public void setOtlStatus(String otlStatus) {
        this.otlStatus = otlStatus;
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
        ObRowId obRowId = (ObRowId) o;
        return Objects.equals(otlAlort, obRowId.otlAlort) && Objects.equals(otlSchl, obRowId.otlSchl) && Objects.equals(otlPlz, obRowId.otlPlz) && Objects.equals(otlStatus, obRowId.otlStatus) && Objects.equals(outdatedAt, obRowId.outdatedAt) && Objects.equals(alreadyAppliedAt, obRowId.alreadyAppliedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(otlAlort, otlSchl, otlPlz, otlStatus, outdatedAt, alreadyAppliedAt);
    }
}
