package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractRow;
import de.dfutil.entities.SerializablePostalObject;
import de.dfutil.entities.format.KgRowFormat;
import de.dfutil.entities.format.RowType;
import de.dfutil.entities.jpa.ids.KgRowId;
import jakarta.persistence.*;

import java.util.Date;

/**
 * In der Kreisgemeindedatei KGS-DA stehen die amtlichen Namen der Gemeinden, Kreise, kreisfreien Städte,
 * Regierungsbezirke und Bundesländer zusammen mit dem achtstelligen Kreisgemeindeschlüssel. Diese Daten
 * werden vom Statistischen Bundesamt in Wiesbaden übernommen und aufgrund der Angaben der statistischen
 * Landesämter erweitert. Mit Hilfe dieses Schlüssels wird die staatliche Gliederung in den Dateien ORT-DA,
 * STRA-DB, OTL-DB und PLZ-DA dargestellt.
 */
@Entity
public class KgRow implements AbstractRow<KgRow, KgRowFormat>, SerializablePostalObject {

    private final static RowType rowType = RowType.KG;
    @Version
    @Column(name = "version")
    private Date version;
    private String kgDatum;

    @EmbeddedId
    private KgRowId kgId;

    @Transient
    private String kgSchluessel;
    @Transient
    private String kgSatzart;

    private String kgName;

    @Override
    public KgRow parseFrom(byte[] rowBytes) {
        for (var token : KgRowFormat.values()) {
            try {
                if (token.parseableContent())
                    applyRowFormatTokenOnRowBytes(token, rowBytes, this);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException("Parsing failed....", e);
            }
        }
        this.kgId = new KgRowId(kgSchluessel, kgSatzart);
        return this;
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

    public String kgSchluessel() {
        return kgSchluessel;
    }

    public void setKgSchluessel(String kgSchluessel) {
        this.kgSchluessel = kgSchluessel;
    }

    public String kgSatzart() {
        return kgSatzart;
    }

    public void setKgSatzart(String kgSatzart) {
        this.kgSatzart = kgSatzart;
    }

    public RowType getRowType() {
        return rowType;
    }

}


