package de.dfutil.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class ImportResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String fileName;
    private LocalDateTime importDate;
    private boolean importSuccessful;
    private Long duration;

    public ImportResult(
            String fileName, LocalDateTime importDate, boolean importSuccessful, Long duration) {
        this.fileName = fileName;
        this.importDate = importDate;
        this.importSuccessful = importSuccessful;
        this.duration = duration;
    }

    public ImportResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDateTime importDate) {
        this.importDate = importDate;
    }

    public boolean isImportSuccessful() {
        return importSuccessful;
    }

    public void setImportSuccessful(boolean importSuccessful) {
        this.importSuccessful = importSuccessful;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportResult that = (ImportResult) o;
        return importSuccessful == that.importSuccessful
                && Objects.equals(fileName, that.fileName)
                && Objects.equals(importDate, that.importDate)
                && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, importDate, importSuccessful);
    }

}
