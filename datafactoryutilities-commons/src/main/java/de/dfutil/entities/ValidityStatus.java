package de.dfutil.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class ValidityStatus {

    @Id
    private Long id;

    private String date;
    private String key;
    private ArchivingStatus state;
    private String keyNew;

    public ValidityStatus() {
    }

    public void setKeyNew(String keyNew) {
        this.keyNew = keyNew;
    }

    public String getKeyNew() {
        return keyNew;
    }

    public void setState(ArchivingStatus state) {
        this.state = state;
    }

    public ArchivingStatus getState() {
        return state;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
