package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractRow;
import de.dfutil.entities.ArchivablePostalObject;
import de.dfutil.entities.RowType;
import de.dfutil.entities.jpa.ids.OrRowId;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Repräsentiert Orte
 */
@Entity
@Table(name = "ORTSTEIL", indexes = @Index(columnList = "ORT_ALORT,ORT_STATUS"))
public class OrRow implements AbstractRow<OrRow>, ArchivablePostalObject {

    private final static RowType rowType = RowType.OR;

    @Version
    @Column(name = "version")
    private Date version;
    private String ortDatum;
    @EmbeddedId
    private OrRowId orRowId;
    private String ortOname;
    private String ortOnamePost;
    private String ortOzusatz;
    private String ortArtOzusatz;
    private String ortOname24;
    private String ortKgs;
    private String ortANeu;

    public static OrRow parseFrom(String rowBytes) {
        OrRow row = new OrRow();
        row.ortDatum = rowBytes.substring(9, 17);
        row.ortOname = rowBytes.substring(26, 66);
        row.ortOnamePost = rowBytes.substring(66, 106);
        row.ortOzusatz = rowBytes.substring(106, 136);
        row.ortArtOzusatz = rowBytes.substring(136, 137);
        row.ortOname24 = rowBytes.substring(137, 161);
        row.ortKgs = rowBytes.substring(161, 169);
        row.ortANeu = rowBytes.substring(169, 177);
        row.orRowId = new OrRowId
                (
                        rowBytes.substring(17, 25),
                        rowBytes.substring(25, 26)
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

    public OrRowId getOrRowId() {
        return orRowId;
    }

}


