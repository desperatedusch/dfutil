package de.dfutil.entities.jpa.ids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PlRowId implements Serializable {

    private String plzPlz;
    private String plzAlOrt;

    public PlRowId() {
    }

    public PlRowId(String plzPlz, String plzAlOrt) {
        this.plzPlz = plzPlz;
        this.plzAlOrt = plzAlOrt;
    }

    public String plzPlz() {
        return plzPlz;
    }

    public void setPlzPlz(String plzPlz) {
        this.plzPlz = plzPlz;
    }

    public String plzAlOrt() {
        return plzAlOrt;
    }

    public void setPlzAlOrt(String plzAlOrt) {
        this.plzAlOrt = plzAlOrt;
    }

}
