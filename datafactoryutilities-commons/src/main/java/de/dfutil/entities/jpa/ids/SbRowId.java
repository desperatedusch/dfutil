package de.dfutil.entities.jpa.ids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class SbRowId implements Serializable {

    private String strAlOrt;
    private String strSchluessel;
    private String strNamenSchl;
    private String strBundLfdnr;
    private String strHnrVon;
    private String strHnrBis;
    private String strStatus;
    private String strHnr1000;

    public SbRowId() {
    }

    public SbRowId(String strAlOrt, String strSchluessel, String strNamenSchl, String strBundLfdnr, String strHnrVon, String strHnrBis, String strStatus, String strHnr1000) {
        this.strAlOrt = strAlOrt;
        this.strSchluessel = strSchluessel;
        this.strNamenSchl = strNamenSchl;
        this.strBundLfdnr = strBundLfdnr;
        this.strHnrVon = strHnrVon;
        this.strHnrBis = strHnrBis;
        this.strStatus = strStatus;
        this.strHnr1000 = strHnr1000;
    }
}
