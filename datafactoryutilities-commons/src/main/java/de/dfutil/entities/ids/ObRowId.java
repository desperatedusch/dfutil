package de.dfutil.entities.ids;

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

    public ObRowId(final String otlAlort, final String otlSchl, final String otlPlz, final String otlStatus) {
        this.otlAlort = otlAlort;
        this.otlSchl = otlSchl;
        this.otlPlz = otlPlz;
        this.otlStatus = otlStatus;
    }

    public String getOtlAlort() {
        return this.otlAlort;
    }

    public void setOtlAlort(final String otlAlort) {
        this.otlAlort = otlAlort;
    }

    public String getOtlSchl() {
        return this.otlSchl;
    }

    public void setOtlSchl(final String otlSchl) {
        this.otlSchl = otlSchl;
    }

    public String getOtlPlz() {
        return this.otlPlz;
    }

    public void setOtlPlz(final String otlPlz) {
        this.otlPlz = otlPlz;
    }

    public String getOtlStatus() {
        return this.otlStatus;
    }

    public void setOtlStatus(final String otlStatus) {
        this.otlStatus = otlStatus;
    }

    @Override
    public boolean equals(final Object o) {
        if (null == o || this.getClass() != o.getClass()) return false;
        final ObRowId obRowId = (ObRowId) o;
        return Objects.equals(this.otlAlort, obRowId.otlAlort) && Objects.equals(this.otlSchl, obRowId.otlSchl) && Objects.equals(this.otlPlz, obRowId.otlPlz) && Objects.equals(this.otlStatus, obRowId.otlStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.otlAlort, this.otlSchl, this.otlPlz, this.otlStatus);
    }
}
