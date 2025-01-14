package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractRow;
import de.dfutil.entities.ArchivablePostalObject;
import de.dfutil.entities.RowType;
import de.dfutil.entities.jpa.ids.SbRowId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;

import java.util.Arrays;
import java.util.Date;

/**
 * Repräsentiert Strassen
 */
@Entity
public class SbRow implements AbstractRow<SbRow>, ArchivablePostalObject {

    private final static RowType rowType = RowType.SB;

    @Version
    @Column(name = "version")
    private Date version;
    private String strDatum;
    @EmbeddedId
    private SbRowId sbRowId;
    private String strStverz;
    private String strNameSort;
    private String strName46;
    private String strName22;
    private String strReserve;
    private String strHnrTyp;
    private String strPlz;
    private String strCode;
    private String strOtlSchl;
    private String strAlorgB;
    private String strKgs;
    private String strAlortNeu;
    private String strNamenSchlNeu;
    private String strBundLfdnrNeu;
    private String strHnrvonNeu;
    private String strHnrbisNeu;

    public static SbRow parseFrom(byte[] rowBytes) {
        SbRow row = new SbRow();
        row.strDatum = Arrays.toString(rowBytes).substring(9, 17);
        row.strStverz = Arrays.toString(rowBytes).substring(54, 55);
        row.strNameSort = Arrays.toString(rowBytes).substring(55, 101);
        row.strName46 = Arrays.toString(rowBytes).substring(101, 147);
        row.strName22 = Arrays.toString(rowBytes).substring(147, 169);
        row.strReserve = Arrays.toString(rowBytes).substring(169, 170);
        row.strHnrTyp = Arrays.toString(rowBytes).substring(170, 171);
        row.strPlz = Arrays.toString(rowBytes).substring(171, 176);
        row.strCode = Arrays.toString(rowBytes).substring(176, 179);
        row.strOtlSchl = Arrays.toString(rowBytes).substring(179, 182);
        row.strAlorgB = Arrays.toString(rowBytes).substring(182, 190);
        row.strKgs = Arrays.toString(rowBytes).substring(190, 198);
        row.strAlortNeu = Arrays.toString(rowBytes).substring(198, 206);
        row.strNamenSchlNeu = Arrays.toString(rowBytes).substring(206, 212);
        row.strBundLfdnrNeu = Arrays.toString(rowBytes).substring(212, 217);
        row.strHnrvonNeu = Arrays.toString(rowBytes).substring(217, 225);
        row.strHnrbisNeu = Arrays.toString(rowBytes).substring(225, 233);
        row.sbRowId = new SbRowId(Arrays.toString(rowBytes).substring(17, 25), Arrays.toString(rowBytes).substring(25, 31), Arrays.toString(rowBytes).substring(31, 36), Arrays.toString(rowBytes).substring(36, 44), Arrays.toString(rowBytes).substring(44, 52), Arrays.toString(rowBytes).substring(52, 53), Arrays.toString(rowBytes).substring(53, 54));
        return row;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public RowType getRowType() {
        return rowType;
    }

    public String getStrDatum() {
        return strDatum;
    }

    public void setStrDatum(String strDatum) {
        this.strDatum = strDatum;
    }


    public String strStverz() {
        return strStverz;
    }

    public void setStrStverz(String strStverz) {
        this.strStverz = strStverz;
    }

    public String strNameSort() {
        return strNameSort;
    }

    public void setStrNameSort(String strNameSort) {
        this.strNameSort = strNameSort;
    }

    public String getStrName46() {
        return strName46;
    }

    public void setStrName46(String strName46) {
        this.strName46 = strName46;
    }

    public String getStrName22() {
        return strName22;
    }

    public void setStrName22(String strName22) {
        this.strName22 = strName22;
    }

    public String getStrReserve() {
        return strReserve;
    }

    public void setStrReserve(String strReserve) {
        this.strReserve = strReserve;
    }

    public String getStrHnrTyp() {
        return strHnrTyp;
    }

    public void setStrHnrTyp(String strHnrTyp) {
        this.strHnrTyp = strHnrTyp;
    }

    public String getStrPlz() {
        return strPlz;
    }

    public void setStrPlz(String strPlz) {
        this.strPlz = strPlz;
    }

    public String getStrCode() {
        return strCode;
    }

    public void setStrCode(String strCode) {
        this.strCode = strCode;
    }

    public String getStrOtlSchl() {
        return strOtlSchl;
    }

    public void setStrOtlSchl(String strOtlSchl) {
        this.strOtlSchl = strOtlSchl;
    }

    public String getStrAlorgB() {
        return strAlorgB;
    }

    public void setStrAlorgB(String strAlorgB) {
        this.strAlorgB = strAlorgB;
    }

    public String getStrKgs() {
        return strKgs;
    }

    public void setStrKgs(String strKgs) {
        this.strKgs = strKgs;
    }

    public String getStrAlortNeu() {
        return strAlortNeu;
    }

    public void setStrAlortNeu(String strAlortNeu) {
        this.strAlortNeu = strAlortNeu;
    }

    public String getStrNamenSchlNeu() {
        return strNamenSchlNeu;
    }

    public void setStrNamenSchlNeu(String strNamenSchlNeu) {
        this.strNamenSchlNeu = strNamenSchlNeu;
    }

    public String getStrBundLfdnrNeu() {
        return strBundLfdnrNeu;
    }

    public void setStrBundLfdnrNeu(String strBundLfdnrNNeu) {
        this.strBundLfdnrNeu = strBundLfdnrNNeu;
    }

    public String getStrHnrvonNeu() {
        return strHnrvonNeu;
    }

    public void setStrHnrvonNeu(String strHnrvonNeu) {
        this.strHnrvonNeu = strHnrvonNeu;
    }

    public String getStrHnrbisNeu() {
        return strHnrbisNeu;
    }

    public void setStrHnrbisNeu(String strHnrbisNeu) {
        this.strHnrbisNeu = strHnrbisNeu;
    }

    public SbRowId getSbRowId() {
        return sbRowId;
    }

    public void setSbRowId(SbRowId sbRowId) {
        this.sbRowId = sbRowId;
    }

    public String getStrStverz() {
        return strStverz;
    }

    public String getStrNameSort() {
        return strNameSort;
    }
}


