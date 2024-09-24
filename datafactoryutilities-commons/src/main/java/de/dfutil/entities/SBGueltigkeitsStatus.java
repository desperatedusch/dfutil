package de.dfutil.entities;

public class SBGueltigkeitsStatus {
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
