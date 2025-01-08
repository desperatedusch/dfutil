package de.dfutil.entities;

import java.util.Collections;
import java.util.List;

/**
 * Objekte, die durch Gebietsreformen Aenderungen unterliegen
 */
public interface ArchivablePostalObject extends SerializablePostalObject {

    default boolean isValid() {
        return true;
    }

    default List<ArchivablePostalObject> successors() {
        return Collections.emptyList();
    }

    default ArchivablePostalObject successor() {
        return new ArchivablePostalObject() {
        };
    }

    default boolean isRemoved() {
        return false;
    }

}
