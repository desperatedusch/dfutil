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

    public KgRowId(final String kgSchluessel, final String kgSatzart) {
        this.kgSchluessel = kgSchluessel;
        this.kgSatzart = kgSatzart;
    }

    public String getKgSchluessel() {
        return this.kgSchluessel;
    }

    public void setKgSchluessel(final String kgSchluessel) {
        this.kgSchluessel = kgSchluessel;
    }

    public String getKgSatzart() {
        return this.kgSatzart;
    }

    public void setKgSatzart(final String kgSatzart) {
        this.kgSatzart = kgSatzart;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (null == o || this.getClass() != o.getClass()) return false;
        final KgRowId kgRowId = (KgRowId) o;
        return Objects.equals(this.kgSchluessel, kgRowId.kgSchluessel)
                && Objects.equals(this.kgSatzart, kgRowId.kgSatzart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.kgSchluessel, this.kgSatzart);
    }

}
