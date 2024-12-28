package de.dfutil.entities.jpa;

import de.dfutil.entities.AbstractDataFactoryRow;
import de.dfutil.entities.SerializablePostalObject;
import de.dfutil.entities.format.KgRowFormat;
import de.dfutil.entities.format.RowType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Version;

import java.util.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Repräsentiert Bundesländer, Regierungsbezirke und Kreise
 */
@Entity
public class KgRow implements AbstractDataFactoryRow<KgRow, KgRowFormat>, SerializablePostalObject {

    private final static RowType rowType = RowType.KG;
    @jakarta.persistence.Id
    @GeneratedValue(strategy = SEQUENCE, generator = "ID_SEQ")
    @Column(name = "id")
    private Long id;
    @Version
    @Column(name = "version")
    private Date version;


    private String kgDatum;
    private String kgSchluessel;
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
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getKgDatum() {
        return kgDatum;
    }

    public void setKgDatum(String kgDatum) {
        this.kgDatum = kgDatum;
    }

    public String getKgSchluessel() {
        return kgSchluessel;
    }

    public void setKgSchluessel(String kgSchluessel) {
        this.kgSchluessel = kgSchluessel;
    }

    public String getKgSatzart() {
        return kgSatzart;
    }

    public void setKgSatzart(String kgSatzart) {
        this.kgSatzart = kgSatzart;
    }

    public String getKgName() {
        return kgName;
    }

    public void setKgName(String kgName) {
        this.kgName = kgName;
    }
}


