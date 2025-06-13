package de.dfutil.entities;

import de.dfutil.entities.ids.KgRowId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KgRowParsingTest {

    private static final String FLENSBURG_STADT =
            "KG23092442023073101001000KFlensburg, Stadt                        $                                                                                                                                                                                                                                                                 \n";

    @Test
    public void parseKgRowBytes2Object() {
        KgRowEntity expectedKgRow = new KgRowEntity();
        expectedKgRow.setKgDatum("20230731");
        expectedKgRow.setKgName("Flensburg, Stadt                        ");
        expectedKgRow.setKgRowId(new KgRowId("01001000", "K"));
        expectedKgRow.setImportingFileIdentifier("KG2309244");
        KgRowEntity parsedKgRow = KgRowEntity.parseFrom(FLENSBURG_STADT);
        Assertions.assertEquals(expectedKgRow, parsedKgRow);
    }
}
