package de.dfutil.entities;

import de.dfutil.entities.ids.ObRowId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObRowParsingTest {

    private static final String LAURENSBERG =
            "OB2309244199412030000400000852070G2Laurensberg                             05334002$                                                                                                                                                                                                                                                \n";

    @Test
    public void parseObRowBytes2Object() {
        final ObRowEntity expectedObRow = new ObRowEntity();
        expectedObRow.setOtlDatum("19941203");
        expectedObRow.setObRowId(new ObRowId("00004000", "008", "52070", "G"));
        expectedObRow.setOtlStverz("2");
        expectedObRow.setOtlName("Laurensberg                             ");
        expectedObRow.setOtlKgs("05334002");
        expectedObRow.setImportingFileIdentifier("OB2309244");
        final ObRowEntity parsedObRow = ObRowEntity.parseFrom(ObRowParsingTest.LAURENSBERG);
        Assertions.assertEquals(expectedObRow, parsedObRow);
    }
}
