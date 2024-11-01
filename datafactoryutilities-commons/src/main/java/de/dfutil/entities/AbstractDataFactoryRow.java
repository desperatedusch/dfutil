package de.dfutil.entities;

import de.dfutil.entities.format.RowFormat;

public interface AbstractDataFactoryRow<T extends PostalObject, RF extends RowFormat<?>> {

    void accept(RF p, byte[] rowBytes) throws ClassNotFoundException, NoSuchMethodException;

    public T parseFrom(byte[] rowBytes);

}
