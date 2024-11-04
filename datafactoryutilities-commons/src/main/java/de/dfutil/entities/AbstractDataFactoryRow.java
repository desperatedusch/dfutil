package de.dfutil.entities;

import de.dfutil.entities.format.RowFormat;
import de.dfutil.helpers.utilities.StringHelper;
import org.springframework.beans.PropertyAccessor;
import org.springframework.util.StringUtils;

import java.util.Arrays;


public interface AbstractDataFactoryRow<T extends PostalObject, RF extends RowFormat<?>> {

    default void accept(RF p, byte[] rowBytes) throws ClassNotFoundException, NoSuchMethodException {
        PropertyAccessor propertyAccessor = propertyAccessor();
        propertyAccessor.setPropertyValue(StringUtils.capitalize(StringHelper.convertHyphenSnakeToCamelCase(p.paramName())), Arrays.copyOfRange(rowBytes, p.startingPos(), p.endingPos()));
    }

    PropertyAccessor propertyAccessor();

    T parseFrom(byte[] rowBytes);


}
