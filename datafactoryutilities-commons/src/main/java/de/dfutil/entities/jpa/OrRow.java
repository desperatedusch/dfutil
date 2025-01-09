package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractRow;
import de.dfutil.entities.ArchivablePostalObject;
import de.dfutil.entities.format.OrRowFormat;
import de.dfutil.entities.format.RowType;
import de.dfutil.entities.jpa.ids.OrRowId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;

import java.util.Date;

/**
 * Repräsentiert Orte
 */
@Entity
public class OrRow implements AbstractRow<OrRow, OrRowFormat>, ArchivablePostalObject {

    private final static RowType rowType = RowType.OR;

    @Version
    @Column(name = "version")
    private Date version;
    private String ortDatum;
    @EmbeddedId
    private OrRowId orRowId;
    private String ortAlort;
    private String ortStatus;
    private String ortOname;
    private String ortOnamePost;
    private String ortOzusatz;
    private String ortArtOzusatz;
    private String ortOname24;
    private String ortKgs;
    private String ortANeu;


    @Override
    public OrRow parseFrom(byte[] rowBytes) {
        for (var token : OrRowFormat.values()) {
            try {
                if (token.parseableContent())
                    applyRowFormatTokenOnRowBytes(token, rowBytes, this);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException("Parsing failed....", e);
            }
        }
        this.orRowId = new OrRowId(ortAlort, ortStatus);
        return this;
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

    public String getOrtANeu() {
        return ortANeu;
    }

    public void setOrtANeu(String ortANeu) {
        this.ortANeu = ortANeu;
    }

    public String getOrtKgs() {
        return ortKgs;
    }

    public void setOrtKgs(String ortKgs) {
        this.ortKgs = ortKgs;
    }

    public String getOrtOname24() {
        return ortOname24;
    }

    public void setOrtOname24(String ortOname24) {
        this.ortOname24 = ortOname24;
    }

    public String getOrtArtOzusatz() {
        return ortArtOzusatz;
    }

    public void setOrtArtOzusatz(String ortArtOzusatz) {
        this.ortArtOzusatz = ortArtOzusatz;
    }

    public String getOrtOzusatz() {
        return ortOzusatz;
    }

    public void setOrtOzusatz(String ortOzusatz) {
        this.ortOzusatz = ortOzusatz;
    }

    public String getOrtOnamePost() {
        return ortOnamePost;
    }

    public void setOrtOnamePost(String ortOnamePost) {
        this.ortOnamePost = ortOnamePost;
    }

    public String getOrtOname() {
        return ortOname;
    }

    public void setOrtOname(String ortOname) {
        this.ortOname = ortOname;
    }

    public String getOrtSTATUS() {
        return ortStatus;
    }

    public void setOrtSTATUS(String ortStatus) {
        this.ortStatus = ortStatus;
    }

    public String getOrtAlort() {
        return ortAlort;
    }

    public void setOrtAlort(String ortAlort) {
        this.ortAlort = ortAlort;
    }

    public String getOrtDatum() {
        return ortDatum;
    }

    public void setOrtDatum(String ortDatum) {
        this.ortDatum = ortDatum;
    }

    public OrRowId orRowId() {
        return orRowId;
    }

    public void setOrRowId(OrRowId orRowId) {
        this.orRowId = orRowId;
    }

}


