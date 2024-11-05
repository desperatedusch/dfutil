package de.dfutil.entities;

import de.dfutil.entities.format.RowFormat;
import de.dfutil.helpers.utilities.StringHelper;
import org.springframework.beans.PropertyAccessor;
import org.springframework.util.StringUtils;

import java.util.Arrays;


public interface AbstractDataFactoryRow<T extends PostalObject, RF extends RowFormat<?>> {

    default void applyRowFormatTokenOnRowBytes(RF token, byte[] rowBytes) throws ClassNotFoundException, NoSuchMethodException {
        PropertyAccessor propertyAccessor = propertyAccessor();
        propertyAccessor.setPropertyValue(StringUtils.capitalize(
                StringHelper.convertHyphenSnakeToCamelCase(
                        token.paramName())), Arrays.copyOfRange(rowBytes, token.startingPos(), token.endingPos()));
    }

    PropertyAccessor propertyAccessor();

    T parseFrom(byte[] rowBytes);

}
