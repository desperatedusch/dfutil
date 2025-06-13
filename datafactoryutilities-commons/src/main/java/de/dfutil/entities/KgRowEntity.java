package de.dfutil.entities;

import de.dfutil.entities.ids.KgRowId;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

/**
 * In der Kreisgemeindedatei KGS-DA stehen die amtlichen Namen der Gemeinden, Kreise, kreisfreien
 * Städte, Regierungsbezirke und Bundesländer zusammen mit dem achtstelligen Kreisgemeindeschlüssel.
 * Diese Daten werden vom Statistischen Bundesamt in Wiesbaden übernommen und aufgrund der Angaben
 * der statistischen Landesämter erweitert. Mit Hilfe dieses Schlüssels wird die staatliche
 * Gliederung in den Dateien ORT-DA, STRA-DB, OTL-DB und PLZ-DA dargestellt.
 */
@Entity
@Table(
        name = "kg_row",
        indexes =
                {
                        @Index(
                                name = "IDX_AGS___KG_SCHLUESSEL__KG_SATZART",
                                columnList = "KG_SCHLUESSEL,KG_SATZART"),
                        @Index(
                                name = "IDX_AGS___UUID",
                                columnList = "UUID")
                }
)
public class KgRowEntity extends AbstractRowEntity<KgRowEntity> implements SerializablePostalObject {

    private static final RowType rowType = RowType.KG;

    @Version
    @Column(name = "version")
    private Date version;

    private String kgDatum;
    @EmbeddedId
    private KgRowId kgRowId;
    private String kgName;

    public static KgRowEntity parseFrom(String rowBytes) {
        KgRowEntity row = new KgRowEntity();
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        KgRowEntity kgRow = (KgRowEntity) o;
        return Objects.equals(version, kgRow.version) && Objects.equals(kgDatum, kgRow.kgDatum) && Objects.equals(kgRowId, kgRow.kgRowId) && Objects.equals(kgName, kgRow.kgName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, kgDatum, kgRowId, kgName);
    }

}
