package de.dfutil.core.entities;

import de.dfutil.entities.PostalObject;

public interface AbstractDataFactoryRow<T extends PostalObject> {

    public T parsedFrom(byte[] rowBytes);

}
