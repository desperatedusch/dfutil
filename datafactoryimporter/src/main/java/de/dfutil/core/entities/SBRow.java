package de.dfutil.core.entities;

import de.dfutil.entities.Strasse;

public class SBRow implements AbstractDataFactoryRow<Strasse> {

    //"SB9410001" für die erste Version der STRA-DB im Oktober 1994
    private String version;
    //= "SB" für alle Sätze der Datei STRA-DB
    private static final String DATEIKENNUNG = "SB";
    //Jahr und Monat der Versionslieferung
    private String versionDatum;
    //Datum der Archivierung (STR-STATUS=S oder N) bzw. Datum des Zugangs (STR-STATUS = G)
    //    private String strDatum;
    //Alphanummer des Bestimmungsortes in der letzten Zeile der Adresse
    private String strAlOrt;
    //Bundesweiter Straßenschlüssel
    private String strSchluessel;
    //Straßennamenschlüssel
    private String straßennamenschluessel;
    //Bundesweite Unterscheidungsnummer für Straßen gleichen Namens
    private String strBundLfdNr;
    //erste Hausnr. des Straßenabschnitts
    private String strHnrVon;
    //letzte Hausnr. des Straßenabschnitts
    private String strHnrBis;
    //ArchivierungsStatus
    private SBGueltigkeitsStatus gueltigkeitsStatus;



    public static Strasse fromBytes(byte[] rowChars) {
        if (rowChars.length != 324)
            throw new IllegalArgumentException(rowChars + " entspricht nicht dem festgelegten Zeilenformat");

        Strasse parsedElement = new Strasse();
        return parsedElement;
    }

    @Override
    public Strasse parsedFrom(byte[] rowBytes) {
        return fromBytes(rowBytes);
    }

    private static class SBGueltigkeitsStatus {
        private String datum;
        private String schluessel;
        private ArchivierungsStatus status;
        private String schluesselNeu;

        SBGueltigkeitsStatus(String datum, String schluessel, ArchivierungsStatus status, String schluesselNeu) {
            this.datum = datum;
            this.schluessel = schluessel;
            this.status = status;
            this.schluesselNeu = schluesselNeu;
        }
    }
}


