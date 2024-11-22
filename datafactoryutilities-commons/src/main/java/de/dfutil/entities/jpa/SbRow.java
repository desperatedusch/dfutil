package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractDataFactoryRow;
import de.dfutil.entities.SerializablePostalObject;
import de.dfutil.entities.format.RowType;
import de.dfutil.entities.format.SBRowFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Version;

import java.util.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
public class SbRow implements AbstractDataFactoryRow<SbRow, SBRowFormat>, SerializablePostalObject {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = SEQUENCE, generator = "ID_SEQ")
    @Column(name = "id")
    private Long id;
    @Version
    @Column(name = "version")
    private Date version;

    private final static RowType rowType = RowType.SB;
    private String strDatum;
    private String strAlOrt;
    private String strSchluessel;
    private String strNamenSchl;
    private String strBundLfdnr;
    private String strHnrVon;
    private String strHnrBis;
    private String strStatus;
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
    private String strBundLfdnrNNeu;
    private String strHnrvonNeu;
    private String strHnrbisNeu;

    @Override
    public SbRow parseFrom(byte[] rowBytes) {
        for (SBRowFormat token : SBRowFormat.values()) {
            try {
                if (token.parseableContent())
                    applyRowFormatTokenOnRowBytes(token, rowBytes, this);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException("Parsing failed....", e);
            }
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStrAlOrt() {
        return strAlOrt;
    }

    public void setStrAlOrt(String strAlOrt) {
        this.strAlOrt = strAlOrt;
    }

    public String getStrSchluessel() {
        return strSchluessel;
    }

    public void setStrSchluessel(String strSchluessel) {
        this.strSchluessel = strSchluessel;
    }

    public String getStrNamenSchl() {
        return strNamenSchl;
    }

    public void setStrNamenSchl(String strassennamenschluessel) {
        this.strNamenSchl = strassennamenschluessel;
    }

    public String getStrBundLfdnr() {
        return strBundLfdnr;
    }

    public void setStrBundLfdnr(String strBundLfdnr) {
        this.strBundLfdnr = strBundLfdnr;
    }

    public String getStrHnrVon() {
        return strHnrVon;
    }

    public void setStrHnrVon(String strHnrVon) {
        this.strHnrVon = strHnrVon;
    }

    public String getStrHnrBis() {
        return strHnrBis;
    }

    public void setStrHnrBis(String strHnrBis) {
        this.strHnrBis = strHnrBis;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
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

    public String getStrBundLfdnrNNeu() {
        return strBundLfdnrNNeu;
    }

    public void setStrBundLfdnrNNeu(String strBundLfdnrNNeu) {
        this.strBundLfdnrNNeu = strBundLfdnrNNeu;
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


}


