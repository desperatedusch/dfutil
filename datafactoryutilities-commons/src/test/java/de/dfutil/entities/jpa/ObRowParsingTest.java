package de.dfutil.entities.jpa;

import org.junit.jupiter.api.Test;

public class ObRowParsingTest {

    private static final String LAURENSBERG =
            "OB2309244199412030000400000852070G2Laurensberg                             05334002$                                                                                                                                                                                                                                                \n";

    @Test
    public void parseObRowBytes2Object() {
        ObRow parsedObRow = ObRow.parseFrom(LAURENSBERG);
        parsedObRow.toString();
    }
}
