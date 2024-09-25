package de.dfutil.entities;

public enum ArchivingsStatus {
    VALID("G"),
    SINGLE_SUCCESSOR("S"),
    MULTIPLE_SUCCESSORS("N"),
    INVALID("W");

    private String statusValue;

    private ArchivingsStatus(String statusValue) {
        this.statusValue = statusValue;
    }
}
