package de.dfutil.core.entities;

import de.dfutil.core.entities.format.RowFormat;
import de.dfutil.entities.PostalObject;

public interface AbstractDataFactoryRow<T extends PostalObject, RF extends RowFormat> {

    T parsedFrom(byte[] rowBytes, RF rowFormat);

}
