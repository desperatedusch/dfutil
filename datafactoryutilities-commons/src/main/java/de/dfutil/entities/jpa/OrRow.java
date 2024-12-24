package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractDataFactoryRow;
import de.dfutil.entities.SerializablePostalObject;
import de.dfutil.entities.format.OrRowFormat;
import de.dfutil.entities.format.RowType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Version;

import java.util.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
public class OrRow implements AbstractDataFactoryRow<OrRow, OrRowFormat>, SerializablePostalObject {

    private final static RowType rowType = RowType.OR;
    @jakarta.persistence.Id
    @GeneratedValue(strategy = SEQUENCE, generator = "ID_SEQ")
    @Column(name = "id")
    private Long id;
    @Version
    @Column(name = "version")
    private Date version;


    private String ortDatum;
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

}


