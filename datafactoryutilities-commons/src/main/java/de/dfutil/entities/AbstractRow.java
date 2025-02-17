package de.dfutil.entities;

import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
public abstract class AbstractRow<T extends SerializablePostalObject> {

    protected String importingFileIdentifier;

    @CreatedDate
    protected Date createdAt;

    @LastModifiedDate
    protected Date modifiedAt;

    public String getImportingFileIdentifier() {
        return importingFileIdentifier;
    }

    public void setImportingFileIdentifier(String importingFileIdentifier) {
        this.importingFileIdentifier = importingFileIdentifier;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

}
