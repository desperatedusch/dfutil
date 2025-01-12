package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractRow;
import de.dfutil.entities.ArchivablePostalObject;
import de.dfutil.entities.RowType;
import de.dfutil.entities.jpa.ids.ObRowId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;

import java.util.Arrays;
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

    /**
     * OB02("OTL-DATUM", 8, 10, 17, true),
     * OB03("OTL-ALORT", 8, 18, 25, true),
     * OB04("OTL-SCHL", 3, 26, 28, true),
     * OB05("OTL-PLZ", 5, 29, 33, true),
     * OB06("OTL-STATUS", 1, 34, 34, true),
     * OB07("OTL-STVERZ", 1, 35, 35, true),
     * OB08("OTL-NAME", 40, 36, 75, true),
     * OB09("OTL-KGS", 8, 76, 83, true),
     * OB10("SATZENDE", 1, 84, 84);
     */

    public static ObRow parseFrom(byte[] rowBytes) {
        ObRow row = new ObRow();
        row.otlDatum = Arrays.toString(rowBytes).substring(9, 17);
        row.otlStverz = Arrays.toString(rowBytes).substring(35, 36);
        row.otlName = Arrays.toString(rowBytes).substring(35, 75);
        row.otlKgs = Arrays.toString(rowBytes).substring(75, 83);
        row.obRowId = new ObRowId(Arrays.toString(rowBytes).substring(17, 25), Arrays.toString(rowBytes).substring(25, 28), Arrays.toString(rowBytes).substring(28, 33), Arrays.toString(rowBytes).substring(34, 35));
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

}


