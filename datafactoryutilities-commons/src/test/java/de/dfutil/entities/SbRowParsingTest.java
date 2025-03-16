package de.dfutil.entities;

import de.dfutil.entities.ids.SbRowId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SbRowParsingTest {

    private static final String ALPENSTRASSE =
            "SB2410277199910270000210000176200013                G01ALPENSTR                                      Alpenstr.                                     Alpenstr.             0N78267001   1318807008335001                                   $                                                                                          \n";

    @Test
    public void parseSBRowBytes2Object() {
        SbRow expectedSbRow = new SbRow();
        expectedSbRow.setStrDatum("19991027");
        expectedSbRow.setSbRowId(new SbRowId("00002100", "001762", "00013", "        ", "        ", "G", "0"));
        expectedSbRow.setStrStverz("1");
        expectedSbRow.setStrNameSort("ALPENSTR                                      ");
        expectedSbRow.setStrName46("Alpenstr.                                     ");
        expectedSbRow.setStrName22("Alpenstr.             ");
        expectedSbRow.setStrReserve("0");
        expectedSbRow.setStrHnrTyp("N");
        expectedSbRow.setStrPlz("78267");
        expectedSbRow.setStrCode("001");
        expectedSbRow.setStrOtlSchl("   ");
        expectedSbRow.setStrAlorgB("13188070");
        expectedSbRow.setStrKgs("08335001");
        expectedSbRow.setStrAlortNeu("        ");
        expectedSbRow.setStrNamenSchlNeu("      ");
        expectedSbRow.setStrBundLfdnrNeu("     ");
        expectedSbRow.setStrHnrvonNeu("        ");
        expectedSbRow.setStrHnrbisNeu("        ");
        expectedSbRow.setImportingFileIdentifier("SB2410277");
        SbRow parsedSbRow = SbRow.parseFrom(ALPENSTRASSE);
        Assertions.assertEquals(expectedSbRow, parsedSbRow);
    }
}
