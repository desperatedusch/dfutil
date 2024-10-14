package de.dfutil.entities;

import java.util.Objects;

public class ValidityStatus {
    private String date;
    private String key;
    private ArchivingStatus state;
    private String keyNew;

    public String keyNew() {
        return keyNew;
    }

    public void setKeyNew(String keyNew) {
        this.keyNew = keyNew;
    }

    public ArchivingStatus state() {
        return state;
    }

    public void setState(ArchivingStatus state) {
        this.state = state;
    }

    public String key() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String date() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ValidityStatus(String date, String key, ArchivingStatus state, String keyNew) {
        this.date = date;
        this.key = key;
        this.state = state;
        this.keyNew = keyNew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidityStatus that = (ValidityStatus) o;
        return Objects.equals(date, that.date) && Objects.equals(key, that.key) && state == that.state && Objects.equals(keyNew, that.keyNew);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, key, state, keyNew);
    }
}
