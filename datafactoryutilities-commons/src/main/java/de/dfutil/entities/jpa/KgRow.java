package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractRow;
import de.dfutil.entities.RowType;
import de.dfutil.entities.SerializablePostalObject;
import de.dfutil.entities.jpa.ids.KgRowId;
import jakarta.persistence.*;

import java.util.Date;

/**
 * In der Kreisgemeindedatei KGS-DA stehen die amtlichen Namen der Gemeinden, Kreise, kreisfreien
 * Städte, Regierungsbezirke und Bundesländer zusammen mit dem achtstelligen Kreisgemeindeschlüssel.
 * Diese Daten werden vom Statistischen Bundesamt in Wiesbaden übernommen und aufgrund der Angaben
 * der statistischen Landesämter erweitert. Mit Hilfe dieses Schlüssels wird die staatliche
 * Gliederung in den Dateien ORT-DA, STRA-DB, OTL-DB und PLZ-DA dargestellt.
 */
@Entity
@Table(
        name = "AGS",
        indexes =
        @Index(
                name = "IDX_AGS___KG_SCHLUESSEL__KG_SATZART",
                columnList = "KG_SCHLUESSEL,KG_SATZART"))
public class KgRow extends AbstractRow<KgRow> implements SerializablePostalObject {

    private static final RowType rowType = RowType.KG;

    @Version
    @Column(name = "version")
    private Date version;

    private String kgDatum;
    @EmbeddedId
    private KgRowId kgRowId;
    private String kgName;

    public static KgRow parseFrom(String rowBytes) {
        KgRow row = new KgRow();
        row.importingFileIdentifier = rowBytes.substring(0, 9);
        row.kgDatum = rowBytes.substring(9, 17);
        row.kgName = rowBytes.substring(26, 66);
        row.kgRowId = new KgRowId(rowBytes.substring(17, 25), rowBytes.substring(25, 26));
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
