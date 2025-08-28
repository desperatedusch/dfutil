package de.dfutil.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractRowEntity<T extends SerializablePostalObject> {

    protected UUID uuid;

    protected String importingFileIdentifier;

    @CreatedDate
    protected Date createdAt;

    @LastModifiedDate
    protected Date modifiedAt;

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }

    public String getImportingFileIdentifier() {
        return this.importingFileIdentifier;
    }

    public void setImportingFileIdentifier(final String importingFileIdentifier) {
        this.importingFileIdentifier = importingFileIdentifier;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return this.modifiedAt;
    }

    public void setModifiedAt(final Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.uuid = UUID.randomUUID();
    }

}
