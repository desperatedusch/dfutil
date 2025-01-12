package de.dfutil.entities.jpa.ids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class SbRowId implements Serializable {

    private String strAlOrt;
    private String strNamenSchl;
    private String strBundLfdnr;
    private String strHnrVon;
    private String strHnrBis;
    private String strStatus;
    private String strHnr1000;

    public SbRowId() {
    }

    public SbRowId(String strAlOrt, String strNamenSchl, String strBundLfdnr, String strHnrVon, String strHnrBis, String strStatus, String strHnr1000) {
        this.strAlOrt = strAlOrt;
        this.strNamenSchl = strNamenSchl;
        this.strBundLfdnr = strBundLfdnr;
        this.strHnrVon = strHnrVon;
        this.strHnrBis = strHnrBis;
        this.strStatus = strStatus;
        this.strHnr1000 = strHnr1000;
    }

    public String strAlOrt() {
        return strAlOrt;
    }

    public void setStrAlOrt(String strAlOrt) {
        this.strAlOrt = strAlOrt;
    }

    public String strNamenSchl() {
        return strNamenSchl;
    }

    public void setStrNamenSchl(String strNamenSchl) {
        this.strNamenSchl = strNamenSchl;
    }

    public String strBundLfdnr() {
        return strBundLfdnr;
    }

    public void setStrBundLfdnr(String strBundLfdnr) {
        this.strBundLfdnr = strBundLfdnr;
    }

    public String strHnrVon() {
        return strHnrVon;
    }

    public void setStrHnrVon(String strHnrVon) {
        this.strHnrVon = strHnrVon;
    }

    public String strHnrBis() {
        return strHnrBis;
    }

    public void setStrHnrBis(String strHnrBis) {
        this.strHnrBis = strHnrBis;
    }

    public String strStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    public String strHnr1000() {
        return strHnr1000;
    }

    public void setStrHnr1000(String strHnr1000) {
        this.strHnr1000 = strHnr1000;
    }

}
