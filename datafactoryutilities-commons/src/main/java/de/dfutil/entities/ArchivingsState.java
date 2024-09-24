package de.dfutil.entities;

public enum ArchivingsState {
    VALID("G"),
    SINGLE_SUCCESSOR("S"),
    MULTIPLE_SUCCESSORS("N"),
    INVALID("W");

    private String statusValue;

    private ArchivingsState(String statusValue) {
        this.statusValue = statusValue;
    }
}
