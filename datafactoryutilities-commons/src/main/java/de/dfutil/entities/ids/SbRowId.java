package de.dfutil.entities.ids;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SbRowId implements Serializable {

    private String strAlort;
    private String strNamenSchl;
    private String strBundLfdnr;
    private String strHnrvon;
    private String strHnrbis;
    private String strStatus;
    private String strHnr1000;

    public SbRowId() {
    }

    public SbRowId(
            String strAlort,
            String strNamenSchl,
            String strBundLfdnr,
            String strHnrvon,
            String strHnrbis,
            String strStatus,
            String strHnr1000) {
        this.strAlort = strAlort;
        this.strNamenSchl = strNamenSchl;
        this.strBundLfdnr = strBundLfdnr;
        this.strHnrvon = strHnrvon;
        this.strHnrbis = strHnrbis;
        this.strStatus = strStatus;
        this.strHnr1000 = strHnr1000;
    }

    public String getStrAlOrt() {
        return strAlort;
    }

    public void setStrAlort(String strAlOrt) {
        this.strAlort = strAlOrt;
    }

    public String getStrNamenSchl() {
        return strNamenSchl;
    }

    public void setStrNamenSchl(String strNamenSchl) {
        this.strNamenSchl = strNamenSchl;
    }

    public String getStrBundLfdnr() {
        return strBundLfdnr;
    }

    public void setStrBundLfdnr(String strBundLfdnr) {
        this.strBundLfdnr = strBundLfdnr;
    }

    public String getStrHnrVon() {
        return strHnrvon;
    }

    public void setStrHnrvon(String strHnrVon) {
        this.strHnrvon = strHnrVon;
    }

    public String getStrHnrBis() {
        return strHnrbis;
    }

    public void setStrHnrbis(String strHnrBis) {
        this.strHnrbis = strHnrBis;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    public String getStrHnr1000() {
        return strHnr1000;
    }

    public void setStrHnr1000(String strHnr1000) {
        this.strHnr1000 = strHnr1000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SbRowId sbRowId = (SbRowId) o;
        return Objects.equals(strAlort, sbRowId.strAlort)
                && Objects.equals(strNamenSchl, sbRowId.strNamenSchl)
                && Objects.equals(strBundLfdnr, sbRowId.strBundLfdnr)
                && Objects.equals(strHnrvon, sbRowId.strHnrvon)
                && Objects.equals(strHnrbis, sbRowId.strHnrbis)
                && Objects.equals(strStatus, sbRowId.strStatus)
                && Objects.equals(strHnr1000, sbRowId.strHnr1000);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                strAlort, strNamenSchl, strBundLfdnr, strHnrvon, strHnrbis, strStatus, strHnr1000);
    }

}
