package de.dfutil.entities.ids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KgRowId kgRowId = (KgRowId) o;
        return Objects.equals(kgSchluessel, kgRowId.kgSchluessel)
                && Objects.equals(kgSatzart, kgRowId.kgSatzart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kgSchluessel, kgSatzart);
    }

}
