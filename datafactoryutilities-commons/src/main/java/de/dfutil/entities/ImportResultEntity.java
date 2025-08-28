package de.dfutil.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "import_result")
public class ImportResultEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String fileName;
    private LocalDateTime importDate;
    private boolean importSuccessful;
    private Long duration;

    public ImportResultEntity(
            final String fileName, final LocalDateTime importDate, final boolean importSuccessful, final Long duration) {
        this.fileName = fileName;
        this.importDate = importDate;
        this.importSuccessful = importSuccessful;
        this.duration = duration;
    }

    public ImportResultEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getImportDate() {
        return this.importDate;
    }

    public void setImportDate(final LocalDateTime importDate) {
        this.importDate = importDate;
    }

    public boolean isImportSuccessful() {
        return this.importSuccessful;
    }

    public void setImportSuccessful(final boolean importSuccessful) {
        this.importSuccessful = importSuccessful;
    }

    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(final Long duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (null == o || this.getClass() != o.getClass()) return false;
        final ImportResultEntity that = (ImportResultEntity) o;
        return this.importSuccessful == that.importSuccessful
                && Objects.equals(this.fileName, that.fileName)
                && Objects.equals(this.importDate, that.importDate)
                && Objects.equals(this.duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.fileName, this.importDate, this.importSuccessful);
    }

}
