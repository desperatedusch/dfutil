package de.dfutil.entities;

import de.dfutil.entities.ids.PlRowId;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

/**
 * Repräsentiert einzelne Postleitzahlbereiche
 */
@Entity
@Table(
        name = "pl_row",
        indexes =
                {
                        @Index(
                                name = "IDX_PLZ___PLZ_PLZ__PLZ_ALORT",
                                columnList = "PLZ_PLZ,PLZ_ALORT"),
                        @Index(
                                name = "IDX_PLZ___UUID",
                                columnList = "UUID")
                }
)
public class PlRowEntity extends AbstractRowEntity<PlRowEntity> implements SerializablePostalObject {

    private static final RowType rowType = RowType.PL;

    @Version
    @Column(name = "version")
    private Date version;

    private String plzDatum;
    @EmbeddedId
    private PlRowId plRowId;
    private String plzArtKardinalität;
    private String plzArtAuslierferung;
    private String plzStverz;
    private String plzPfverz;
    private String plzOname;
    private String plzOzusatz;
    private String plzArtOzusatz;
    private String plzOname24;
    private String plzPostlag;
    private String plzLaBrief;
    private String plzLaAlort;
    private String plzKgs;
    private String plzOrtCode;
    private String plzLeitcodeMax;
    private String plzRabattInfoSchwer;
    private String plzReserve;
    private String plzFzNr;
    private String plzBzNr;

    public static PlRowEntity parseFrom(final String rowBytes) {
        final PlRowEntity row = new PlRowEntity();
        row.importingFileIdentifier = rowBytes.substring(0, 9);
        row.plzDatum = rowBytes.substring(9, 17);
        row.plzArtKardinalität = rowBytes.substring(30, 31);
        row.plzArtAuslierferung = rowBytes.substring(31, 32);
        row.plzStverz = rowBytes.substring(32, 33);
        row.plzPfverz = rowBytes.substring(33, 34);
        row.plzOname = rowBytes.substring(34, 74);
        row.plzOzusatz = rowBytes.substring(74, 104);
        row.plzArtOzusatz = rowBytes.substring(104, 105);
        row.plzOname24 = rowBytes.substring(105, 129);
        row.plzPostlag = rowBytes.substring(129, 130);
        row.plzLaBrief = rowBytes.substring(130, 138);
        row.plzLaAlort = rowBytes.substring(138, 146);
        row.plzKgs = rowBytes.substring(146, 154);
        row.plzOrtCode = rowBytes.substring(154, 157);
        row.plzLeitcodeMax = rowBytes.substring(157, 160);
        row.plzRabattInfoSchwer = rowBytes.substring(160, 161);
        row.plzReserve = rowBytes.substring(161, 163);
        row.plzFzNr = rowBytes.substring(163, 165);
        row.plzBzNr = rowBytes.substring(165, 167);
        row.plRowId = new PlRowId(rowBytes.substring(17, 22), rowBytes.substring(22, 30));
        return row;
    }

    public Date getVersion() {
        return this.version;
    }

    public void setVersion(final Date version) {
        this.version = version;
    }

    public RowType getRowType() {
        return PlRowEntity.rowType;
    }

    public String getPlzDatum() {
        return this.plzDatum;
    }

    public void setPlzDatum(final String plzDatum) {
        this.plzDatum = plzDatum;
    }

    public String getPlzArtKardinalität() {
        return this.plzArtKardinalität;
    }

    public void setPlzArtKardinalität(final String plzArtKardinalität) {
        this.plzArtKardinalität = plzArtKardinalität;
    }

    public String getPlzArtAuslierferung() {
        return this.plzArtAuslierferung;
    }

    public void setPlzArtAuslierferung(final String plzArtAuslierferung) {
        this.plzArtAuslierferung = plzArtAuslierferung;
    }

    public String getPlzStverz() {
        return this.plzStverz;
    }

    public void setPlzStverz(final String plzStVerz) {
        plzStverz = plzStVerz;
    }

    public String getPlzPfverz() {
        return this.plzPfverz;
    }

    public void setPlzPfverz(final String plzPfVerz) {
        plzPfverz = plzPfVerz;
    }

    public String getPlzOname() {
        return this.plzOname;
    }

    public void setPlzOname(final String plzOName) {
        plzOname = plzOName;
    }

    public String getPlzOzusatz() {
        return this.plzOzusatz;
    }

    public void setPlzOzusatz(final String plzOzusatz) {
        this.plzOzusatz = plzOzusatz;
    }

    public String getPlzArtOzusatz() {
        return this.plzArtOzusatz;
    }

    public void setPlzArtOzusatz(final String plzArtOzusatz) {
        this.plzArtOzusatz = plzArtOzusatz;
    }

    public String getPlzOname24() {
        return this.plzOname24;
    }

    public void setPlzOname24(final String plzOname24) {
        this.plzOname24 = plzOname24;
    }

    public String getPlzPostlag() {
        return this.plzPostlag;
    }

    public void setPlzPostlag(final String plzPostlag) {
        this.plzPostlag = plzPostlag;
    }

    public String getPlzLaBrief() {
        return this.plzLaBrief;
    }

    public void setPlzLaBrief(final String plzLaBrief) {
        this.plzLaBrief = plzLaBrief;
    }

    public String getPlzLaAlort() {
        return this.plzLaAlort;
    }

    public void setPlzLaAlort(final String plzLaAlort) {
        this.plzLaAlort = plzLaAlort;
    }

    public String getPlzKgs() {
        return this.plzKgs;
    }

    public void setPlzKgs(final String plzKgs) {
        this.plzKgs = plzKgs;
    }

    public String getPlzOrtCode() {
        return this.plzOrtCode;
    }

    public void setPlzOrtCode(final String plzOrtCode) {
        this.plzOrtCode = plzOrtCode;
    }

    public String getPlzLeitcodeMax() {
        return this.plzLeitcodeMax;
    }

    public void setPlzLeitcodeMax(final String plzLeitcodeMax) {
        this.plzLeitcodeMax = plzLeitcodeMax;
    }

    public String getPlzRabattInfoSchwer() {
        return this.plzRabattInfoSchwer;
    }

    public void setPlzRabattInfoSchwer(final String plzRabattInfoSchwer) {
        this.plzRabattInfoSchwer = plzRabattInfoSchwer;
    }

    public String getPlzReserve() {
        return this.plzReserve;
    }

    public void setPlzReserve(final String plzReserve) {
        this.plzReserve = plzReserve;
    }

    public String getPlzFzNr() {
        return this.plzFzNr;
    }

    public void setPlzFzNr(final String plzFzNr) {
        this.plzFzNr = plzFzNr;
    }

    public String getPlzBzNr() {
        return this.plzBzNr;
    }

    public void setPlzBzNr(final String plzBzNr) {
        this.plzBzNr = plzBzNr;
    }

    public PlRowId getPlRowId() {
        return this.plRowId;
    }

    public void setPlRowId(final PlRowId plRowId) {
        this.plRowId = plRowId;
    }

    @Override
    public boolean equals(final Object o) {
        if (null == o || this.getClass() != o.getClass()) return false;
        final PlRowEntity plRow = (PlRowEntity) o;
        return Objects.equals(this.version, plRow.version) && Objects.equals(this.plzDatum, plRow.plzDatum) && Objects.equals(this.plRowId, plRow.plRowId) && Objects.equals(this.plzArtKardinalität, plRow.plzArtKardinalität) && Objects.equals(this.plzArtAuslierferung, plRow.plzArtAuslierferung) && Objects.equals(this.plzStverz, plRow.plzStverz) && Objects.equals(this.plzPfverz, plRow.plzPfverz) && Objects.equals(this.plzOname, plRow.plzOname) && Objects.equals(this.plzOzusatz, plRow.plzOzusatz) && Objects.equals(this.plzArtOzusatz, plRow.plzArtOzusatz) && Objects.equals(this.plzOname24, plRow.plzOname24) && Objects.equals(this.plzPostlag, plRow.plzPostlag) && Objects.equals(this.plzLaBrief, plRow.plzLaBrief) && Objects.equals(this.plzLaAlort, plRow.plzLaAlort) && Objects.equals(this.plzKgs, plRow.plzKgs) && Objects.equals(this.plzOrtCode, plRow.plzOrtCode) && Objects.equals(this.plzLeitcodeMax, plRow.plzLeitcodeMax) && Objects.equals(this.plzRabattInfoSchwer, plRow.plzRabattInfoSchwer) && Objects.equals(this.plzReserve, plRow.plzReserve) && Objects.equals(this.plzFzNr, plRow.plzFzNr) && Objects.equals(this.plzBzNr, plRow.plzBzNr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.version, this.plzDatum, this.plRowId, this.plzArtKardinalität, this.plzArtAuslierferung, this.plzStverz, this.plzPfverz, this.plzOname, this.plzOzusatz, this.plzArtOzusatz, this.plzOname24, this.plzPostlag, this.plzLaBrief, this.plzLaAlort, this.plzKgs, this.plzOrtCode, this.plzLeitcodeMax, this.plzRabattInfoSchwer, this.plzReserve, this.plzFzNr, this.plzBzNr);
    }

}
