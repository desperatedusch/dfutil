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

    public SbRowId(final String strAlort, final String strNamenSchl, final String strBundLfdnr, final String strHnrvon, final String strHnrbis, final String strStatus, final String strHnr1000) {
        this.strAlort = strAlort;
        this.strNamenSchl = strNamenSchl;
        this.strBundLfdnr = strBundLfdnr;
        this.strHnrvon = strHnrvon;
        this.strHnrbis = strHnrbis;
        this.strStatus = strStatus;
        this.strHnr1000 = strHnr1000;
    }

    public String getStrAlOrt() {
        return this.strAlort;
    }

    public String getStrNamenSchl() {
        return this.strNamenSchl;
    }

    public void setStrNamenSchl(final String strNamenSchl) {
        this.strNamenSchl = strNamenSchl;
    }

    public String getStrBundLfdnr() {
        return this.strBundLfdnr;
    }

    public void setStrBundLfdnr(final String strBundLfdnr) {
        this.strBundLfdnr = strBundLfdnr;
    }

    public String getStrHnrVon() {
        return this.strHnrvon;
    }

    public String getStrHnrBis() {
        return this.strHnrbis;
    }

    public String getStrStatus() {
        return this.strStatus;
    }

    public void setStrStatus(final String strStatus) {
        this.strStatus = strStatus;
    }

    public String getStrHnr1000() {
        return this.strHnr1000;
    }

    public void setStrHnr1000(final String strHnr1000) {
        this.strHnr1000 = strHnr1000;
    }

    public String getStrAlort() {
        return this.strAlort;
    }

    public void setStrAlort(final String strAlOrt) {
        strAlort = strAlOrt;
    }

    public String getStrHnrvon() {
        return this.strHnrvon;
    }

    public void setStrHnrvon(final String strHnrVon) {
        strHnrvon = strHnrVon;
    }

    public String getStrHnrbis() {
        return this.strHnrbis;
    }

    public void setStrHnrbis(final String strHnrBis) {
        strHnrbis = strHnrBis;
    }

    @Override
    public boolean equals(final Object o) {
        if (null == o || this.getClass() != o.getClass()) return false;
        final SbRowId sbRowId = (SbRowId) o;
        return Objects.equals(this.strAlort, sbRowId.strAlort) && Objects.equals(this.strNamenSchl, sbRowId.strNamenSchl) && Objects.equals(this.strBundLfdnr, sbRowId.strBundLfdnr) && Objects.equals(this.strHnrvon, sbRowId.strHnrvon) && Objects.equals(this.strHnrbis, sbRowId.strHnrbis) && Objects.equals(this.strStatus, sbRowId.strStatus) && Objects.equals(this.strHnr1000, sbRowId.strHnr1000);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.strAlort, this.strNamenSchl, this.strBundLfdnr, this.strHnrvon, this.strHnrbis, this.strStatus, this.strHnr1000);
    }
}
