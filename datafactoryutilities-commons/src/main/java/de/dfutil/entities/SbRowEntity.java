package de.dfutil.entities;

import de.dfutil.entities.ids.SbRowId;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * Repräsentiert Strassen
 */
@Entity
@Table(
        name = "sb_row",
        indexes =
                {
                        @Index(
                                name = "IDX_STRASSE___STR_ALORT__STR_NAMEN_SCHL__STR_BUND_LFDNR__STR_HNRVON__STR_HNRBIS__STR_STATUS__STR_HNR1000",
                                columnList = "STR_ALORT,STR_NAMEN_SCHL,STR_BUND_LFDNR,STR_HNRVON,STR_HNRBIS,STR_STATUS,STR_HNR1000"),
                        @Index(
                                name = "IDX_STRASSE___UUID",
                                columnList = "UUID")
                }
)
public class SbRowEntity extends AbstractRowEntity<SbRowEntity> implements ArchivablePostalObject {

    private static final RowType rowType = RowType.SB;

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
    private LocalDateTime outdatedAt;
    private LocalDateTime alreadyAppliedAt;

    @OneToOne
    @JoinColumn(name = "succession_state_id")
    private SuccessionState successionState;

    public static SbRowEntity parseFrom(String rowBytes) {
        SbRowEntity row = new SbRowEntity();
        row.importingFileIdentifier = rowBytes.substring(0, 9);
        row.strDatum = rowBytes.substring(9, 17);
        row.strStverz = rowBytes.substring(54, 55);
        row.strNameSort = rowBytes.substring(55, 101);
        row.strName46 = rowBytes.substring(101, 147);
        row.strName22 = rowBytes.substring(147, 169);
        row.strReserve = rowBytes.substring(169, 170);
        row.strHnrTyp = rowBytes.substring(170, 171);
        row.strPlz = rowBytes.substring(171, 176);
        row.strCode = rowBytes.substring(176, 179);
        row.strOtlSchl = rowBytes.substring(179, 182);
        row.strAlorgB = rowBytes.substring(182, 190);
        row.strKgs = rowBytes.substring(190, 198);
        row.strAlortNeu = rowBytes.substring(198, 206);
        row.strNamenSchlNeu = rowBytes.substring(206, 212);
        row.strBundLfdnrNeu = rowBytes.substring(212, 217);
        row.strHnrvonNeu = rowBytes.substring(217, 225);
        row.strHnrbisNeu = rowBytes.substring(225, 233);
        row.sbRowId =
                new SbRowId(
                        rowBytes.substring(17, 25),
                        rowBytes.substring(25, 31),
                        rowBytes.substring(31, 36),
                        rowBytes.substring(36, 44),
                        rowBytes.substring(44, 52),
                        rowBytes.substring(52, 53),
                        rowBytes.substring(53, 54));
        return row;
    }

    public SuccessionState getSuccessionState() {
        return successionState;
    }

    public void setSuccessionState(SuccessionState successionState) {
        this.successionState = successionState;
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

    public String strNameSort() {
        return strNameSort;
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

    public void setStrStverz(String strStverz) {
        this.strStverz = strStverz;
    }

    public String getStrNameSort() {
        return strNameSort;
    }

    public void setStrNameSort(String strNameSort) {
        this.strNameSort = strNameSort;
    }

    public LocalDateTime getOutdatedAt() {
        return outdatedAt;
    }

    public void setOutdatedAt(LocalDateTime outdatedSince) {
        this.outdatedAt = outdatedSince;
    }

    public LocalDateTime alreadyAppliedAt() {
        return alreadyAppliedAt;
    }

    public void setAlreadyAppliedAt(LocalDateTime alreadyAppliedAt) {
        this.alreadyAppliedAt = alreadyAppliedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o || getClass() != o.getClass()) return false;
        SbRowEntity sbRow = (SbRowEntity) o;
        return Objects.equals(version, sbRow.version) && Objects.equals(strDatum, sbRow.strDatum) && Objects.equals(sbRowId, sbRow.sbRowId) && Objects.equals(strStverz, sbRow.strStverz) && Objects.equals(strNameSort, sbRow.strNameSort) && Objects.equals(strName46, sbRow.strName46) && Objects.equals(strName22, sbRow.strName22) && Objects.equals(strReserve, sbRow.strReserve) && Objects.equals(strHnrTyp, sbRow.strHnrTyp) && Objects.equals(strPlz, sbRow.strPlz) && Objects.equals(strCode, sbRow.strCode) && Objects.equals(strOtlSchl, sbRow.strOtlSchl) && Objects.equals(strAlorgB, sbRow.strAlorgB) && Objects.equals(strKgs, sbRow.strKgs) && Objects.equals(strAlortNeu, sbRow.strAlortNeu) && Objects.equals(strNamenSchlNeu, sbRow.strNamenSchlNeu) && Objects.equals(strBundLfdnrNeu, sbRow.strBundLfdnrNeu) && Objects.equals(strHnrvonNeu, sbRow.strHnrvonNeu) && Objects.equals(strHnrbisNeu, sbRow.strHnrbisNeu) && Objects.equals(outdatedAt, sbRow.outdatedAt) && Objects.equals(alreadyAppliedAt, sbRow.alreadyAppliedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, strDatum, sbRowId, strStverz, strNameSort, strName46, strName22, strReserve, strHnrTyp, strPlz, strCode, strOtlSchl, strAlorgB, strKgs, strAlortNeu, strNamenSchlNeu, strBundLfdnrNeu, strHnrvonNeu, strHnrbisNeu, outdatedAt, alreadyAppliedAt);
    }
}
