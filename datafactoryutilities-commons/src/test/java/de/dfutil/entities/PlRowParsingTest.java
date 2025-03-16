package de.dfutil.entities;

import de.dfutil.entities.ids.PlRowId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlRowParsingTest {

    private static final String HAMBURGERPLZ =
            "PL23082132007031220639213365000522Hamburg                                                                Hamburg                 N686026222133650002000000      0  2120$                                                                                                                                                            \n";

    @Test
    public void parsePlRowBytes2Object() {
        PlRow expectedPlRow = new PlRow();
        expectedPlRow.setPlzDatum("20070312");
        expectedPlRow.setPlRowId(new PlRowId("20639", "21336500"));
        expectedPlRow.setPlzArtKardinalität("0");
        expectedPlRow.setPlzArtAuslierferung("5");
        expectedPlRow.setPlzStverz("2");
        expectedPlRow.setPlzPfverz("2");
        expectedPlRow.setPlzOname("Hamburg                                 ");
        expectedPlRow.setPlzOzusatz("                              ");
        expectedPlRow.setPlzArtOzusatz(" ");
        expectedPlRow.setPlzOname24("Hamburg                 ");
        expectedPlRow.setPlzPostlag("N");
        expectedPlRow.setPlzLaBrief("68602622");
        expectedPlRow.setPlzLaAlort("21336500");
        expectedPlRow.setPlzKgs("02000000");
        expectedPlRow.setPlzOrtCode("   ");
        expectedPlRow.setPlzLeitcodeMax("   ");
        expectedPlRow.setPlzRabattInfoSchwer("0");
        expectedPlRow.setPlzReserve("  ");
        expectedPlRow.setPlzFzNr("21");
        expectedPlRow.setPlzBzNr("20");
        expectedPlRow.setImportingFileIdentifier("PL2308213");
        PlRow parsedPlRow = PlRow.parseFrom(HAMBURGERPLZ);
        Assertions.assertEquals(expectedPlRow, parsedPlRow);
    }
}
