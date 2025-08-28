package de.dfutil.entities.ids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrRowId implements Serializable {

    private String ortAlort;
    private String ortStatus;

    public OrRowId() {
    }

    public OrRowId(final String ortAlort, final String ortStatus) {
        this.ortAlort = ortAlort;
        this.ortStatus = ortStatus;
    }

    public String getOrtAlort() {
        return this.ortAlort;
    }

    public void setOrtAlort(final String ortAlort) {
        this.ortAlort = ortAlort;
    }

    public String getOrtStatus() {
        return this.ortStatus;
    }

    public void setOrtStatus(final String ortStatus) {
        this.ortStatus = ortStatus;
    }

    @Override
    public boolean equals(final Object o) {
        if (null == o || this.getClass() != o.getClass()) return false;
        final OrRowId orRowId = (OrRowId) o;
        return Objects.equals(this.ortAlort, orRowId.ortAlort) && Objects.equals(this.ortStatus, orRowId.ortStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ortAlort, this.ortStatus);
    }
}
