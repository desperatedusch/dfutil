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

    public PlRowId(String plzPlz, String plzAlort) {
        this.plzPlz = plzPlz;
        this.plzAlort = plzAlort;
    }

    public String plzPlz() {
        return plzPlz;
    }

    public void setPlzPlz(String plzPlz) {
        this.plzPlz = plzPlz;
    }

    public String plzAlOrt() {
        return plzAlort;
    }

    public void setPlzAlort(String plzAlOrt) {
        this.plzAlort = plzAlOrt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlRowId plRowId = (PlRowId) o;
        return Objects.equals(plzPlz, plRowId.plzPlz) && Objects.equals(plzAlort, plRowId.plzAlort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plzPlz, plzAlort);
    }

}
