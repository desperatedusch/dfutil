package de.dfutil.entities;

/**
 * Objekte, die durch Gebietsreformen moeglichen Aenderungen unterliegen
 */
public interface ArchivablePostalObject extends SerializablePostalObject {

    SuccessionStateLinkElement getSuccessionState();

    void setSuccessionState(SuccessionStateLinkElement successionStateLinkElement);

}
