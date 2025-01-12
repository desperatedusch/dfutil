package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractRow;
import de.dfutil.entities.RowType;
import de.dfutil.entities.SerializablePostalObject;
import de.dfutil.entities.jpa.ids.PlRowId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;

import java.util.Arrays;
import java.util.Date;


/**
 * Repräsentiert einzelne Postleitzahlbereiche
 */
@Entity
public class PlRow implements AbstractRow<PlRow>, SerializablePostalObject {

    private final static RowType rowType = RowType.PL;

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

    public static PlRow parseFrom(byte[] rowBytes) {
        PlRow row = new PlRow();
        row.plzDatum = Arrays.toString(rowBytes).substring(9, 17);
        row.plzArtKardinalität = Arrays.toString(rowBytes).substring(30, 31);
        row.plzArtAuslierferung = Arrays.toString(rowBytes).substring(31, 32);
        row.plzStverz = Arrays.toString(rowBytes).substring(32, 33);
        row.plzPfverz = Arrays.toString(rowBytes).substring(33, 34);
        row.plzOname = Arrays.toString(rowBytes).substring(34, 74);
        row.plzOzusatz = Arrays.toString(rowBytes).substring(74, 104);
        row.plzArtOzusatz = Arrays.toString(rowBytes).substring(104, 105);
        row.plzOname24 = Arrays.toString(rowBytes).substring(105, 129);
        row.plzPostlag = Arrays.toString(rowBytes).substring(129, 130);
        row.plzLaBrief = Arrays.toString(rowBytes).substring(130, 138);
        row.plzLaAlort = Arrays.toString(rowBytes).substring(138, 146);
        row.plzKgs = Arrays.toString(rowBytes).substring(146, 154);
        row.plzOrtCode = Arrays.toString(rowBytes).substring(154, 157);
        row.plzLeitcodeMax = Arrays.toString(rowBytes).substring(157, 160);
        row.plzRabattInfoSchwer = Arrays.toString(rowBytes).substring(160, 161);
        row.plzReserve = Arrays.toString(rowBytes).substring(161, 163);
        row.plzFzNr = Arrays.toString(rowBytes).substring(163, 165);
        row.plzBzNr = Arrays.toString(rowBytes).substring(165, 167);
        row.plRowId = new PlRowId(Arrays.toString(rowBytes).substring(17, 22), Arrays.toString(rowBytes).substring(22, 30));
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

    public String getPlzDatum() {
        return plzDatum;
    }

    public void setPlzDatum(String plzDatum) {
        this.plzDatum = plzDatum;
    }

    public String getPlzArtKardinalität() {
        return plzArtKardinalität;
    }

    public void setPlzArtKardinalität(String plzArtKardinalität) {
        this.plzArtKardinalität = plzArtKardinalität;
    }

    public String getPlzArtAuslierferung() {
        return plzArtAuslierferung;
    }

    public void setPlzArtAuslierferung(String plzArtAuslierferung) {
        this.plzArtAuslierferung = plzArtAuslierferung;
    }

    public String getPlzStverz() {
        return plzStverz;
    }

    public void setPlzStverz(String plzStVerz) {
        this.plzStverz = plzStVerz;
    }

    public String getPlzPfverz() {
        return plzPfverz;
    }

    public void setPlzPfverz(String plzPfVerz) {
        this.plzPfverz = plzPfVerz;
    }

    public String getPlzOname() {
        return plzOname;
    }

    public void setPlzOname(String plzOName) {
        this.plzOname = plzOName;
    }

    public String getPlzOzusatz() {
        return plzOzusatz;
    }

    public void setPlzOzusatz(String plzOzusatz) {
        this.plzOzusatz = plzOzusatz;
    }

    public String getPlzArtOzusatz() {
        return plzArtOzusatz;
    }

    public void setPlzArtOzusatz(String plzArtOzusatz) {
        this.plzArtOzusatz = plzArtOzusatz;
    }

    public String getPlzOname24() {
        return plzOname24;
    }

    public void setPlzOname24(String plzOname24) {
        this.plzOname24 = plzOname24;
    }

    public String getPlzPostlag() {
        return plzPostlag;
    }

    public void setPlzPostlag(String plzPostlag) {
        this.plzPostlag = plzPostlag;
    }

    public String getPlzLaBrief() {
        return plzLaBrief;
    }

    public void setPlzLaBrief(String plzLaBrief) {
        this.plzLaBrief = plzLaBrief;
    }

    public String getPlzLaAlort() {
        return plzLaAlort;
    }

    public void setPlzLaAlort(String plzLaAlort) {
        this.plzLaAlort = plzLaAlort;
    }

    public String getPlzKgs() {
        return plzKgs;
    }

    public void setPlzKgs(String plzKgs) {
        this.plzKgs = plzKgs;
    }

    public String getPlzOrtCode() {
        return plzOrtCode;
    }

    public void setPlzOrtCode(String plzOrtCode) {
        this.plzOrtCode = plzOrtCode;
    }

    public String getPlzLeitcodeMax() {
        return plzLeitcodeMax;
    }

    public void setPlzLeitcodeMax(String plzLeitcodeMax) {
        this.plzLeitcodeMax = plzLeitcodeMax;
    }

    public String getPlzRabattInfoSchwer() {
        return plzRabattInfoSchwer;
    }

    public void setPlzRabattInfoSchwer(String plzRabattInfoSchwer) {
        this.plzRabattInfoSchwer = plzRabattInfoSchwer;
    }

    public String getPlzReserve() {
        return plzReserve;
    }

    public void setPlzReserve(String plzReserve) {
        this.plzReserve = plzReserve;
    }

    public String getPlzFzNr() {
        return plzFzNr;
    }

    public void setPlzFzNr(String plzFzNr) {
        this.plzFzNr = plzFzNr;
    }

    public String getPlzBzNr() {
        return plzBzNr;
    }

    public void setPlzBzNr(String plzBzNr) {
        this.plzBzNr = plzBzNr;
    }

}

