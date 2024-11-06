package de.dfutil.core;

import de.dfutil.entities.SBRow;
import org.junit.jupiter.api.Test;

public class SBRowBytes2ObjectTest {

    private static final byte[] ALPENSTRASSE = "SB2410277199910270000210000176200013                G01ALPENSTR                                      Alpenstr.                                     Alpenstr.             0N78267001   1318807008335001                                   $                                                                                          \n".getBytes();

    @Test
    public void parseSBRowBytes2Object() {
        SBRow parsedSBRow = new SBRow().parseFrom(ALPENSTRASSE);
        parsedSBRow.toString();
        SBRow expectedSBRow = new SBRow();

    }

}
