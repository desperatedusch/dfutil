package de.dfutil.entities.jpa.ids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrRowId implements Serializable {

    private String ortAlort;
    private String ortStatus;

    public OrRowId() {
    }

    public OrRowId(String ortAlort, String ortStatus) {
        this.ortAlort = ortAlort;
        this.ortStatus = ortStatus;
    }

    public String ortAlort() {
        return ortAlort;
    }

    public void setOrtAlort(String ortAlort) {
        this.ortAlort = ortAlort;
    }

    public String ortStatus() {
        return ortStatus;
    }

    public void setOrtStatus(String ortStatus) {
        this.ortStatus = ortStatus;
    }
}
