package de.dfutil.entities;

public enum ArchivierungsStatus {
    GUELTIG("G"),
    EIN_NACHFOLGER("S"),
    MEHRERE_NACHFOLGER("N"),
    WEGGEFALLEN("W");

    private String statusKennzeichen;

    private ArchivierungsStatus(String statusKennzeichen) {
        this.statusKennzeichen = statusKennzeichen;
    }
}
