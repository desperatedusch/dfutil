package de.dfutil.entities.jpa.ids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ObRowId implements Serializable {

    private String otlAlort;
    private String otlSchl;
    private String otlPlz;
    private String otlStatus;

    public ObRowId() {
    }

    public ObRowId(String otlAlort, String otlSchl, String otlPlz, String otlStatus) {
        this.otlAlort = otlAlort;
        this.otlSchl = otlSchl;
        this.otlPlz = otlPlz;
        this.otlStatus = otlStatus;
    }

    public String otlAlort() {
        return otlAlort;
    }

    public void setOtlAlort(String otlAlort) {
        this.otlAlort = otlAlort;
    }

    public String otlSchl() {
        return otlSchl;
    }

    public void setOtlSchl(String otlSchl) {
        this.otlSchl = otlSchl;
    }

    public String otlPlz() {
        return otlPlz;
    }

    public void setOtlPlz(String otlPlz) {
        this.otlPlz = otlPlz;
    }

    public String otlStatus() {
        return otlStatus;
    }

    public void setOtlStatus(String otlStatus) {
        this.otlStatus = otlStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObRowId obRowId = (ObRowId) o;
        return Objects.equals(otlAlort, obRowId.otlAlort)
                && Objects.equals(otlSchl, obRowId.otlSchl)
                && Objects.equals(otlPlz, obRowId.otlPlz)
                && Objects.equals(otlStatus, obRowId.otlStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(otlAlort, otlSchl, otlPlz, otlStatus);
    }

}
