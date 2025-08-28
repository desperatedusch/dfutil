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

    public static SbRowEntity parseFrom(final String rowBytes) {
        final SbRowEntity row = new SbRowEntity();
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
        return this.successionState;
    }

    public void setSuccessionState(final SuccessionState successionState) {
        this.successionState = successionState;
    }

    public Date getVersion() {
        return this.version;
    }

    public void setVersion(final Date version) {
        this.version = version;
    }

    public RowType getRowType() {
        return SbRowEntity.rowType;
    }

    public String getStrDatum() {
        return this.strDatum;
    }

    public void setStrDatum(final String strDatum) {
        this.strDatum = strDatum;
    }

    public String strStverz() {
        return this.strStverz;
    }

    public String strNameSort() {
        return this.strNameSort;
    }

    public String getStrName46() {
        return this.strName46;
    }

    public void setStrName46(final String strName46) {
        this.strName46 = strName46;
    }

    public String getStrName22() {
        return this.strName22;
    }

    public void setStrName22(final String strName22) {
        this.strName22 = strName22;
    }

    public String getStrReserve() {
        return this.strReserve;
    }

    public void setStrReserve(final String strReserve) {
        this.strReserve = strReserve;
    }

    public String getStrHnrTyp() {
        return this.strHnrTyp;
    }

    public void setStrHnrTyp(final String strHnrTyp) {
        this.strHnrTyp = strHnrTyp;
    }

    public String getStrPlz() {
        return this.strPlz;
    }

    public void setStrPlz(final String strPlz) {
        this.strPlz = strPlz;
    }

    public String getStrCode() {
        return this.strCode;
    }

    public void setStrCode(final String strCode) {
        this.strCode = strCode;
    }

    public String getStrOtlSchl() {
        return this.strOtlSchl;
    }

    public void setStrOtlSchl(final String strOtlSchl) {
        this.strOtlSchl = strOtlSchl;
    }

    public String getStrAlorgB() {
        return this.strAlorgB;
    }

    public void setStrAlorgB(final String strAlorgB) {
        this.strAlorgB = strAlorgB;
    }

    public String getStrKgs() {
        return this.strKgs;
    }

    public void setStrKgs(final String strKgs) {
        this.strKgs = strKgs;
    }

    public String getStrAlortNeu() {
        return this.strAlortNeu;
    }

    public void setStrAlortNeu(final String strAlortNeu) {
        this.strAlortNeu = strAlortNeu;
    }

    public String getStrNamenSchlNeu() {
        return this.strNamenSchlNeu;
    }

    public void setStrNamenSchlNeu(final String strNamenSchlNeu) {
        this.strNamenSchlNeu = strNamenSchlNeu;
    }

    public String getStrBundLfdnrNeu() {
        return this.strBundLfdnrNeu;
    }

    public void setStrBundLfdnrNeu(final String strBundLfdnrNNeu) {
        strBundLfdnrNeu = strBundLfdnrNNeu;
    }

    public String getStrHnrvonNeu() {
        return this.strHnrvonNeu;
    }

    public void setStrHnrvonNeu(final String strHnrvonNeu) {
        this.strHnrvonNeu = strHnrvonNeu;
    }

    public String getStrHnrbisNeu() {
        return this.strHnrbisNeu;
    }

    public void setStrHnrbisNeu(final String strHnrbisNeu) {
        this.strHnrbisNeu = strHnrbisNeu;
    }

    public SbRowId getSbRowId() {
        return this.sbRowId;
    }

    public void setSbRowId(final SbRowId sbRowId) {
        this.sbRowId = sbRowId;
    }

    public String getStrStverz() {
        return this.strStverz;
    }

    public void setStrStverz(final String strStverz) {
        this.strStverz = strStverz;
    }

    public String getStrNameSort() {
        return this.strNameSort;
    }

    public void setStrNameSort(final String strNameSort) {
        this.strNameSort = strNameSort;
    }

    public LocalDateTime getOutdatedAt() {
        return this.outdatedAt;
    }

    public void setOutdatedAt(final LocalDateTime outdatedSince) {
        outdatedAt = outdatedSince;
    }

    public LocalDateTime alreadyAppliedAt() {
        return this.alreadyAppliedAt;
    }

    public void setAlreadyAppliedAt(final LocalDateTime alreadyAppliedAt) {
        this.alreadyAppliedAt = alreadyAppliedAt;
    }

    @Override
    public boolean equals(final Object o) {
        if (null == o || this.getClass() != o.getClass()) return false;
        final SbRowEntity sbRow = (SbRowEntity) o;
        return Objects.equals(this.version, sbRow.version) && Objects.equals(this.strDatum, sbRow.strDatum) && Objects.equals(this.sbRowId, sbRow.sbRowId) && Objects.equals(this.strStverz, sbRow.strStverz) && Objects.equals(this.strNameSort, sbRow.strNameSort) && Objects.equals(this.strName46, sbRow.strName46) && Objects.equals(this.strName22, sbRow.strName22) && Objects.equals(this.strReserve, sbRow.strReserve) && Objects.equals(this.strHnrTyp, sbRow.strHnrTyp) && Objects.equals(this.strPlz, sbRow.strPlz) && Objects.equals(this.strCode, sbRow.strCode) && Objects.equals(this.strOtlSchl, sbRow.strOtlSchl) && Objects.equals(this.strAlorgB, sbRow.strAlorgB) && Objects.equals(this.strKgs, sbRow.strKgs) && Objects.equals(this.strAlortNeu, sbRow.strAlortNeu) && Objects.equals(this.strNamenSchlNeu, sbRow.strNamenSchlNeu) && Objects.equals(this.strBundLfdnrNeu, sbRow.strBundLfdnrNeu) && Objects.equals(this.strHnrvonNeu, sbRow.strHnrvonNeu) && Objects.equals(this.strHnrbisNeu, sbRow.strHnrbisNeu) && Objects.equals(this.outdatedAt, sbRow.outdatedAt) && Objects.equals(this.alreadyAppliedAt, sbRow.alreadyAppliedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.version, this.strDatum, this.sbRowId, this.strStverz, this.strNameSort, this.strName46, this.strName22, this.strReserve, this.strHnrTyp, this.strPlz, this.strCode, this.strOtlSchl, this.strAlorgB, this.strKgs, this.strAlortNeu, this.strNamenSchlNeu, this.strBundLfdnrNeu, this.strHnrvonNeu, this.strHnrbisNeu, this.outdatedAt, this.alreadyAppliedAt);
    }
}
