package de.dfutil.entities;

public interface AbstractDataFactoryRow<T extends PostalObject> {

    public T parseFrom(byte[] rowBytes);

}
