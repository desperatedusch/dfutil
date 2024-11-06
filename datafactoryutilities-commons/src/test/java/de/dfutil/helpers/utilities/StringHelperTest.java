package de.dfutil.helpers.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringHelperTest {

    @Test
    public void convertHyphenSnakeToCamelCaseTest() {
        Assertions.assertEquals("strDatum", StringHelper.convertHyphenSnakeToCamelCase("STR-DATUM"));
    }

}
