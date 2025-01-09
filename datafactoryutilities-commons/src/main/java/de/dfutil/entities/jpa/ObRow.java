package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractRow;
import de.dfutil.entities.ArchivablePostalObject;
import de.dfutil.entities.format.ObRowFormat;
import de.dfutil.entities.format.RowType;
import de.dfutil.entities.jpa.ids.ObRowId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;

import java.util.Date;

/**
 * Repräsentiert Ortsteile
 */
@Entity
public class ObRow implements AbstractRow<ObRow, ObRowFormat>, ArchivablePostalObject {

    private final static RowType rowType = RowType.OB;

    @Version
    @Column(name = "version")
    private Date version;
    private String otlDatum;
    @EmbeddedId
    private ObRowId obRowId;
    private String otlAlort;
    private String otlSchl;
    private String otlPlz;
    private String otlStatus;
    private String otlStverz;
    private String otlName;
    private String otlKgs;

    @Override
    public ObRow parseFrom(byte[] rowBytes) {
        for (var token : ObRowFormat.values()) {
            try {
                if (token.parseableContent())
                    applyRowFormatTokenOnRowBytes(token, rowBytes, this);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException("Parsing failed....", e);
            }
        }
        this.obRowId = new ObRowId(otlAlort, otlSchl, otlPlz, otlStatus);
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

    public String getOtlDatum() {
        return otlDatum;
    }

    public void setOtlDatum(String otlDatum) {
        this.otlDatum = otlDatum;
    }

    public String getOtlAlort() {
        return otlAlort;
    }

    public void setOtlAlort(String otlAlort) {
        this.otlAlort = otlAlort;
    }

    public String getOtlSchl() {
        return otlSchl;
    }

    public void setOtlSchl(String otlSchl) {
        this.otlSchl = otlSchl;
    }

    public String getOtlPlz() {
        return otlPlz;
    }

    public void setOtlPlz(String otlPlz) {
        this.otlPlz = otlPlz;
    }

    public String getOtlStatus() {
        return otlStatus;
    }

    public void setOtlStatus(String otlStatus) {
        this.otlStatus = otlStatus;
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


