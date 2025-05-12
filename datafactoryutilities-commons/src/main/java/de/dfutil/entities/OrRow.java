package de.dfutil.entities;

import de.dfutil.entities.ids.OrRowId;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * Repräsentiert Orte
 */
@Entity
@Table(
        name = "ORT",
        indexes = @Index(
                name =
                        "IDX_ORT___ORT_ALORT__ORT_STATUS",
                columnList =
                        "ORT_ALORT,ORT_STATUS"))
public class OrRow extends AbstractRow<OrRow> implements ArchivablePostalObject {

    private static final RowType rowType = RowType.OR;

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
    private String ortAlortNeu;
    private LocalDateTime outdatedAt;
    private LocalDateTime alreadyAppliedAt;

    public static OrRow parseFrom(String rowBytes) {
        OrRow row = new OrRow();
        row.importingFileIdentifier = rowBytes.substring(0, 9);
        row.ortDatum = rowBytes.substring(9, 17);
        row.ortOname = rowBytes.substring(26, 66);
        row.ortOnamePost = rowBytes.substring(66, 106);
        row.ortOzusatz = rowBytes.substring(106, 136);
        row.ortArtOzusatz = rowBytes.substring(136, 137);
        row.ortOname24 = rowBytes.substring(137, 161);
        row.ortKgs = rowBytes.substring(161, 169);
        row.ortAlortNeu = rowBytes.substring(169, 177);
        row.orRowId = new OrRowId(rowBytes.substring(17, 25), rowBytes.substring(25, 26));
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

    public String getOrtAlortNeu() {
        return ortAlortNeu;
    }

    public void setOrtAlortNeu(String ortANeu) {
        this.ortAlortNeu = ortANeu;
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

    public OrRowId getOrRowId() {
        return orRowId;
    }

    public void setOrRowId(OrRowId orRowId) {
        this.orRowId = orRowId;
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
        if (o == null || getClass() != o.getClass()) return false;
        OrRow orRow = (OrRow) o;
        return Objects.equals(version, orRow.version) && Objects.equals(ortDatum, orRow.ortDatum) && Objects.equals(orRowId, orRow.orRowId) && Objects.equals(ortOname, orRow.ortOname) && Objects.equals(ortOnamePost, orRow.ortOnamePost) && Objects.equals(ortOzusatz, orRow.ortOzusatz) && Objects.equals(ortArtOzusatz, orRow.ortArtOzusatz) && Objects.equals(ortOname24, orRow.ortOname24) && Objects.equals(ortKgs, orRow.ortKgs) && Objects.equals(ortAlortNeu, orRow.ortAlortNeu) && Objects.equals(outdatedAt, orRow.outdatedAt) && Objects.equals(alreadyAppliedAt, orRow.alreadyAppliedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, ortDatum, orRowId, ortOname, ortOnamePost, ortOzusatz, ortArtOzusatz, ortOname24, ortKgs, ortAlortNeu, outdatedAt, alreadyAppliedAt);
    }

}
