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
        name = "or_row",
        indexes =
                {
                        @Index(
                                name = "IDX_ORT___ORT_ALORT__ORT_STATUS",
                                columnList = "ORT_ALORT,ORT_STATUS"),
                        @Index(
                                name = "IDX_ORT___UUID",
                                columnList = "UUID")
                }
)
public class OrRowEntity extends AbstractRowEntity<OrRowEntity> implements ArchivablePostalObject {

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

    @OneToOne
    @JoinColumn(name = "succession_state_id")
    private SuccessionState successionState;

    public static OrRowEntity parseFrom(final String rowBytes) {
        final OrRowEntity row = new OrRowEntity();
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
        return OrRowEntity.rowType;
    }

    public String getOrtAlortNeu() {
        return this.ortAlortNeu;
    }

    public void setOrtAlortNeu(final String ortANeu) {
        ortAlortNeu = ortANeu;
    }

    public String getOrtKgs() {
        return this.ortKgs;
    }

    public void setOrtKgs(final String ortKgs) {
        this.ortKgs = ortKgs;
    }

    public String getOrtOname24() {
        return this.ortOname24;
    }

    public void setOrtOname24(final String ortOname24) {
        this.ortOname24 = ortOname24;
    }

    public String getOrtArtOzusatz() {
        return this.ortArtOzusatz;
    }

    public void setOrtArtOzusatz(final String ortArtOzusatz) {
        this.ortArtOzusatz = ortArtOzusatz;
    }

    public String getOrtOzusatz() {
        return this.ortOzusatz;
    }

    public void setOrtOzusatz(final String ortOzusatz) {
        this.ortOzusatz = ortOzusatz;
    }

    public String getOrtOnamePost() {
        return this.ortOnamePost;
    }

    public void setOrtOnamePost(final String ortOnamePost) {
        this.ortOnamePost = ortOnamePost;
    }

    public String getOrtOname() {
        return this.ortOname;
    }

    public void setOrtOname(final String ortOname) {
        this.ortOname = ortOname;
    }

    public String getOrtDatum() {
        return this.ortDatum;
    }

    public void setOrtDatum(final String ortDatum) {
        this.ortDatum = ortDatum;
    }

    public OrRowId orRowId() {
        return this.orRowId;
    }

    public OrRowId getOrRowId() {
        return this.orRowId;
    }

    public void setOrRowId(final OrRowId orRowId) {
        this.orRowId = orRowId;
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
        final OrRowEntity orRow = (OrRowEntity) o;
        return Objects.equals(this.version, orRow.version) && Objects.equals(this.ortDatum, orRow.ortDatum) && Objects.equals(this.orRowId, orRow.orRowId) && Objects.equals(this.ortOname, orRow.ortOname) && Objects.equals(this.ortOnamePost, orRow.ortOnamePost) && Objects.equals(this.ortOzusatz, orRow.ortOzusatz) && Objects.equals(this.ortArtOzusatz, orRow.ortArtOzusatz) && Objects.equals(this.ortOname24, orRow.ortOname24) && Objects.equals(this.ortKgs, orRow.ortKgs) && Objects.equals(this.ortAlortNeu, orRow.ortAlortNeu) && Objects.equals(this.outdatedAt, orRow.outdatedAt) && Objects.equals(this.alreadyAppliedAt, orRow.alreadyAppliedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.version, this.ortDatum, this.orRowId, this.ortOname, this.ortOnamePost, this.ortOzusatz, this.ortArtOzusatz, this.ortOname24, this.ortKgs, this.ortAlortNeu, this.outdatedAt, this.alreadyAppliedAt);
    }

}
