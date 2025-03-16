package de.dfutil.entities.jpa;

import de.dfutil.entities.KgRow;
import de.dfutil.entities.ids.KgRowId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KgRowParsingTest {

    private static final String FLENSBURG_STADT =
            "KG23092442023073101001000KFlensburg, Stadt                        $                                                                                                                                                                                                                                                                 \n";

    @Test
    public void parseKgRowBytes2Object() {
        KgRow expectedKgRow = new KgRow();
        expectedKgRow.setKgDatum("20230731");
        expectedKgRow.setKgName("Flensburg, Stadt                        ");
        expectedKgRow.setKgRowId(new KgRowId("01001000", "K"));
        expectedKgRow.setImportingFileIdentifier("KG2309244");
        KgRow parsedKgRow = KgRow.parseFrom(FLENSBURG_STADT);
        Assertions.assertEquals(expectedKgRow, parsedKgRow);
    }
}
