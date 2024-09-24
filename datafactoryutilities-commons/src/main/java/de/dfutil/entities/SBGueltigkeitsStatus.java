package de.dfutil.entities;

public class SBGueltigkeitsStatus {
    private String datum;
    private String schluessel;
    private ArchivingsState status;
    private String schluesselNeu;

    public SBGueltigkeitsStatus(String datum, String schluessel, ArchivingsState status, String schluesselNeu) {
        this.datum = datum;
        this.schluessel = schluessel;
        this.status = status;
        this.schluesselNeu = schluesselNeu;
    }
}
