package de.dfutil.entities;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractRow<T extends SerializablePostalObject> {

    protected String importingFileIdentifier;

    public String getImportingFileIdentifier() {
        return importingFileIdentifier;
    }

    public void setImportingFileIdentifier(String importingFileIdentifier) {
        this.importingFileIdentifier = importingFileIdentifier;
    }

}
