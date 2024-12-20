package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractDataFactoryRow;
import de.dfutil.entities.SerializablePostalObject;
import de.dfutil.entities.format.PlRowFormat;
import de.dfutil.entities.format.RowType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Version;

import java.util.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
public class PlRow implements AbstractDataFactoryRow<PlRow, PlRowFormat>, SerializablePostalObject {

    private final static RowType rowType = RowType.PL;
    @jakarta.persistence.Id
    @GeneratedValue(strategy = SEQUENCE, generator = "ID_SEQ")
    @Column(name = "id")
    private Long id;
    @Version
    @Column(name = "version")
    private Date version;
    private String plzDatum;
    private String plzPlz;
    private String plzAlOrt;
    private String plzArtkardinalität;
    private String plzArtauslierferung;
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
    private String plzRabattInfoschwer;
    private String plzReserve;
    private String plzFzNr;
    private String plzBzNr;


    @Override
    public PlRow parseFrom(byte[] rowBytes) {
        for (var token : PlRowFormat.values()) {
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

    public String getPlzDatum() {
        return plzDatum;
    }

    public void setPlzDatum(String plzDatum) {
        this.plzDatum = plzDatum;
    }

    public String getPlzPlz() {
        return plzPlz;
    }

    public void setPlzPlz(String plzPlz) {
        this.plzPlz = plzPlz;
    }

    public String getPlzAlOrt() {
        return plzAlOrt;
    }

    public void setPlzAlOrt(String plzAlOrt) {
        this.plzAlOrt = plzAlOrt;
    }

    public String getPlzArtkardinalität() {
        return plzArtkardinalität;
    }

    public void setPlzArtkardinalität(String plzArtKardinalität) {
        this.plzArtkardinalität = plzArtKardinalität;
    }

    public String getPlzArtauslierferung() {
        return plzArtauslierferung;
    }

    public void setPlzArtauslierferung(String plzArtAuslierferung) {
        this.plzArtauslierferung = plzArtAuslierferung;
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

    public String getPlzRabattInfoschwer() {
        return plzRabattInfoschwer;
    }

    public void setPlzRabattInfoschwer(String plzRabattInfoschwer) {
        this.plzRabattInfoschwer = plzRabattInfoschwer;
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


