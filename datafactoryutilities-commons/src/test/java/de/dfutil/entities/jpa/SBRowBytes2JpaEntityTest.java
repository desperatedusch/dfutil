package de.dfutil.entities.jpa;

import org.junit.jupiter.api.Test;

public class SBRowBytes2JpaEntityTest {

    private static final byte[] ALPENSTRASSE = "SB2410277199910270000210000176200013                G01ALPENSTR                                      Alpenstr.                                     Alpenstr.             0N78267001   1318807008335001                                   $                                                                                          \n".getBytes();

    @Test
    public void parseSBRowBytes2Object() {
        SbRow parsedSbRow = new SbRow().parseFrom(ALPENSTRASSE);
        parsedSbRow.toString();
        SbRow expectedSbRow = new SbRow();

    }

}
