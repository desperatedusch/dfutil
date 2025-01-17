package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractRow;
import de.dfutil.entities.RowType;
import de.dfutil.entities.SerializablePostalObject;
import de.dfutil.entities.jpa.ids.KgRowId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;

import java.util.Arrays;
import java.util.Date;

/**
 * In der Kreisgemeindedatei KGS-DA stehen die amtlichen Namen der Gemeinden, Kreise, kreisfreien Städte,
 * Regierungsbezirke und Bundesländer zusammen mit dem achtstelligen Kreisgemeindeschlüssel. Diese Daten
 * werden vom Statistischen Bundesamt in Wiesbaden übernommen und aufgrund der Angaben der statistischen
 * Landesämter erweitert. Mit Hilfe dieses Schlüssels wird die staatliche Gliederung in den Dateien ORT-DA,
 * STRA-DB, OTL-DB und PLZ-DA dargestellt.
 */
@Entity
public class KgRow implements AbstractRow<KgRow>, SerializablePostalObject {

    private final static RowType rowType = RowType.KG;

    @Version
    @Column(name = "version")
    private Date version;
    private String kgDatum;
    @EmbeddedId
    private KgRowId kgRowId;
    private String kgName;


    public static KgRow parseFrom(byte[] rowBytes) {
        KgRow row = new KgRow();
        row.kgDatum = Arrays.toString(rowBytes).substring(9, 17);
        row.kgName = Arrays.toString(rowBytes).substring(26, 66);
        row.kgRowId = new KgRowId
                (
                        Arrays.toString(rowBytes).substring(17, 25),
                        Arrays.toString(rowBytes).substring(25, 26)
                );
        return row;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }


    public String getKgDatum() {
        return kgDatum;
    }

    public void setKgDatum(String kgDatum) {
        this.kgDatum = kgDatum;
    }

    public String getKgName() {
        return kgName;
    }

    public void setKgName(String kgName) {
        this.kgName = kgName;
    }

    public RowType getRowType() {
        return rowType;
    }

    public KgRowId getKgRowId() {
        return kgRowId;
    }

    public void setKgRowId(KgRowId kgRowId) {
        this.kgRowId = kgRowId;
    }

}


