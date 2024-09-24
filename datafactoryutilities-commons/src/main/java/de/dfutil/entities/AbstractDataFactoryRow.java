package de.dfutil.entities;

import de.dfutil.entities.format.RowFormat;

public interface AbstractDataFactoryRow<T extends PostalObject, RF extends RowFormat> {

    T parsedFrom(byte[] rowBytes, RF rowFormat);

}
