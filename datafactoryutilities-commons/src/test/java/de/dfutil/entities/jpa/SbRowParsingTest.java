package de.dfutil.entities.jpa;

import org.junit.jupiter.api.Test;

public class SbRowParsingTest {

    private static final byte[] ALPENSTRASSE = "SB2410277199910270000210000176200013                G01ALPENSTR                                      Alpenstr.                                     Alpenstr.             0N78267001   1318807008335001                                   $                                                                                          \n".getBytes();

    @Test
    public void parseSBRowBytes2Object() {
        SbRow parsedSbRow = SbRow.parseFrom(ALPENSTRASSE);
        parsedSbRow.toString();
    }

}
