package de.dfutil.entities;

public class ValidityStatus {
    private String date;
    private String key;
    private ArchivingsStatus state;
    private String keyNew;

    public ValidityStatus(String date, String key, ArchivingsStatus state, String keyNew) {
        this.date = date;
        this.key = key;
        this.state = state;
        this.keyNew = keyNew;
    }
}
