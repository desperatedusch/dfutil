package de.dfutil.entities.jpa.ids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

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

}
