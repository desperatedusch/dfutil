package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractRow;
import de.dfutil.entities.ArchivablePostalObject;
import de.dfutil.entities.RowType;
import de.dfutil.entities.jpa.ids.SbRowId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;

import java.nio.charset.StandardCharsets;
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
        String string = new String(rowBytes, StandardCharsets.UTF_8);
        SbRow row = new SbRow();
        row.strDatum = string.substring(9, 17);
        row.strStverz = string.substring(54, 55);
        row.strNameSort = string.substring(55, 101);
        row.strName46 = string.substring(101, 147);
        row.strName22 = string.substring(147, 169);
        row.strReserve = string.substring(169, 170);
        row.strHnrTyp = string.substring(170, 171);
        row.strPlz = string.substring(171, 176);
        row.strCode = string.substring(176, 179);
        row.strOtlSchl = string.substring(179, 182);
        row.strAlorgB = string.substring(182, 190);
        row.strKgs = string.substring(190, 198);
        row.strAlortNeu = string.substring(198, 206);
        row.strNamenSchlNeu = string.substring(206, 212);
        row.strBundLfdnrNeu = string.substring(212, 217);
        row.strHnrvonNeu = string.substring(217, 225);
        row.strHnrbisNeu = string.substring(225, 233);
        row.sbRowId = new SbRowId
                (
                        string.substring(17, 25),
                        string.substring(25, 31),
                        string.substring(31, 36),
                        string.substring(36, 44),
                        string.substring(44, 52),
                        string.substring(52, 53),
                        string.substring(53, 54)
                );
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


