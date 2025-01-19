package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractRow;
import de.dfutil.entities.ArchivablePostalObject;
import de.dfutil.entities.RowType;
import de.dfutil.entities.jpa.ids.ObRowId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Repräsentiert Ortsteile
 */
@Entity
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

    public static ObRow parseFrom(byte[] rowBytes) {
        String string = new String(rowBytes, StandardCharsets.UTF_8);
        ObRow row = new ObRow();
        row.otlDatum = string.substring(9, 17);
        row.otlStverz = string.substring(34, 35);
        row.otlName = string.substring(35, 75);
        row.otlKgs = string.substring(75, 83);
        row.obRowId = new ObRowId
                (
                        string.substring(17, 25),
                        string.substring(25, 28),
                        string.substring(28, 33),
                        string.substring(33, 34)
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


