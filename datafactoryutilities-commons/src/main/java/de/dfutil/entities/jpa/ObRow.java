package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractRow;
import de.dfutil.entities.ArchivablePostalObject;
import de.dfutil.entities.RowType;
import de.dfutil.entities.jpa.ids.ObRowId;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Repräsentiert Ortsteile
 */
@Entity
@Table(name = "ORT")
public class ObRow implements AbstractRow<ObRow>, ArchivablePostalObject {

    private final static RowType rowType = RowType.OB;

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
        row.otlDatum = rowBytes.substring(9, 17);
        row.otlStverz = rowBytes.substring(34, 35);
        row.otlName = rowBytes.substring(35, 75);
        row.otlKgs = rowBytes.substring(75, 83);
        row.obRowId = new ObRowId
                (
                        rowBytes.substring(17, 25),
                        rowBytes.substring(25, 28),
                        rowBytes.substring(28, 33),
                        rowBytes.substring(33, 34)
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

}


