package de.dfutil.entities.jpa.ids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class KgRowId implements Serializable {

    private String kgSchluessel;
    private String kgSatzart;

    public KgRowId() {

    }

    public KgRowId(String kgSchluessel, String kgSatzart) {
        this.kgSchluessel = kgSchluessel;
        this.kgSatzart = kgSatzart;
    }

    public String kgSchluessel() {
        return kgSchluessel;
    }

    public void setKgSchluessel(String kgSchluessel) {
        this.kgSchluessel = kgSchluessel;
    }

    public String kgSatzart() {
        return kgSatzart;
    }

    public void setKgSatzart(String kgSatzart) {
        this.kgSatzart = kgSatzart;
    }
}
