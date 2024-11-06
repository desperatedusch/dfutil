package de.dfutil.entities;

import de.dfutil.entities.format.RowFormat;
import de.dfutil.helpers.utilities.StringHelper;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;


public interface AbstractDataFactoryRow<T extends PostalObject, RF extends RowFormat<?>> {

    default void applyRowFormatTokenOnRowBytes(RF token, byte[] rowBytes, T containingPostalObject) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException {

        Field field = ReflectionUtils.findField(containingPostalObject.getClass(), StringHelper.convertHyphenSnakeToCamelCase(token.paramName()));
        if (Objects.nonNull(field)) {
            ReflectionUtils.makeAccessible(field);
            field.set(
                    containingPostalObject,
                    new String(Arrays.copyOfRange(
                            rowBytes,
                            token.startingPos(),
                            token.endingPos()),
                            StandardCharsets.UTF_8
                    ));
        }
    }

    T parseFrom(byte[] rowBytes);

}
