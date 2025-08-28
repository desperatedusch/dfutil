package de.dfutil.entities;

import de.dfutil.entities.ids.ObRowId;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * Repräsentiert Ortsteile
 */
@Entity
@Table(
        name = "ot_row",
        indexes =
                {
                        @Index(
                                name = "IDX_ORTSTEIL___OTL_ALORT__OTL_SCHL__OTL_PLZ__OTL_STATUS",
                                columnList = "OTL_ALORT,OTL_SCHL,OTL_PLZ,OTL_STATUS"),
                        @Index(
                                name = "IDX_ORTSTEIL___UUID",
                                columnList = "UUID")
                }
)
public class ObRowEntity extends AbstractRowEntity<ObRowEntity> implements ArchivablePostalObject {

    private static final RowType rowType = RowType.OB;

    @Version
    @Column(name = "version")
    private Date version;

    private String otlDatum;
    @EmbeddedId
    private ObRowId obRowId;
    private String otlStverz;
    private String otlName;
    private String otlKgs;
    private LocalDateTime outdatedAt;
    private LocalDateTime alreadyAppliedAt;

    @OneToOne
    @JoinColumn(name = "succession_state_id")
    private SuccessionState successionState;

    public static ObRowEntity parseFrom(final String rowBytes) {
        final ObRowEntity row = new ObRowEntity();
        row.importingFileIdentifier = rowBytes.substring(0, 9);
        row.otlDatum = rowBytes.substring(9, 17);
        row.otlStverz = rowBytes.substring(34, 35);
        row.otlName = rowBytes.substring(35, 75);
        row.otlKgs = rowBytes.substring(75, 83);
        row.obRowId =
                new ObRowId(
                        rowBytes.substring(17, 25),
                        rowBytes.substring(25, 28),
                        rowBytes.substring(28, 33),
                        rowBytes.substring(33, 34));
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
        return ObRowEntity.rowType;
    }

    public String getOtlDatum() {
        return this.otlDatum;
    }

    public void setOtlDatum(final String otlDatum) {
        this.otlDatum = otlDatum;
    }

    public String getOtlStverz() {
        return this.otlStverz;
    }

    public void setOtlStverz(final String otlStverz) {
        this.otlStverz = otlStverz;
    }

    public String getOtlName() {
        return this.otlName;
    }

    public void setOtlName(final String otlName) {
        this.otlName = otlName;
    }

    public String getOtlKgs() {
        return this.otlKgs;
    }

    public void setOtlKgs(final String otlKgs) {
        this.otlKgs = otlKgs;
    }

    public ObRowId getObRowId() {
        return this.obRowId;
    }

    public void setObRowId(final ObRowId obRowId) {
        this.obRowId = obRowId;
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
        final ObRowEntity obRow = (ObRowEntity) o;
        return Objects.equals(this.version, obRow.version) && Objects.equals(this.otlDatum, obRow.otlDatum) && Objects.equals(this.obRowId, obRow.obRowId) && Objects.equals(this.otlStverz, obRow.otlStverz) && Objects.equals(this.otlName, obRow.otlName) && Objects.equals(this.otlKgs, obRow.otlKgs) && Objects.equals(this.outdatedAt, obRow.outdatedAt) && Objects.equals(this.alreadyAppliedAt, obRow.alreadyAppliedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.version, this.otlDatum, this.obRowId, this.otlStverz, this.otlName, this.otlKgs, this.outdatedAt, this.alreadyAppliedAt);
    }

}
