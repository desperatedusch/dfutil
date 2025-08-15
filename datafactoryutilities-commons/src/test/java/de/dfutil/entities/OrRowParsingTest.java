package de.dfutil.entities;

import de.dfutil.entities.ids.OrRowId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrRowParsingTest {

    private static final String HOPSTENBAUERSCHAFT =
            "OR23092441978010100000500SAa-Bauerschaft                          Hopsten                                                                Aa-Bauerschaft                  26631500$                                                                                                                                                  \n";

    @Test
    public void parseOrRowBytes2Object() {
        OrRowEntity expectedOrRow = new OrRowEntity();
        expectedOrRow.setOrtDatum("19780101");
        expectedOrRow.setOrRowId(new OrRowId("00000500", "S"));
        expectedOrRow.setOrtOname("Aa-Bauerschaft                          ");
        expectedOrRow.setOrtOnamePost("Hopsten                                 ");
        expectedOrRow.setOrtOzusatz("                              ");
        expectedOrRow.setOrtArtOzusatz(" ");
        expectedOrRow.setOrtOname24("Aa-Bauerschaft          ");
        expectedOrRow.setOrtKgs("        ");
        expectedOrRow.setOrtAlortNeu("26631500");
        expectedOrRow.setImportingFileIdentifier("OR2309244");
        OrRowEntity parsedOrRow = OrRowEntity.parseFrom(HOPSTENBAUERSCHAFT);
        Assertions.assertEquals(expectedOrRow, parsedOrRow);
    }
}
