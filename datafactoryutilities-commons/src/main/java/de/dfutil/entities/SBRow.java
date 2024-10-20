package de.dfutil.entities;

import de.dfutil.entities.format.v2308213.RowType;
import de.dfutil.entities.format.v2308213.SBRowFormat;
import jakarta.persistence.Entity;
import org.springframework.data.redis.core.RedisHash;

@Entity(name = "Street")
@RedisHash("Street")
public class SBRow implements AbstractDataFactoryRow<SBRow, SBRowFormat>, PostalObject {

    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    private Long id;

    private final RowType rowType = RowType.SB;
    //"SB9410001" für die erste Version der STRA-DB im Oktober 1994
    private String version;
    //= "SB" für alle Sätze der Datei STRA-DB
    private static final String DATEIKENNUNG = "SB";
    //Jahr und Monat der Versionslieferung
    private String versionDatum;
    //Datum der Archivierung (STR-STATUS=S oder N) bzw. Datum des Zugangs (STR-STATUS = G)
    private String strDatum;
    //Alphanummer des Bestimmungsortes in der letzten Zeile der Adresse
    private String strAlOrt;
    //Bundesweiter Straßenschlüssel
    private String strSchluessel;
    //Straßennamenschlüssel
    private String strassennamenschluessel;
    //Bundesweite Unterscheidungsnummer für Straßen gleichen Namens
    private String strBundLfdNr;
    //erste Hausnr. des Straßenabschnitts
    private String strHnrVon;
    //letzte Hausnr. des Straßenabschnitts
    private String strHnrBis;
    //ArchivierungsStatus
    @SuppressWarnings("JpaAttributeTypeInspection")
    private ValidityStatus gueltigkeitsStatus;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public SBRow parsedFrom(byte[] rowBytes, SBRowFormat rowFormat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}


