package de.dfutil.entities;

public enum ArchivingStatus {
    VALID("G"),
    SINGLE_SUCCESSOR("S"),
    MULTIPLE_SUCCESSORS("N"),
    INVALID("W");

    private String statusValue;

    private ArchivingStatus(String statusValue) {
        this.statusValue = statusValue;
    }
}
