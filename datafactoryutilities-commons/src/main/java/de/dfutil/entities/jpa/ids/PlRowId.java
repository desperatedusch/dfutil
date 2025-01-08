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

}
