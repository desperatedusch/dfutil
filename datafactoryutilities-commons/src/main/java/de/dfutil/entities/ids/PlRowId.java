package de.dfutil.entities.ids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlRowId implements Serializable {

    private String plzPlz;
    private String plzAlort;

    public PlRowId() {
    }

    public PlRowId(final String plzPlz, final String plzAlort) {
        this.plzPlz = plzPlz;
        this.plzAlort = plzAlort;
    }

    public String getPlzPlz() {
        return this.plzPlz;
    }

    public void setPlzPlz(final String plzPlz) {
        this.plzPlz = plzPlz;
    }

    public String getPlzAlOrt() {
        return this.plzAlort;
    }

    public void setPlzAlort(final String plzAlOrt) {
        plzAlort = plzAlOrt;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (null == o || this.getClass() != o.getClass()) return false;
        final PlRowId plRowId = (PlRowId) o;
        return Objects.equals(this.plzPlz, plRowId.plzPlz) && Objects.equals(this.plzAlort, plRowId.plzAlort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.plzPlz, this.plzAlort);
    }

}
