package de.dfutil.entities;

/**
 * Objekte, die durch Gebietsreformen moeglichen Aenderungen unterliegen
 */
public interface ArchivablePostalObject extends SerializablePostalObject {

    SuccessionState getSuccessionState();

    public void setSuccessionState(SuccessionState successionState);

}
