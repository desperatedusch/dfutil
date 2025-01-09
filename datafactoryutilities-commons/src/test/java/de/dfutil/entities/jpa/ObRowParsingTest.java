package de.dfutil.entities.jpa;

import org.junit.jupiter.api.Test;

public class ObRowParsingTest {

    private static final byte[] LAURENSBERG = "OB2309244199412030000400000852070G2Laurensberg                             05334002$                                                                                                                                                                                                                                                \n".getBytes();

    @Test
    public void parseObRowBytes2Object() {
        ObRow parsedObRow = new ObRow().parseFrom(LAURENSBERG);
        parsedObRow.toString();
    }

}
