package de.dfutil.entities;

import de.dfutil.entities.ids.ObRowId;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

/**
 * Repräsentiert Ortsteile
 */
@Entity
@Table(
        name = "ORTSTEIL",
        indexes =
        @Index(
                name = "IDX_ORTSTEIL___OTL_ALORT__OTL_SCHL__OTL_PLZ__OTL_STATUS__OUTDATED_AT__ALREADY_APPLIED_AT",
                columnList = "OTL_ALORT,OTL_SCHL,OTL_PLZ,OTL_STATUS,OUTDATED_AT,ALREADY_APPLIED_AT"))
public class ObRow extends AbstractRow implements ArchivablePostalObject {

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


    public static ObRow parseFrom(String rowBytes) {
        ObRow row = new ObRow();
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
                        rowBytes.substring(33, 34),
                        null,
                        null
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

    public String getOtlDatum() {
        return otlDatum;
    }

    public void setOtlDatum(String otlDatum) {
        this.otlDatum = otlDatum;
    }

    public String getOtlStverz() {
        return otlStverz;
    }

    public void setOtlStverz(String otlStverz) {
        this.otlStverz = otlStverz;
    }

    public String getOtlName() {
        return otlName;
    }

    public void setOtlName(String otlName) {
        this.otlName = otlName;
    }

    public String getOtlKgs() {
        return otlKgs;
    }

    public void setOtlKgs(String otlKgs) {
        this.otlKgs = otlKgs;
    }

    public ObRowId getObRowId() {
        return obRowId;
    }

    public void setObRowId(ObRowId obRowId) {
        this.obRowId = obRowId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ObRow obRow = (ObRow) o;
        return Objects.equals(version, obRow.version) && Objects.equals(otlDatum, obRow.otlDatum) && Objects.equals(obRowId, obRow.obRowId) && Objects.equals(otlStverz, obRow.otlStverz) && Objects.equals(otlName, obRow.otlName) && Objects.equals(otlKgs, obRow.otlKgs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, otlDatum, obRowId, otlStverz, otlName, otlKgs);
    }

}
