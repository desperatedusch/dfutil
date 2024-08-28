package de.dfutil.core.entities;

import de.dfutil.entities.PostalObject;

public abstract class AbstractDataFactoryRow<T extends PostalObject> {

    public abstract T parsedFrom(byte[] rowBytes);

}
